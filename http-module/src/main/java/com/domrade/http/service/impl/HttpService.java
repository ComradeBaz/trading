package com.domrade.http.service.impl;

import com.domrade.http.service.api.IHttpService;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Log4j2
@Service
@NoArgsConstructor
public class HttpService implements IHttpService {

    private final static String TAG = HttpService.class.toString();

    private RestTemplate restTemplate;

    @Autowired
    public HttpService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String makeConnectionAndGetResponse(String url, HttpMethod httpMethod) {
        log.info("About to make request");
        log.info("URL: " + url);
        log.info("Http Method: " + httpMethod.toString());
        String response = "";
        try {
            response = restTemplate.exchange(url, httpMethod, getHttpEntity(), String.class).getBody();
            return response;
        } catch (HttpClientErrorException exception) {
            if (exception.getRawStatusCode() == 401) {

            } else if (exception.getRawStatusCode() == 403) {

            } else if (exception.getRawStatusCode() == 404) {

            } else if (exception.getRawStatusCode() == 405) {

            } else {
                throw exception;
            }
        }
        return response;
    }

    public HttpEntity getHttpEntity() {
        // create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return new HttpEntity(headers);
    }
}
