package io.incondensable.service.transaction.implementations;

import io.incondensable.controller.dto.TransactionRequestDTO;
import io.incondensable.global.LoggerObserver;
import io.incondensable.model.TransactionTypeEnum;
import io.incondensable.model.entity.AccountBalance;
import io.incondensable.service.balance.AccountBalanceService;
import io.incondensable.service.transaction.TransactionStrategy;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * @author abbas
 */
@Service
public class WithdrawTransactionUseCase implements TransactionStrategy {

    private final LoggerObserver withdrawLogger;
    private final AccountBalanceService accountBalanceService;

    public WithdrawTransactionUseCase(LoggerObserver withdrawLogger, AccountBalanceService accountBalanceService) {
        this.withdrawLogger = withdrawLogger;
        this.accountBalanceService = accountBalanceService;
    }

    @Override
    public void execute(TransactionRequestDTO req) {
        CompletableFuture.supplyAsync(() -> {
            AccountBalance toWithdraw = accountBalanceService.getByUserAccountId(req.getAccountHolderUserId());

            toWithdraw.setBalance(
                    toWithdraw.getBalance() - req.getAmount()
            );

            return accountBalanceService.updateAfterTransaction(toWithdraw);
        }).whenComplete(((accountBalance, ex) -> withdrawLogger.onTransaction(
                accountBalance.getAccount().getAccountNumber(),
                req.getAmount(),
                TransactionTypeEnum.WITHDRAWAL
        )));
    }

}
