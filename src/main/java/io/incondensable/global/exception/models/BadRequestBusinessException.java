package io.incondensable.global.exception.models;

import io.incondensable.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class BadRequestBusinessException extends BusinessException {

    public BadRequestBusinessException(String message, Object[] args) {
        super(message, args, HttpStatus.BAD_REQUEST.value());
    }

}
