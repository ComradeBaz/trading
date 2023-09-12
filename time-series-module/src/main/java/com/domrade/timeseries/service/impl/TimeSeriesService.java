package com.domrade.timeseries.service.impl;

import com.domrade.http.service.api.IHttpService;
import com.domrade.timeseries.models.TimeSeriesMetaData;
import com.domrade.timeseries.models.TimeSeriesResponse;
import com.domrade.timeseries.models.TimeSeriesValue;
import com.domrade.timeseries.service.api.ITimeSeriesService;
import com.domrade.utilities.service.api.IJsonConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeSeriesService implements ITimeSeriesService {

    private IHttpService httpService;
    private IJsonConverterService jsonConverterService;

    @Value("${base.url.timeseries}")
    private String baseUrlTimeseries;

    @Autowired
    public TimeSeriesService(IHttpService httpService, IJsonConverterService jsonConverterService) {
        this.httpService = httpService;
        this.jsonConverterService = jsonConverterService;
    }

    @Override
    public TimeSeriesResponse getTimeSeriesResponseBySymbol(String symbol, String interval, String outputsize) {
        String url = baseUrlTimeseries
                + "?symbol="
                + symbol
                + "&interval="
                + interval
                + "&outputsize="
                + outputsize;
        String response = httpService.makeConnectionAndGetResponse(url, HttpMethod.GET);
        List<TimeSeriesMetaData> metaDataList = jsonConverterService.parseJson(response, TimeSeriesMetaData.class, "meta");
        TimeSeriesMetaData metaData = metaDataList.get(0);
        List<TimeSeriesValue> timeSeriesValuesList = jsonConverterService.parseJson(response, TimeSeriesValue.class, "values");

        return new TimeSeriesResponse(metaData, timeSeriesValuesList);
    }
}
