package com.domrade.forexpairs.service.api;

import com.domrade.forexpairs.models.ForexPairItem;
import com.domrade.forexpairs.models.ForexPairRateItem;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface IForexPairsListService {

    public List<ForexPairItem> listForexPairs();

    public List<ForexPairItem> listForexPairsByCurrencyBase(String currency_base);

    public List<ForexPairItem> listForexPairsBySymbol(String symbol);

    ForexPairRateItem listExchangeRateByPair(String pair) throws JsonProcessingException;
}
