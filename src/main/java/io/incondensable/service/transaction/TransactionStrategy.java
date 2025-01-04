package io.incondensable.service.transaction;

import io.incondensable.controller.dto.TransactionRequestDTO;

/**
 * @author abbas
 */
public interface TransactionStrategy {

    void execute(TransactionRequestDTO req);

}
