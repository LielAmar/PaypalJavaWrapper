package com.lielamar.paypalwrapper.requests;

import com.lielamar.paypalwrapper.modules.Options;
import org.json.JSONObject;

import java.io.IOException;

public abstract class Request {

    protected String apiLink;

    public Request(String apiLink) {
        this.apiLink = apiLink;
    }


    public String getAPILink() {
        return this.apiLink;
    }


    /**
     * Request Executor
     *
     * @param accessToken   Access token to use to call Paypal's API
     * @param options       Options object to retrieve basic data from
     * @return              Paypal's response to the request
     */
    public abstract JSONObject execute(String accessToken, Options options);
}
