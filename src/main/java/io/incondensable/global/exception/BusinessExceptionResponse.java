package io.incondensable.global.exception;

/**
 * @author abbas
 */
public record BusinessExceptionResponse(String message, String status, Integer statusCode) {
}
