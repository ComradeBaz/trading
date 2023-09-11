package com.domrade.timeseries.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class TimeSeriesMetaData {

    private String symbol;
    private String interval;
    private String currency;
    private String exchange_timezone;
    private String exchange;
    private String mic_code;
    private String type;
}