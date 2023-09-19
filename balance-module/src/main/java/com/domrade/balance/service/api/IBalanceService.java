package com.domrade.balance.service.api;

import com.domrade.balance.models.Balance;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IBalanceService {

    public List<Balance> getBalanceUpdatesByUserId(Long userId);

    public Balance saveNewBalance(Balance balance);

    public List<Balance> getLatestBalance(Long userId, Pageable pageable);
}
