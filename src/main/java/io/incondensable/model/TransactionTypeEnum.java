package io.incondensable.model;

import io.incondensable.exceptions.transaction.TransactionTypeNotFound;

import java.util.Arrays;

/**
 * @author abbas
 */
public enum TransactionTypeEnum {

    DEPOSIT,
    WITHDRAWAL,
    TRANSFER;

    public static TransactionTypeEnum findType(TransactionTypeEnum type) {
        return Arrays.stream(TransactionTypeEnum.values())
                .filter(type::equals)
                .findFirst()
                .orElseThrow(() -> {
                    throw new TransactionTypeNotFound("transaction.type.not.found", new Object[]{type.toString()});
                });
    }
}
