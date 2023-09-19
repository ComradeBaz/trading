package com.domrade.balance.service.impl;

import com.domrade.balance.models.Balance;
import com.domrade.balance.repository.IBalanceRepository;
import com.domrade.balance.service.api.IBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BalanceService implements IBalanceService {

    private IBalanceRepository balanceRepository;

    @Autowired
    public BalanceService(IBalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }

    @Override
    public List<Balance> getBalanceUpdatesByUserId(Long userId) {
        return balanceRepository.getBalanceUpdatesByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    public Balance saveNewBalance(Balance balance) {
        return this.balanceRepository.save(balance);
    }

    @Override
    public List<Balance> getLatestBalance(Long userId, Pageable pageable) {
        return balanceRepository.getLatestBalanceByUserId(userId, pageable)
                .orElseThrow(() -> new EntityNotFoundException());
    }
}
