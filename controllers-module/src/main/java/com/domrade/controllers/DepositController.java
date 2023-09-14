package com.domrade.controllers;

import com.domrade.deposit.models.Deposit;
import com.domrade.deposit.service.api.IDepositService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@NoArgsConstructor
@RestController
@RequestMapping("/rest/v1/deposit/")
public class DepositController {

    private IDepositService depositService;

    @Autowired
    public DepositController(IDepositService depositService) {
        this.depositService = depositService;
    }

    @PostMapping(value = "addDeposit")
    public ResponseEntity<Deposit> addUser(@RequestBody Deposit deposit) {
        return new ResponseEntity(depositService.saveDeposit(deposit), HttpStatus.CREATED);
    }
}
