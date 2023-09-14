package com.domrade.withdrawal.service.impl;

import com.domrade.withdrawal.models.Withdrawal;
import com.domrade.withdrawal.repository.IWithdrawalRepository;
import com.domrade.withdrawal.service.api.IWithdrawalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WithdrawalService implements IWithdrawalService {

    private IWithdrawalRepository withdrawalRepository;

    @Autowired
    public WithdrawalService(IWithdrawalRepository withdrawalRepository) {
        this.withdrawalRepository = withdrawalRepository;
    }
    @Override
    public Withdrawal saveWithdrawal(Withdrawal withdrawal) {
        return withdrawalRepository.save(withdrawal);
    }
}
