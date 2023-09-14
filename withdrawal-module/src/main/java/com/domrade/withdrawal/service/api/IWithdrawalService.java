package com.domrade.withdrawal.service.api;

import com.domrade.withdrawal.models.Withdrawal;
import lombok.With;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IWithdrawalService {

    public Withdrawal saveWithdrawal(Withdrawal withdrawal);

    public List<Withdrawal> getWithdrawalsByUserId(Long userId);
}
