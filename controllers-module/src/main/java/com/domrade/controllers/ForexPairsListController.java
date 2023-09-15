package com.domrade.controllers;

import com.domrade.forexpairs.models.ForexPairItem;
import com.domrade.forexpairs.models.ForexPairRateItem;
import com.domrade.forexpairs.service.api.IForexPairsListService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@NoArgsConstructor
@RestController
@RequestMapping("/rest/v1/forexPairsList/")
public class ForexPairsListController {

    private IForexPairsListService forexPairsService;

    @Autowired
    public ForexPairsListController(IForexPairsListService forexPairsService) {
        this.forexPairsService = forexPairsService;
    }

    @GetMapping (value = "/listForexPairs")
    public ResponseEntity<List<ForexPairItem>> listForexPairs() {
        List<ForexPairItem> returnList = forexPairsService.listForexPairs();
        return new ResponseEntity<>(returnList, HttpStatus.OK);
    }

    @GetMapping (value = "/listForexPairsByCurrencyBase")
    public ResponseEntity<List<ForexPairItem>> listForexPairsByCurrencyBase(@RequestParam("currencyBase") String currencyBase) {
        List<ForexPairItem> returnList = forexPairsService.listForexPairsByCurrencyBase(currencyBase);
        return new ResponseEntity<>(returnList, HttpStatus.OK);
    }

    @GetMapping (value = "/listForexPairsBySymbol")
    public ResponseEntity<List<ForexPairItem>> listForexPairsBySymbol(@RequestParam("symbol") String symbol) {
        List<ForexPairItem> returnList = forexPairsService.listForexPairsBySymbol(symbol);
        return new ResponseEntity<>(returnList, HttpStatus.OK);
    }

    @GetMapping (value = "/listForexPairsRateBySymbol")
    public ResponseEntity<ForexPairRateItem> listForexPairsRateBySymbol(@RequestParam("symbol") String symbol) throws JsonProcessingException {
        ForexPairRateItem item = forexPairsService.listExchangeRateByPair(symbol);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }
}
