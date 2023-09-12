package com.domrade.controllers;

import com.domrade.http.service.api.IHttpService;
import com.domrade.stocks.models.StockItem;
import com.domrade.stocks.service.api.IListStockService;
import com.domrade.utilities.service.api.IJsonConverterService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@NoArgsConstructor
@RestController
@RequestMapping("/rest/v1/stocks/")
public class StocksController {

    @Autowired
    private IListStockService listStockService;

    @GetMapping(value = "/listStocks")
    public ResponseEntity<String> listStocks() {
        List<StockItem> returnList = listStockService.listStocks();
        return new ResponseEntity(returnList, HttpStatus.OK);
    }

    @GetMapping(value = "/listStocksBySymbol")
    public ResponseEntity<String> listStocksBySymbol(@RequestParam(name="symbol") String symbol) {
        List<StockItem> returnList = listStockService.listStocksBySymbol(symbol);
        return new ResponseEntity(returnList, HttpStatus.OK);
    }

    @GetMapping(value = "/listStocksByExchange")
    public ResponseEntity<String> listStocksByExchange(@RequestParam(name="exchange") String exchange) {
        List<StockItem> returnList = listStockService.listStocksByExchange(exchange);
        return new ResponseEntity(returnList, HttpStatus.OK);
    }

    @GetMapping(value = "/listStocksByCountry")
    public ResponseEntity<String> listStocksByCountry(@RequestParam(name="country") String country) {
        List<StockItem> returnList = listStockService.listStocksByCountry(country);
        return new ResponseEntity(returnList, HttpStatus.OK);
    }

    @GetMapping(value = "/listStocksBySymbolExchangeCountry")
    public ResponseEntity<String> listStocksBySymbolExchangeCountry(
            @RequestParam(name="symbol") String symbol, @RequestParam(name="exchange") String exchange, @RequestParam(name="country") String country) throws JsonProcessingException {
        List<StockItem> returnList = listStockService.listStocksBySymbolExchangeCountry(symbol, exchange, country);
        return new ResponseEntity(returnList.get(0), HttpStatus.OK);
    }
}
