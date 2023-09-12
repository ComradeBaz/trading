package com.domrade.stocks.service.impl;

import com.domrade.http.service.api.IHttpService;
import com.domrade.stocks.models.StockItem;
import com.domrade.stocks.service.api.IListStockService;
import com.domrade.utilities.service.api.IJsonConverterService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@NoArgsConstructor
public class ListStockService implements IListStockService {

    private final String API = "stocks";

    private IHttpService httpService;

    private IJsonConverterService jsonConverterService;

    @Value("${base.url.stocks}")
    private String baseUrlStocks;

    @Autowired
    public ListStockService(IHttpService httpService, IJsonConverterService jsonConverterService) {
        this.httpService = httpService;
        this.jsonConverterService = jsonConverterService;
    }

    @Override
    public List<StockItem> listStocks() {
        String response = httpService.makeConnectionAndGetResponse(baseUrlStocks + API, HttpMethod.GET);
        return jsonConverterService.parseJson(response, StockItem.class, "data");
    }

    @Override
    public List<StockItem> listStocksBySymbol(String symbol) {
        String response = httpService.makeConnectionAndGetResponse(baseUrlStocks + API + "?symbol=" + symbol, HttpMethod.GET);
        return jsonConverterService.parseJson(response, StockItem.class, "data");
    }

    @Override
    public List<StockItem> listStocksByExchange(String exchange) {
        String response = httpService.makeConnectionAndGetResponse(baseUrlStocks + API + "?exchange=" + exchange, HttpMethod.GET);
        return jsonConverterService.parseJson(response, StockItem.class, "data");
    }

    @Override
    public List<StockItem> listStocksByCountry(String country) {
        String response = httpService.makeConnectionAndGetResponse(baseUrlStocks + API + "?country=" + country, HttpMethod.GET);
        return jsonConverterService.parseJson(response, StockItem.class, "data");
    }

    @Override
    public List<StockItem> listStocksBySymbolExchangeCountry(String symbol, String exchange, String country) {
        String response = httpService.makeConnectionAndGetResponse(baseUrlStocks + API + "?symbol=" + symbol + "&exchange=" + exchange + "&country=" + country, HttpMethod.GET);
        return jsonConverterService.parseJson(response, StockItem.class, "data");
    }
}
