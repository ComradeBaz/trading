package com.domrade.withdrawal.service.impl;

import com.domrade.withdrawal.models.Withdrawal;
import com.domrade.withdrawal.repository.IWithdrawalRepository;
import com.domrade.withdrawal.service.api.IWithdrawalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Withdrawal> getWithdrawalsByUserId(Long userId) {
        List<Withdrawal> returnList = new ArrayList<>();
        return withdrawalRepository.getWithdrawalsByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException());
    }
}
