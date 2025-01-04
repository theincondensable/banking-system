package io.incondensable.controller.dto.admin;

import io.incondensable.model.entity.UserAccount;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * @author abbas
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountAdminResponseDTO {

    private Long userAccountId;
    private String userName;
    private String accountNumber;
    private Instant createdAt;

    public static UserAccountAdminResponseDTO mapFromEntity(UserAccount entity) {
        return new UserAccountAdminResponseDTO(
                entity.getId(),
                entity.getName(),
                entity.getAccountNumber(),
                entity.getCreatedAt()
        );
    }

}
