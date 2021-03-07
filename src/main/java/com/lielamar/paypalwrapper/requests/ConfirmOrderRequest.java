package com.lielamar.paypalwrapper.requests;

import com.lielamar.paypalwrapper.modules.Options;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ConfirmOrderRequest {

    private String transactionId;

    /*
     * Confirms an order with the given id
     */

    public ConfirmOrderRequest(String transactionId) {
        this.transactionId = transactionId;
    }


    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }


    /**
     * Executes the ConfirmOrderRequest.
     * This forms a JSONObject object with all the required data for Paypal to return information about a certain Transaction.
     *
     * @param options       Options object to retrieve basic data from
     * @param accessToken   Access token to use to call Paypal's API
     * @return              Paypal's response to the request
     */
    public JSONObject execute(Options options, String accessToken) {
        try {
            HttpClient client = HttpClientBuilder.create().build();

            HttpPost postRequest = new HttpPost(options.getEnvironment().getConfirmOrderUrl(this.transactionId));

            // Header
            postRequest.addHeader("Content-Type", "application/json");
            postRequest.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);

            // Body

            // Execution
            HttpResponse response = client.execute(postRequest);
            String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);

            return new JSONObject(responseBody);
        } catch(IOException exception) {
            exception.printStackTrace();
        }

        return null;
    }
}
