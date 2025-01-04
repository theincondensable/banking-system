package io.incondensable.validator.transaction;

import io.incondensable.controller.dto.TransactionRequestDTO;
import io.incondensable.global.exception.models.BadRequestBusinessException;
import io.incondensable.global.exception.models.NotFoundBusinessException;
import io.incondensable.repository.AccountBalanceRepository;
import io.incondensable.repository.UserAccountRepository;
import org.springframework.stereotype.Service;

/**
 * @author abbas
 */
@Service
public abstract class AbstractTransactionValidator implements TransactionValidator {

    protected final UserAccountRepository userAccountRepository;
    protected final AccountBalanceRepository accountBalanceRepository;

    protected AbstractTransactionValidator(UserAccountRepository userAccountRepository, AccountBalanceRepository accountBalanceRepository) {
        this.userAccountRepository = userAccountRepository;
        this.accountBalanceRepository = accountBalanceRepository;
    }

    public void validateRequest(TransactionRequestDTO req) {
        if (!userAccountRepository.existsById(req.getAccountHolderUserId()))
            throw new NotFoundBusinessException(
                    "transaction.holder.user.account.not.found",
                    new Object[]{req.getAccountHolderUserId()}
            );
    }

    public void validateWithdrawAndTransfer(TransactionRequestDTO req) {
        accountBalanceRepository.findAccountBalanceByUserAccount(req.getAccountHolderUserId()).ifPresentOrElse(
                accountBalance -> {
                    if (accountBalance.getBalance() < req.getAmount())
                        throw new BadRequestBusinessException(
                                "account.holder.balance.not.enough",
                                new Object[]{req.getAccountHolderUserId(), req.getAmount()}
                        );
                }, () -> {
                    throw new BadRequestBusinessException(
                            "account.holder.has.never.deposited",
                            new Object[]{req.getAccountHolderUserId()}
                    );
                }
        );
    }

}
