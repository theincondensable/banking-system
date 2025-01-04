package io.incondensable.global.exception.models;

import io.incondensable.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class NotFoundBusinessException extends BusinessException {

    public NotFoundBusinessException(String message, Object[] args) {
        super(message, args, HttpStatus.NOT_FOUND.value());
    }

}
