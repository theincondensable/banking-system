package io.incondensable.controller.dto.admin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author abbas
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountCreateRequestDTO {

    @NotNull
    @Size(min = 3, max = 100, message = "{user.account.needs.user.name}")
    private String userName;

}
