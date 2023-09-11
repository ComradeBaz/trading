package com.domrade.timeseries.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TimeSeriesValue {

    private String datetime;
    private String open;
    private String high;
    private String low;
    private String close;
    private String volume;
}

