package com.lielamar.paypalwrapper;

import com.lielamar.paypalwrapper.modules.Options;
import com.lielamar.paypalwrapper.requests.GetAccessTokenRequest;
import com.lielamar.paypalwrapper.requests.Request;
import org.json.JSONObject;

import java.util.Objects;

public class PaypalWrapper {

    private final String accessToken;
    private final Options options;

    public PaypalWrapper(String clientId, String secret) {
        this(Objects.requireNonNull(new GetAccessTokenRequest(clientId, secret).execute(null, null)).getString("access_token"));
    }

    public PaypalWrapper(String accessToken) {
        this.accessToken = accessToken;
        this.options = new Options();
    }


    public Options getOptions() {
        return this.options;
    }


    public JSONObject executeRequest(Request request) {
        return request.execute(this.accessToken, this.options);
    }
}
