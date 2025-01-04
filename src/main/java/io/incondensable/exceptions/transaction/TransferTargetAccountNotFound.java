package io.incondensable.exceptions.transaction;

import io.incondensable.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

/**
 * @author abbas
 */
public class TransferTargetAccountNotFound extends BusinessException {

    public TransferTargetAccountNotFound(Long targetUserAccountId) {
        super("transfer.target.account.not.found", new Object[]{targetUserAccountId}, HttpStatus.NOT_FOUND.value());
    }

}
