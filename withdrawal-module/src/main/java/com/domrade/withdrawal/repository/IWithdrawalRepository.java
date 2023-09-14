package com.domrade.withdrawal.repository;

import com.domrade.withdrawal.models.Withdrawal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IWithdrawalRepository extends JpaRepository<Withdrawal, Long> {
}
