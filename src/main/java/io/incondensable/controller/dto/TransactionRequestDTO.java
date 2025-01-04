package io.incondensable.controller.dto;

import io.incondensable.model.TransactionTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author abbas
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequestDTO {

    @NotNull(message = "{transaction.type.is.null}")
    private TransactionTypeEnum transactionType;

    @NotNull(message = "{transaction.amount.is.null}")
    @Positive(message = "{transaction.amount.is.not.acceptable}")
    private Double amount;

    @NotNull(message = "{transaction.account.holder.user.id.is.null}")
    @Positive(message = "{id.is.null}")
    private Long accountHolderUserId; // This is the Account Holder User's ID in Database

    @Positive(message = "{id.is.null}")
    private Long targetUserId; // It must be filled only for TRANSFER which is the Receiver's User Account

}
