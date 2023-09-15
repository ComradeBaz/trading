package com.domrade.forexpairs.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ForexPairItem {

    private String symbol;
    private String currency_group;
    private String currency_base;
    private String currency_quote;
}
