/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.domrade.utilities.service.impl;

import com.domrade.utilities.service.api.IJsonConverterService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import lombok.extern.log4j.Log4j2;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


@Log4j2
@Service
public class JsonConverterService implements IJsonConverterService {

    private final ObjectMapper objMapper;

    public JsonConverterService() {
        objMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    }

    @PostConstruct
    public void init() {

    }

    @Override
    public String convertObjectToJson(Object t) {
        try {
            return objMapper.writeValueAsString(t);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(JsonConverterService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Could not convert object to JSON";
    }

    @Override
    public String convertStringToObject(String s, Object t) {
        try {
            Object o = objMapper.readValue(s, t.getClass());
            String st = convertObjectToJson(objMapper.readValue(s, t.getClass()));
            return convertObjectToJson(objMapper.readValue(s, t.getClass()));
        } catch (IOException ex) {
            log.error(ex.getMessage());
        }
        return "Could not convert object to JSON";
    }

    /**
     * @param <T>
     * @param response The response or part of a response from a GET/POST
     *                 request to Hubspot
     * @param type     The type to which we want to deserialize
     * @param property The JSON property from the response that we are
     *                 interested in
     * @return A list of Hubspot Objects of type T
     */
    @Override
    public <T> List<T> parseJson(String response, Class<T> type, String property) {
        List<T> returnList = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter();
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        JsonNode jsonNode;
        TypeFactory typeFactory = mapper.getTypeFactory();
        try {
            jsonNode = mapper.readTree(response);
            JsonNode objNode = jsonNode.get(property);
            if (objNode != null) {
                returnList = mapper.readValue(objNode.toString(), typeFactory.constructCollectionType(List.class, type));
            } else {
                return returnList;
            }
        } catch (NullPointerException npe) {
            Logger.getLogger(JsonConverterService.class.getName()).log(Level.WARNING, "No paging node - this should not be a problem", npe);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(JsonConverterService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return returnList;
        }

        //return returnList;
    }

    @Override
    public <T extends Object> T convertJsonStringToObject(String response, Class<T> type) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(response, type);
    }

}
