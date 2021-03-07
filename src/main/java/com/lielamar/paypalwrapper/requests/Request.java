package com.lielamar.paypalwrapper.requests;

import com.lielamar.paypalwrapper.modules.Options;
import org.json.JSONObject;

public abstract class Request {

    /**
     * Request Executor
     *
     * @param accessToken   Access token to use to call Paypal's API
     * @param options       Options object to retrieve basic data from
     * @return              Paypal's response to the request
     */
    public abstract JSONObject execute(Options options, String accessToken);
}
