package io.incondensable.repository;

import io.incondensable.model.entity.AccountBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author abbas
 */
@Repository
public interface AccountBalanceRepository extends JpaRepository<AccountBalance, Long> {

    @Query("select ab from AccountBalance ab where ab.account.accountNumber = :accountNumber")
    Optional<AccountBalance> findAccountBalanceByAccountNumber(@Param("accountNumber") String accountNumber);

    @Query("select ab from AccountBalance ab where ab.account.id = :holderAccountId")
    Optional<AccountBalance> findAccountBalanceByUserAccount(@Param("holderAccountId") Long holderAccountId);

}
