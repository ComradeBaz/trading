package com.domrade.balance.service.impl;

import com.domrade.balance.models.Balance;
import com.domrade.balance.repository.IBalanceRepository;
import com.domrade.balance.service.api.IBalanceService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class BalanceService implements IBalanceService {

    private IBalanceRepository balanceRepository;

    @Autowired
    public BalanceService(IBalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }

    @Override
    public List<Balance> getBalanceUpdatesByUserId(Long userId) {
        List<Balance> returnList = new ArrayList<>();
        return balanceRepository.getBalanceUpdatesByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException());
    }
}
