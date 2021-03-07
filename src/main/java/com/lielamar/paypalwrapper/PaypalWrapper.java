package com.lielamar.paypalwrapper;

import com.lielamar.paypalwrapper.exceptions.RequestExecutionException;
import com.lielamar.paypalwrapper.modules.Environment;
import com.lielamar.paypalwrapper.modules.Options;
import com.lielamar.paypalwrapper.requests.GetAccessTokenRequest;
import com.lielamar.paypalwrapper.requests.Request;
import org.json.JSONObject;

public class PaypalWrapper {

    private final String accessToken;      // Paypal's Access Token
    private final Options options;         // An options object


    private PaypalWrapper(String accessToken, Options options) {
        this.accessToken = accessToken;
        this.options = options;
    }

    /**
     * A builder that builds a PaypalWrapper object through an Access Token
     *
     * @param environment   Environment of the Wrapper
     * @param accessToken   Access Token to use to build the PaypalWrapper
     * @return              Built PaypalWrapper object
     */
    public static PaypalWrapper buildWrapper(Environment environment, String accessToken) {
        Options options = new Options(environment);

        return new PaypalWrapper(accessToken, options);
    }

    /**
     * A builder that builds a PaypalWrapper object through an a client Id and Client Secret
     *
     * @param environment   Environment of the Wrapper
     * @param clientId      ID of the client to generate the Access Token for
     * @param secret        Secret of the client to generate the Access Token for
     * @return              Built PaypalWrapper object
     */
    public static PaypalWrapper buildWrapper(Environment environment, String clientId, String secret) throws RequestExecutionException {
        Options options = new Options(environment);

        GetAccessTokenRequest request = new GetAccessTokenRequest(clientId, secret);
        JSONObject response = request.execute(options, null);

        if(response == null)
            throw new RequestExecutionException(request);

        return new PaypalWrapper(response.getString("access_token"), options);
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
        return request.execute(this.options, this.accessToken);
    }
}
