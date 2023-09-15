package com.domrade.balance.service.api;

import com.domrade.balance.models.Balance;

import java.util.List;

public interface IBalanceService {

    public List<Balance> getBalanceUpdatesByUserId(Long userId);
}
