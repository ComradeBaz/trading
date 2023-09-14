package com.domrade.deposit.service.api;

import com.domrade.deposit.models.Deposit;
import org.springframework.stereotype.Repository;

@Repository
public interface IDepositService {

    public Deposit saveDeposit(Deposit deposit);
}
