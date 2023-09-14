package com.domrade.deposit.repository;

import com.domrade.deposit.models.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDepositRepository extends JpaRepository<Deposit, Long> {
}
