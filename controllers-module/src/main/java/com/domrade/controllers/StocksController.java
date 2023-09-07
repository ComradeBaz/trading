package com.domrade.controllers;

import com.domrade.http.service.api.IHttpService;
import com.domrade.stocks.models.StockItem;
import com.domrade.utilities.service.api.IJsonConverterService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@RestController
@RequestMapping("/rest/v1/stocks/")
public class StocksController {

    private final String API = "stocks";

    @Value("${base.url}")
    private String baseUrl;

    @Autowired
    private IHttpService httpService;

    @Autowired
    private IJsonConverterService jsonConverterService;

    @GetMapping(value = "/listStocks")
    public ResponseEntity<String> listStocks() {
        String response = httpService.makeConnectionAndGetResponse(baseUrl + API, HttpMethod.GET);
        List<StockItem> returnList = new ArrayList<>();
        returnList = jsonConverterService.parseJson(response, StockItem.class, "data");
        return new ResponseEntity(returnList, HttpStatus.OK);
    }

    @GetMapping(value = "/listStocksBySymbol")
    public ResponseEntity<String> listStocksBySymbol(@RequestParam(name="symbol") String symbol) {
        String response = httpService.makeConnectionAndGetResponse(baseUrl + API + "?symbol=" + symbol, HttpMethod.GET);
        List<StockItem> returnList = new ArrayList<>();
        returnList = jsonConverterService.parseJson(response, StockItem.class, "data");
        return new ResponseEntity(returnList, HttpStatus.OK);
    }

    @GetMapping(value = "/listStocksByExchange")
    public ResponseEntity<String> listStocksByExchange(@RequestParam(name="exchange") String exchange) {
        String response = httpService.makeConnectionAndGetResponse(baseUrl + API + "?exchange=" + exchange, HttpMethod.GET);
        List<StockItem> returnList = new ArrayList<>();
        returnList = jsonConverterService.parseJson(response, StockItem.class, "data");
        return new ResponseEntity(returnList, HttpStatus.OK);
    }

    @GetMapping(value = "/listStocksByCountry")
    public ResponseEntity<String> listStocksByCountry(@RequestParam(name="country") String country) {
        String response = httpService.makeConnectionAndGetResponse(baseUrl + API + "?country=" + country, HttpMethod.GET);
        List<StockItem> returnList = new ArrayList<>();
        returnList = jsonConverterService.parseJson(response, StockItem.class, "data");
        return new ResponseEntity(returnList, HttpStatus.OK);
    }
}
