package com.domrade.controllers;

import com.domrade.balance.models.Balance;
import com.domrade.balance.service.api.IBalanceService;
import com.domrade.deposit.models.Deposit;
import com.domrade.deposit.service.api.IDepositService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        try {
            Pageable pageable = PageRequest.of(0,1, Sort.Direction.DESC,"updatedDTM");
            balanceObject = balanceService.getLatestBalance(deposit.getUserId(), pageable).get(0);
        } catch (IndexOutOfBoundsException iobe) {
            balanceObject = new Balance(deposit.getUserId(), 0f, 0f, ldt);
        }
        float balanceAmount = deposit.getAmount();
        //balanceObject.setAmount(deposit.getAmount());
        //balanceObject.setTotal(balanceAmount + balanceObject.getTotal());
        //balanceObject.setUpdatedDTM(ldt);
        Balance newBalanceObject = new Balance(deposit.getUserId(), deposit.getAmount(), balanceAmount + balanceObject.getTotal(), ldt);
        depositService.saveDeposit(deposit);
        Balance returnBalance = balanceService.saveNewBalance(newBalanceObject);

        return new ResponseEntity(returnBalance, HttpStatus.CREATED);
    }

    @GetMapping(value = "getDepositsByUserId")
    public ResponseEntity<Deposit> getDepositsByUserId(@RequestParam(name = "userId") Long userId) {
        return new ResponseEntity(depositService.getDepositsByUserId(userId), HttpStatus.OK);
    }
}
