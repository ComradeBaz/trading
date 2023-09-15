package com.domrade.controllers;

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
    public ResponseEntity<Deposit> addUser(@RequestBody Deposit deposit) {
        LocalDateTime ldt = LocalDateTime.now();
        deposit.setCreatedDTM(ldt);
        return new ResponseEntity(depositService.saveDeposit(deposit), HttpStatus.CREATED);
    }

    @GetMapping(value = "getDepositsByUserId")
    public ResponseEntity<Deposit> getWithdrawalsByUserId(@RequestParam(name = "userId") Long userId) {
        return new ResponseEntity(depositService.getDepositsByUserId(userId), HttpStatus.OK);
    }
}
