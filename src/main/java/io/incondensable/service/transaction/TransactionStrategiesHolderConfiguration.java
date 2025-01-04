package io.incondensable.service.transaction;

import io.incondensable.model.TransactionTypeEnum;
import io.incondensable.service.transaction.implementations.DepositTransactionUseCase;
import io.incondensable.service.transaction.implementations.TransferTransactionUseCase;
import io.incondensable.service.transaction.implementations.WithdrawTransactionUseCase;
import io.incondensable.validator.transaction.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @author abbas
 */
@Configuration
public class TransactionStrategiesHolderConfiguration {

    @Bean
    public Map<TransactionTypeEnum, TransactionStrategy> transactionStrategies(
            DepositTransactionUseCase depositTransactionUseCase,
            WithdrawTransactionUseCase withdrawTransactionUseCase,
            TransferTransactionUseCase transferTransactionUseCase
    ) {
        return Map.of(
                TransactionTypeEnum.DEPOSIT, depositTransactionUseCase,
                TransactionTypeEnum.WITHDRAWAL, withdrawTransactionUseCase,
                TransactionTypeEnum.TRANSFER, transferTransactionUseCase
        );
    }

    @Bean
    public Map<TransactionTypeEnum, TransactionValidator> transactionValidators(
            DepositTransactionValidator depositTransactionValidator,
            WithdrawTransactionValidator withdrawTransactionValidator,
            TransferTransactionValidator transferTransactionValidator
    ) {
        return Map.of(
                TransactionTypeEnum.DEPOSIT, depositTransactionValidator,
                TransactionTypeEnum.WITHDRAWAL, withdrawTransactionValidator,
                TransactionTypeEnum.TRANSFER, transferTransactionValidator
        );
    }

}
