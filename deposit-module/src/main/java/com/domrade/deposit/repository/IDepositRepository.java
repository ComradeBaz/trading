package com.domrade.deposit.repository;

import com.domrade.deposit.models.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IDepositRepository extends JpaRepository<Deposit, Long> {
    @Query(value = "SELECT * FROM deposits_table d WHERE d.user_id = :userId", nativeQuery = true)
    public Optional<List<Deposit>> getDepositsByUserId(Long userId);
}
