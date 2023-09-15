package com.domrade.controllers;

import com.domrade.balance.service.api.IBalanceService;
import com.domrade.deposit.models.Deposit;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@CrossOrigin
@NoArgsConstructor
@RestController
@RequestMapping("/rest/v1/balance/")
public class BalanceController {

    private IBalanceService balanceService;

    @Autowired
    public BalanceController(IBalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @GetMapping(value = "getBalanceUpdatesByUserId")
    public ResponseEntity<Deposit> getWithdrawalsByUserId(@RequestParam(name = "userId") Long userId) {
        return new ResponseEntity(balanceService.getBalanceUpdatesByUserId(userId), HttpStatus.OK);
    }
}
