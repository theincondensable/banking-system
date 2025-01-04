package io.incondensable.validator.transaction;

import io.incondensable.controller.dto.TransactionRequestDTO;
import io.incondensable.repository.AccountBalanceRepository;
import io.incondensable.repository.UserAccountRepository;
import org.springframework.stereotype.Service;

/**
 * @author abbas
 */
@Service
public class WithdrawTransactionValidator extends AbstractTransactionValidator {

    protected WithdrawTransactionValidator(UserAccountRepository userAccountRepository, AccountBalanceRepository accountBalanceRepository) {
        super(userAccountRepository, accountBalanceRepository);
    }

    @Override
    public void validateRequest(TransactionRequestDTO req) {
        super.validateRequest(req);
        super.validateWithdrawAndTransfer(req);
    }
}
