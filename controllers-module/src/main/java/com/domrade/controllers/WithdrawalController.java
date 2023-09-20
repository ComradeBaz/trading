package com.domrade.controllers;

import com.domrade.balance.models.Balance;
import com.domrade.balance.service.api.IBalanceService;
import com.domrade.deposit.models.Deposit;
import com.domrade.withdrawal.models.Withdrawal;
import com.domrade.withdrawal.service.api.IWithdrawalService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@CrossOrigin
@NoArgsConstructor
@RestController
@RequestMapping("/rest/v1/withdrawal/")
public class WithdrawalController {

    private IWithdrawalService withdrawalService;
    private IBalanceService balanceService;

    @Autowired
    public WithdrawalController(IWithdrawalService withdrawalService, IBalanceService balanceService) {
        this.withdrawalService = withdrawalService;
        this.balanceService = balanceService;
    }

    @PostMapping(value = "addWithdrawal")
    public ResponseEntity<Deposit> addUser(@RequestBody Withdrawal withdrawal) {
        LocalDateTime ldt = LocalDateTime.now();
        Balance balanceObject;
        // If this is the first deposit there is no existing record to get to determine the total balance
        // Since this is the first deposit the opening balance is 0
        // Create a default balanceObject with a total of 0
        try {
            balanceObject = balanceService.getLatestBalanceByUserId(withdrawal.getUserId()).get(0);
        } catch (IndexOutOfBoundsException iobe) {
            balanceObject = new Balance(withdrawal.getUserId(), 0f, 0f, ldt);
        }
        float withdrawalAmount = withdrawal.getAmount();
        Balance newBalanceObject = new Balance(withdrawal.getUserId(), -withdrawal.getAmount(), balanceObject.getTotalBalance() - withdrawalAmount, ldt);
        // Set the cretedDTM for the withdrawal
        withdrawal.setCreatedDTM(ldt);
        withdrawalService.saveWithdrawal(withdrawal);
        Balance returnBalance = balanceService.saveNewBalance(newBalanceObject);

        return new ResponseEntity(returnBalance, HttpStatus.CREATED);
    }

    @GetMapping(value = "getWithdrawalsByUserId")
    public ResponseEntity<Withdrawal> getWithdrawalsByUserId(@RequestParam(name = "userId") Long userId) {
        return new ResponseEntity(withdrawalService.getWithdrawalsByUserId(userId), HttpStatus.OK);
    }
}
