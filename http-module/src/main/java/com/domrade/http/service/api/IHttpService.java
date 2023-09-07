package com.domrade.http.service.api;

import org.springframework.http.HttpMethod;

public interface IHttpService {

    /**
     * *
     * @param url               The endpoint we are targeting
     * @param httpMethod        The REST method type
     * @return                  The response as a String
     */
    public String makeConnectionAndGetResponse(String url, HttpMethod httpMethod);
}
