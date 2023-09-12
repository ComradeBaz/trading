package com.domrade.timeseries.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TimeSeriesValue {

    private String datetime;
    private Double open;
    private Double high;
    private Double low;
    private Double close;
    private String volume;
}

