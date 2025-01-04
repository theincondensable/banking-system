package io.incondensable.exceptions.account;

import io.incondensable.global.exception.models.NotFoundBusinessException;

/**
 * @author abbas
 */
public class AccountNotFoundWithAccountNumberException extends NotFoundBusinessException {

    public AccountNotFoundWithAccountNumberException(String accountNumber) {
        super(
                "user.account.not.found.with.account.number",
                new Object[]{accountNumber}
        );
    }

}
