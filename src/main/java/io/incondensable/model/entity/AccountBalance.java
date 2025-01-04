package io.incondensable.model.entity;

import io.incondensable.global.model.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author abbas
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountBalance extends BaseEntity {

    private Double balance;

    @OneToOne
    @JoinColumn(name = "FK_ACCOUNT")
    private UserAccount account;

}
