package io.incondensable.global.exception.models;


import io.incondensable.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class ForbiddenBusinessException extends BusinessException {

    public ForbiddenBusinessException(String message, Object[] args) {
        super(message, args, HttpStatus.FORBIDDEN.value());
    }

}
