package com.domrade.deposit.service.impl;

import com.domrade.deposit.models.Deposit;
import com.domrade.deposit.repository.IDepositRepository;
import com.domrade.deposit.service.api.IDepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepositService implements IDepositService {

    private IDepositRepository depositRepository;

    @Autowired
    public DepositService(IDepositRepository depositRepository) {
        this.depositRepository = depositRepository;
    }

    @Override
    public Deposit saveDeposit(Deposit deposit) {
        return depositRepository.save(deposit);
    }
}
