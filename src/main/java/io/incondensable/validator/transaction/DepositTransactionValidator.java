package io.incondensable.validator.transaction;

import io.incondensable.repository.AccountBalanceRepository;
import io.incondensable.repository.UserAccountRepository;
import org.springframework.stereotype.Service;

/**
 * @author abbas
 */
@Service
public class DepositTransactionValidator extends AbstractTransactionValidator {

    protected DepositTransactionValidator(UserAccountRepository userAccountRepository, AccountBalanceRepository accountBalanceRepository) {
        super(userAccountRepository, accountBalanceRepository);
    }

}
