package com.lielamar.paypalwrapper.requests;

import com.lielamar.paypalwrapper.modules.Options;
import org.json.JSONObject;

import java.io.IOException;

public abstract class Request {

    String apiLink;

    public Request(String apiLink) {
        this.apiLink = apiLink;
    }

    public String getAPILink() {
        return this.apiLink;
    }

    public abstract JSONObject execute(String accessToken, Options options);
}
