package com.domrade.timeseries.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
