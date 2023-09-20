package com.domrade.controllers;

import com.domrade.balance.models.Balance;
import com.domrade.balance.service.api.IBalanceService;
import com.domrade.deposit.models.Deposit;
import com.domrade.deposit.service.api.IDepositService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@CrossOrigin
@NoArgsConstructor
@RestController
@RequestMapping("/rest/v1/deposits/")
public class DepositController {

    private IDepositService depositService;
    private IBalanceService balanceService;

    @Autowired
    public DepositController(IDepositService depositService, IBalanceService balanceService) {
        this.depositService = depositService;
        this.balanceService = balanceService;
    }

    @PostMapping(value = "addDeposit")
    public ResponseEntity<Balance> addDeposit(@RequestBody Deposit deposit) {
        LocalDateTime ldt = LocalDateTime.now();
        deposit.setCreatedDTM(ldt);
        Balance balanceObject;
        // If this is the first deposit there is no existing record to get to determine the total balance
        // Since this is the first deposit the opening balance is 0
        // Create a default balanceObject with a total of 0
        try {
            balanceObject = balanceService.getLatestBalanceByUserId(deposit.getUserId()).get(0);
        } catch (IndexOutOfBoundsException iobe) {
            balanceObject = new Balance(deposit.getUserId(), 0f, 0f, ldt);
        }
        float depositAmount = deposit.getAmount();
        Balance newBalanceObject = new Balance(deposit.getUserId(), deposit.getAmount(), depositAmount + balanceObject.getTotalBalance(), ldt);
        depositService.saveDeposit(deposit);
        Balance returnBalance = balanceService.saveNewBalance(newBalanceObject);

        return new ResponseEntity(returnBalance, HttpStatus.CREATED);
    }

    @GetMapping(value = "getDepositsByUserId")
    public ResponseEntity<Deposit> getDepositsByUserId(@RequestParam(name = "userId") Long userId) {
        return new ResponseEntity(depositService.getDepositsByUserId(userId), HttpStatus.OK);
    }
}
