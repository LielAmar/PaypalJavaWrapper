package com.lielamar.paypalwrapper.requests;

import com.lielamar.paypalwrapper.modules.Options;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class GetAccessTokenRequest extends Request {

    private String clientId;
    private String secret;

    public GetAccessTokenRequest(String clientId, String secret) {
        super("https://api-m.sandbox.paypal.com/v1/oauth2/token");

        this.clientId = clientId;
        this.secret = secret;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public JSONObject execute(String accessToken, Options options) {
        try {
            HttpClient client = HttpClientBuilder.create().build();

            HttpPost postRequest = new HttpPost(this.apiLink);

            // Header
            String encoding = Base64.getEncoder().encodeToString((this.clientId + ":" + this.secret).getBytes());
            postRequest.addHeader("Content-Type", "application/x-www-form-urlencoded");
            postRequest.addHeader(HttpHeaders.AUTHORIZATION, "Basic " + encoding);

            // Body
            StringEntity entity = new StringEntity("grant_type=client_credentials");
            postRequest.setEntity(entity);

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
