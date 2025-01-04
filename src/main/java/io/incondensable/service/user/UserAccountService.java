package io.incondensable.service.user;

import io.incondensable.controller.dto.admin.UserAccountAdminResponseDTO;
import io.incondensable.controller.dto.admin.UserAccountCreateRequestDTO;
import io.incondensable.exceptions.account.AccountNotFoundWithAccountNumberException;
import io.incondensable.global.exception.models.NotFoundBusinessException;
import io.incondensable.model.entity.UserAccount;
import io.incondensable.repository.UserAccountRepository;
import io.incondensable.service.balance.AccountBalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author abbas
 */
@Service
@RequiredArgsConstructor
public class UserAccountService {

    private final AccountBalanceService accountBalanceService;
    private final UserAccountRepository userAccountRepository;

    public List<UserAccountAdminResponseDTO> getAllUsers() {
        return userAccountRepository.findAll().stream()
                .map(UserAccountAdminResponseDTO::mapFromEntity)
                .collect(Collectors.toList());
    }

    public UserAccountAdminResponseDTO getUserAccountById(Long userAccountId) {
        UserAccount userAccount = userAccountRepository.findById(userAccountId).orElseThrow(
                () -> {
                    throw new NotFoundBusinessException(
                            "user.account.not.found",
                            new Object[]{userAccountId}
                    );
                }
        );

        return UserAccountAdminResponseDTO.mapFromEntity(userAccount);
    }

    public UserAccountAdminResponseDTO getUserAccountByAccountNumber(String accountNumber) {
        UserAccount userAccount = userAccountRepository.findByAccountNumber(accountNumber).orElseThrow(
                () -> {
                    throw new AccountNotFoundWithAccountNumberException(accountNumber);
                }
        );

        return UserAccountAdminResponseDTO.mapFromEntity(userAccount);
    }

    @Transactional
    public UserAccountAdminResponseDTO createUserAccount(UserAccountCreateRequestDTO req) {
        UserAccount entityToSave = new UserAccount();

        entityToSave.setName(req.getUserName());
        entityToSave.setAccountNumber(UUID.randomUUID().toString());

        UserAccount userAccount = userAccountRepository.save(entityToSave);
        accountBalanceService.createUserAccountBalance(userAccount);

        return UserAccountAdminResponseDTO.mapFromEntity(userAccount);
    }

}
