package com.domrade.controllers;

import com.domrade.timeseries.models.TimeSeriesResponse;
import com.domrade.timeseries.service.api.ITimeSeriesService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@NoArgsConstructor
@RestController
@RequestMapping("/rest/v1/timeseries/")
public class TimeSeriesController {

    @Autowired
    private ITimeSeriesService timeseriesService;

    @GetMapping(value = "listTimeseriesBySymbol")
    public ResponseEntity<String> listTimeseriesBySymbol(@RequestParam("symbol") String symbol,
                                                         @RequestParam(value = "interval", defaultValue = "1day") String interval,
                                                         @RequestParam(value = "outputsize", defaultValue = "30") String outputsize) {
        TimeSeriesResponse response = timeseriesService.getTimeSeriesResponseBySymbol(symbol, interval, outputsize);

        return new ResponseEntity(response, HttpStatus.OK);
    }
}
