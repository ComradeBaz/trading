package com.domrade.timeseries.service.api;

import com.domrade.timeseries.models.TimeSeriesResponse;

public interface ITimeSeriesService {

    public TimeSeriesResponse getTimeSeriesResponseBySymbol(String symbol, String interval, String outputsize);
}
