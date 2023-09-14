package com.domrade.controllers;

import com.domrade.deposit.models.Deposit;
import com.domrade.withdrawal.models.Withdrawal;
import com.domrade.withdrawal.service.api.IWithdrawalService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@NoArgsConstructor
@RestController
@RequestMapping("/rest/v1/withdrawal/")
public class WithdrawalController {

    private IWithdrawalService withdrawalService;

    @Autowired
    public WithdrawalController(IWithdrawalService withdrawalService) {
        this.withdrawalService = withdrawalService;
    }

    @PostMapping(value = "addWithdrawal")
    public ResponseEntity<Deposit> addUser(@RequestBody Withdrawal withdrawal) {
        return new ResponseEntity(withdrawalService.saveWithdrawal(withdrawal), HttpStatus.CREATED);
    }

    @GetMapping(value = "getWithdrawalsByUserId")
    public ResponseEntity<Withdrawal> getWithdrawalsByUserId(@RequestParam(name = "userId") Long userId) {
        return new ResponseEntity(withdrawalService.getWithdrawalsByUserId(userId), HttpStatus.OK);
    }
}
