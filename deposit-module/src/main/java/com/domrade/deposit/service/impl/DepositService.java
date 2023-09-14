package com.domrade.deposit.service.impl;

import com.domrade.deposit.models.Deposit;
import com.domrade.deposit.repository.IDepositRepository;
import com.domrade.deposit.service.api.IDepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Deposit> getDepositsByUserId(Long userId) {
        List<Deposit> returnList = new ArrayList<>();
        return depositRepository.getDepositsByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException());
    }
}
