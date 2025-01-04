package io.incondensable.controller.dto.account;

import io.incondensable.model.entity.AccountBalance;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author abbas
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccountInformationResponseDTO {

    private String userName;
    private String accountNumber;
    private Double accountBalance;

    public static AccountInformationResponseDTO mapFromEntity(AccountBalance accountBalance) {
        return new AccountInformationResponseDTO(
                accountBalance.getAccount().getName(),
                accountBalance.getAccount().getAccountNumber(),
                accountBalance.getBalance()
        );
    }

}
