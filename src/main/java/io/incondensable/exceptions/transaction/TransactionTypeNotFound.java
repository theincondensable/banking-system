package io.incondensable.exceptions.transaction;

import io.incondensable.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

/**
 * @author abbas
 */
public class TransactionTypeNotFound extends BusinessException {

    public TransactionTypeNotFound(String message, Object[] args) {
        super(message, args, HttpStatus.NOT_FOUND.value());
    }

}
