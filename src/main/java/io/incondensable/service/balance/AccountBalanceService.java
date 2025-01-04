package io.incondensable.service.balance;

import io.incondensable.controller.dto.account.AccountInformationResponseDTO;
import io.incondensable.exceptions.account.AccountNotFoundWithAccountNumberException;
import io.incondensable.model.entity.AccountBalance;
import io.incondensable.model.entity.UserAccount;
import io.incondensable.repository.AccountBalanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author abbas
 */
@Service
@RequiredArgsConstructor
public class AccountBalanceService {

    private final AccountBalanceRepository accountBalanceRepository;

    public AccountInformationResponseDTO getAccountInformation(String accountNumber) {
        return accountBalanceRepository.findAccountBalanceByAccountNumber(accountNumber)
                .map(AccountInformationResponseDTO::mapFromEntity)
                .orElseThrow(() -> {
                    throw new AccountNotFoundWithAccountNumberException(accountNumber);
                });
    }

    public AccountBalance getByUserAccountId(Long userAccountId) {
        return accountBalanceRepository.findAccountBalanceByUserAccount(userAccountId)
                .get();
    }

    public AccountBalance createUserAccountBalance(UserAccount userAccount) {
        AccountBalance entityToSave = new AccountBalance();

        entityToSave.setBalance(1000.0);
        entityToSave.setAccount(userAccount);

        return accountBalanceRepository.save(entityToSave);
    }

    public AccountBalance updateAfterTransaction(AccountBalance accountBalance) {
        return accountBalanceRepository.save(accountBalance);
    }

}
