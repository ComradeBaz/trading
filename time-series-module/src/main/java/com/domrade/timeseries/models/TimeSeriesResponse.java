package com.domrade.timeseries.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
public class TimeSeriesResponse {

    private TimeSeriesMetaData meta;
    private List<TimeSeriesValue> values;

    public TimeSeriesResponse(TimeSeriesMetaData meta, List<TimeSeriesValue> values) {
        this.meta = meta;
        this.values = values;
    }
}

/**
 * this.closeValuesMap = this.values.stream()
 *                     .collect(Collectors.toMap(v -> v.getDatetime(), v -> v.getClose()));
 *             this.openValuesMap = this.values.stream()
 *                     .collect(Collectors.toMap(v -> v.getDatetime(), v -> v.getOpen()));
 *             this.highValuesMap = this.values.stream()
 *                     .collect(Collectors.toMap(v -> v.getDatetime(), v -> v.getHigh()));
 *             this.lowValuesMap = this.values.stream()
 *                     .collect(Collectors.toMap(v -> v.getDatetime(), v -> v.getLow()));*
 */
