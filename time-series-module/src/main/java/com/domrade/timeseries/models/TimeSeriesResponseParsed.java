package com.domrade.timeseries.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
public class TimeSeriesResponseParsed {

    private TimeSeriesMetaData meta;
    private List<TimeSeriesValue> values;

    private Map<Date, Double> closeValuesMap;
    private Map<Date, Double> openValuesMap;
    private Map<Date, Double> highValuesMap;
    private Map<Date, Double> lowValuesMap;

    public TimeSeriesResponseParsed(TimeSeriesMetaData meta, List<TimeSeriesValue> values) {
        this.meta = meta;
        this.values = values;
        setValues("low");
        setValues("close");
        setValues("open");
        setValues("high");
    }

    // Group the data by open, close, high and low values and date
    private void setValues(String type) {
        Map<Date, Double> valuesHashMap = new HashMap<>();
        if (this.values.size() > 0) {
            valuesHashMap = this.values.stream()
                    .collect(Collectors.toMap(v -> {
                        try {
                            SimpleDateFormat sdf = getDateFormatter(this.meta.getInterval());
                            Date myDate = sdf.parse(v.getDatetime());
                            return myDate;
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        return new Date();
                    }, v -> {
                        if (type.equalsIgnoreCase("low")) {
                            return v.getLow();
                        } else if (type.equalsIgnoreCase("close")) {
                            return v.getClose();
                        } else if (type.equalsIgnoreCase("open")) {
                            return v.getOpen();
                        } else {
                            return v.getHigh();
                        }
                    }));
        }
        switch (type) {
            // Add the values to a TreeMap to apply sorting based on the key
            // which is a date
            case "low":
                lowValuesMap = new TreeMap<>(valuesHashMap);
                break;
            case "close":
                closeValuesMap = new TreeMap<>(valuesHashMap);
                break;
            case "open":
                openValuesMap = new TreeMap<>(valuesHashMap);
                break;
            default:
                highValuesMap = new TreeMap<>(valuesHashMap);
        }
    }

    private SimpleDateFormat getDateFormatter(String interval) {
        String patternOneMinuteInterval = "yyyy-MM-dd HH:mm:ss";
        String patternOndDayInterval = "yyyy-MM-dd";

        switch (interval) {
            case ("1day"):
            case ("1week"):
            case ("1month"):
                return new SimpleDateFormat(patternOndDayInterval,
                        Locale.ENGLISH);
            case ("1min"):
            case("5min"):
            case("15min"):
            case("30min"):
            case("45min"):
            case("1h"):
            case("2h"):
            case("4h"):
            default:
                return new SimpleDateFormat(patternOneMinuteInterval,
                        Locale.ENGLISH);
        }
    }
}
