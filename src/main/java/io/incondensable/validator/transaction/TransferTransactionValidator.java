package io.incondensable.validator.transaction;

import io.incondensable.controller.dto.TransactionRequestDTO;
import io.incondensable.exceptions.transaction.TransferTargetAccountNotFound;
import io.incondensable.global.exception.models.BadRequestBusinessException;
import io.incondensable.repository.AccountBalanceRepository;
import io.incondensable.repository.UserAccountRepository;
import org.springframework.stereotype.Service;

/**
 * @author abbas
 */
@Service
public class TransferTransactionValidator extends AbstractTransactionValidator {

    public TransferTransactionValidator(UserAccountRepository userAccountRepository, AccountBalanceRepository accountBalanceRepository) {
        super(userAccountRepository, accountBalanceRepository);
    }

    @Override
    public void validateRequest(TransactionRequestDTO req) {
        super.validateRequest(req);
        if (req.getTargetUserId() == null)
            throw new BadRequestBusinessException("transfer.needs.target.account", new Object[]{req.getTargetUserId()});

        boolean doesTransferTargetExist = userAccountRepository.existsById(req.getTargetUserId());
        if (!doesTransferTargetExist)
            throw new TransferTargetAccountNotFound(req.getTargetUserId());

        super.validateWithdrawAndTransfer(req);
    }
}
