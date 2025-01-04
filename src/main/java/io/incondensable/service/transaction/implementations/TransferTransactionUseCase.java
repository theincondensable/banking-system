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
public class TransferTransactionUseCase implements TransactionStrategy {

    private final LoggerObserver transferLogger;
    private final AccountBalanceService accountBalanceService;

    public TransferTransactionUseCase(LoggerObserver transferLogger, AccountBalanceService accountBalanceService) {
        this.transferLogger = transferLogger;
        this.accountBalanceService = accountBalanceService;
    }

    @Override
    public void execute(TransactionRequestDTO req) {
        CompletableFuture.supplyAsync(() -> {
            AccountBalance from = accountBalanceService.getByUserAccountId(req.getAccountHolderUserId());
            AccountBalance to = accountBalanceService.getByUserAccountId(req.getTargetUserId());

            from.setBalance(
                    from.getBalance() - req.getAmount()
            );
            to.setBalance(
                    to.getBalance() + req.getAmount()
            );

            AccountBalance fromUpdatedBalance = accountBalanceService.updateAfterTransaction(from);
            accountBalanceService.updateAfterTransaction(to);

            return fromUpdatedBalance;
        }).whenComplete(((accountBalance, ex) -> transferLogger.onTransaction(
                accountBalance.getAccount().getAccountNumber(),
                req.getAmount(),
                TransactionTypeEnum.TRANSFER
        )));
    }

}
