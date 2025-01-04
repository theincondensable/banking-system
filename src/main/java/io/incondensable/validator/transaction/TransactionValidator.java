package io.incondensable.validator.transaction;

import io.incondensable.controller.dto.TransactionRequestDTO;

/**
 * @author abbas
 */
public interface TransactionValidator {

    void validateRequest(TransactionRequestDTO req);

}
