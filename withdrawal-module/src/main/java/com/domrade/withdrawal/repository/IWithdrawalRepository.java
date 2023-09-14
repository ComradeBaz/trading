package com.domrade.withdrawal.repository;

import com.domrade.withdrawal.models.Withdrawal;
import lombok.With;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IWithdrawalRepository extends JpaRepository<Withdrawal, Long> {

    @Query(value = "SELECT * FROM withdrawals_table w WHERE w.user_id = :userId", nativeQuery = true)
    public Optional<List<Withdrawal>> getWithdrawalsByUserId(Long userId);
}
