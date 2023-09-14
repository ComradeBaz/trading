package com.domrade.deposit.service.api;

import com.domrade.deposit.models.Deposit;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDepositService {

    public Deposit saveDeposit(Deposit deposit);

    public List<Deposit> getDepositsByUserId(Long userId);
}
