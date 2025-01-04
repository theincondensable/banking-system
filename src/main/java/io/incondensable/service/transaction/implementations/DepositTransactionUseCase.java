package io.incondensable.service.transaction.implementations;

import io.incondensable.controller.dto.TransactionRequestDTO;
import io.incondensable.global.LoggerObserver;
import io.incondensable.model.TransactionTypeEnum;
import io.incondensable.model.entity.AccountBalance;
import io.incondensable.service.balance.AccountBalanceService;
import io.incondensable.service.transaction.TransactionStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * @author abbas
 */
@Service
public class DepositTransactionUseCase implements TransactionStrategy {

    private final LoggerObserver depositLogger;
    private final AccountBalanceService accountBalanceService;

    public DepositTransactionUseCase(LoggerObserver depositLogger, AccountBalanceService accountBalanceService) {
        this.depositLogger = depositLogger;
        this.accountBalanceService = accountBalanceService;
    }

    @Override
    public void execute(TransactionRequestDTO req) {
        CompletableFuture.supplyAsync(() -> {
            AccountBalance toDeposit = accountBalanceService.getByUserAccountId(req.getAccountHolderUserId());

            toDeposit.setBalance(
                    toDeposit.getBalance() + req.getAmount()
            );

            return accountBalanceService.updateAfterTransaction(toDeposit);
        }).whenComplete(((accountBalance, ex) -> depositLogger.onTransaction(
                accountBalance.getAccount().getAccountNumber(),
                req.getAmount(),
                TransactionTypeEnum.DEPOSIT
        )));
    }

}
