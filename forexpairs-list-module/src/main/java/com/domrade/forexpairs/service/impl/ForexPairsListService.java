package com.domrade.forexpairs.service.impl;

import com.domrade.forexpairs.models.ForexPairItem;
import com.domrade.forexpairs.service.api.IForexPairsListService;
import com.domrade.http.service.api.IHttpService;
import com.domrade.utilities.service.api.IJsonConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForexPairsListService implements IForexPairsListService {

    private final String API = "forex_pairs";

    private IHttpService httpService;

    private IJsonConverterService jsonConverterService;

    @Value("${base.url}")
    private String baseUrl;

    @Autowired
    public ForexPairsListService(IHttpService httpService, IJsonConverterService jsonConverterService) {
        this.httpService = httpService;
        this.jsonConverterService = jsonConverterService;
    }

    @Override
    public List<ForexPairItem> listForexPairs() {
        String url = baseUrl + API;
        String response = httpService.makeConnectionAndGetResponse(url, HttpMethod.GET);
        return jsonConverterService.parseJson(response, ForexPairItem.class, "data");
    }

    @Override
    public List<ForexPairItem> listForexPairsByCurrencyBase(String currency_base) {
        String url = baseUrl
                + API
                + "?currency_base="
                + currency_base;
        String response = httpService.makeConnectionAndGetResponse(url, HttpMethod.GET);
        return jsonConverterService.parseJson(response, ForexPairItem.class, "data");
    }

    @Override
    public List<ForexPairItem> listForexPairsBySymbol(String symbol) {
        String url = baseUrl
                + API
                + "?symbol="
                + symbol;
        String response = httpService.makeConnectionAndGetResponse(url, HttpMethod.GET);
        return jsonConverterService.parseJson(response, ForexPairItem.class, "data");
    }
}
