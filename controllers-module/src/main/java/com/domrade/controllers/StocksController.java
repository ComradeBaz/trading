package com.domrade.controllers;

import com.domrade.http.service.api.IHttpService;
import com.domrade.stocks.models.StockItem;
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

    private final String API = "stocks";

    @Value("${base.url.stocks}")
    private String baseUrlStocks;

    @Autowired
    private IHttpService httpService;

    @Autowired
    private IJsonConverterService jsonConverterService;

    @GetMapping(value = "/listStocks")
    public ResponseEntity<String> listStocks() {
        String response = httpService.makeConnectionAndGetResponse(baseUrlStocks + API, HttpMethod.GET);
        List<StockItem> returnList = new ArrayList<>();
        returnList = jsonConverterService.parseJson(response, StockItem.class, "data");
        return new ResponseEntity(returnList, HttpStatus.OK);
    }

    @GetMapping(value = "/listStocksBySymbol")
    public ResponseEntity<String> listStocksBySymbol(@RequestParam(name="symbol") String symbol) {
        String response = httpService.makeConnectionAndGetResponse(baseUrlStocks + API + "?symbol=" + symbol, HttpMethod.GET);
        List<StockItem> returnList = new ArrayList<>();
        returnList = jsonConverterService.parseJson(response, StockItem.class, "data");
        return new ResponseEntity(returnList, HttpStatus.OK);
    }

    @GetMapping(value = "/listStocksByExchange")
    public ResponseEntity<String> listStocksByExchange(@RequestParam(name="exchange") String exchange) {
        String response = httpService.makeConnectionAndGetResponse(baseUrlStocks + API + "?exchange=" + exchange, HttpMethod.GET);
        List<StockItem> returnList = new ArrayList<>();
        returnList = jsonConverterService.parseJson(response, StockItem.class, "data");
        return new ResponseEntity(returnList, HttpStatus.OK);
    }

    @GetMapping(value = "/listStocksByCountry")
    public ResponseEntity<String> listStocksByCountry(@RequestParam(name="country") String country) {
        String response = httpService.makeConnectionAndGetResponse(baseUrlStocks + API + "?country=" + country, HttpMethod.GET);
        List<StockItem> returnList = new ArrayList<>();
        returnList = jsonConverterService.parseJson(response, StockItem.class, "data");
        return new ResponseEntity(returnList, HttpStatus.OK);
    }

    @GetMapping(value = "/listStocksBySymbolExchangeCountry")
    public ResponseEntity<String> listStocksBySymbolExchangeCountry(
            @RequestParam(name="symbol") String symbol, @RequestParam(name="exchange") String exchange, @RequestParam(name="country") String country) throws JsonProcessingException {
        String response = httpService.makeConnectionAndGetResponse(baseUrlStocks + API + "?symbol=" + symbol + "&exchange=" + exchange + "&country=" + country, HttpMethod.GET);
        List<StockItem> returnList = jsonConverterService.parseJson(response, StockItem.class, "data");
        return new ResponseEntity(returnList.get(0), HttpStatus.OK);
    }
}
