package com.domrade.balance.repository;

import com.domrade.balance.models.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IBalanceRepository extends JpaRepository<Balance, Long> {
    @Query(value = "SELECT * FROM balance_table b WHERE b.user_id = :userId", nativeQuery = true)
    public Optional<List<Balance>> getBalanceUpdatesByUserId(Long userId);

}
