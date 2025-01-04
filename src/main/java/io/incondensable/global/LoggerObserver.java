package io.incondensable.global;

import io.incondensable.model.TransactionTypeEnum;

/**
 * @author abbas
 */
public interface LoggerObserver {

    void onTransaction(String accountNumber, Double amount, TransactionTypeEnum transactionType);

}
