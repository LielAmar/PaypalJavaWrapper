package com.lielamar.paypalwrapper;

import com.lielamar.paypalwrapper.modules.Options;
import com.lielamar.paypalwrapper.requests.GetAccessTokenRequest;
import com.lielamar.paypalwrapper.requests.Request;
import org.json.JSONObject;

import java.util.Objects;

public class PaypalWrapper {

    private final String accessToken; // Paypal's Access Token
    private final Options options;    // An options object

    /**
     * A constructor that receives a Client ID and a Secret key, and creates a request to retrieve the Access Token from Paypal.
     */
    public PaypalWrapper(String clientId, String secret) {
        this(Objects.requireNonNull(new GetAccessTokenRequest(clientId, secret).execute(null, null)).getString("access_token"));
    }

    public PaypalWrapper(String accessToken) {
        this.accessToken = accessToken;
        this.options = new Options();
    }


    /**
     * Returns the Options object
     * @return   Options object
     */
    public Options getOptions() {
        return this.options;
    }

    
    /**
     * Executes the provided request
     *
     * @param request   Request to execute
     * @return          Response from the request
     */
    public JSONObject executeRequest(Request request) {
        return request.execute(this.accessToken, this.options);
    }
}
