package io.incondensable.global.exception;

import lombok.Getter;

import java.util.Locale;

/**
 * @author abbas
 */
@Getter
public class BusinessException extends RuntimeException {

    private final String message;
    private final Object[] args;
    private final Locale locale;
    private final int httpStatusCode;

    public BusinessException(String message, Object[] args, int httpStatusCode) {
        this.message = message;
        this.args = args;
        this.locale = Locale.getDefault();
        this.httpStatusCode = httpStatusCode;
    }

}
