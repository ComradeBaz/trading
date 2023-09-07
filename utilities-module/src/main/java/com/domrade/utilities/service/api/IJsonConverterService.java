package com.domrade.utilities.service.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IJsonConverterService {

    public <T> String convertObjectToJson(T t);

    public <T> String convertStringToObject(String s, T t);

    /**
     *
     * @param <T>
     * @param response The response or part of a response from a GET/POST
     * request to Hubspot
     * @param type The type to which we want to deserialize
     * @param property The JSON property from the response that we are
     * interested in
     * @return A list of Hubspot Objects of type T
     */
    public <T> List<T> parseJson(String response, Class<T> type, String property);
    
    public <T extends Object> T convertJsonStringToObject(String response, Class<T> type) throws JsonProcessingException;

}
