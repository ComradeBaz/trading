package com.domrade.stocks.service.api;

import com.domrade.stocks.models.StockItem;

import java.util.List;

public interface IListStockService {

    List<StockItem> listStocks();

    List<StockItem> listStocksBySymbol(String symbol);

    List<StockItem> listStocksByExchange(String exchange);

    List<StockItem> listStocksByCountry(String country);

    List<StockItem> listStocksBySymbolExchangeCountry(String symbol, String exchange, String country);
}
