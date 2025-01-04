package io.incondensable.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author abbas
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccountBalanceResponseDTO {

    private String accountNumber;
    private Double balance;

}
