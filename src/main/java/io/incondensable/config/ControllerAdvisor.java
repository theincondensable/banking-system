package io.incondensable.config;

import io.incondensable.global.exception.BaseRequestFieldsError;
import io.incondensable.global.exception.BusinessException;
import io.incondensable.global.exception.BusinessExceptionResponse;
import io.incondensable.global.exception.RequestFieldsError;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.*;

@ControllerAdvice
public class ControllerAdvisor {

    private final MessageSource messageSource;

    public ControllerAdvisor(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(BusinessException ex) {
        return ResponseEntity.status(ex.getHttpStatusCode()).body(
                new BusinessExceptionResponse(
                        messageSource.getMessage(ex.getMessage(), ex.getArgs(), Locale.getDefault()),
                        HttpStatus.resolve(ex.getHttpStatusCode()).getReasonPhrase(),
                        ex.getHttpStatusCode()
                )
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseRequestFieldsError> validationException(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<RequestFieldsError> errors = new ArrayList<>(fieldErrors.size());
        for (FieldError fieldError : fieldErrors) {
            String field = fieldError.getField();
            if (field.contains(".")) field = field.split("\\.")[1];
            errors.add(new RequestFieldsError(fieldError.getDefaultMessage(), field));
        }
        BaseRequestFieldsError fieldsError = new BaseRequestFieldsError(errors);
        return ResponseEntity.of(Optional.of(fieldsError));
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleLocally(NoSuchElementException exception) {
        System.out.println(Arrays.toString(exception.fillInStackTrace().getStackTrace()));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Arrays.toString(exception.fillInStackTrace().getStackTrace()));
    }

}