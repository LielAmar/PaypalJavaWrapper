package com.lielamar.paypalwrapper.modules;

public enum Environment {

    PRODUCTION (
            "https://api-m.paypal.com/v1/oauth2/token",
            "https://api-m.paypal.com/v2/checkout/orders",
            "https://api.paypal.com/v2/checkout/orders/%s/capture"
            ),
    SANDBOX (
            "https://api-m.sandbox.paypal.com/v1/oauth2/token",
            "https://api-m.sandbox.paypal.com/v2/checkout/orders",
            "https://api.sandbox.paypal.com/v2/checkout/orders/%s/capture"
    );

    private final String getAccessTokenUrl;
    private final String createOrderUrl;
    private final String confirmOrderUrl;

    Environment(String getAccessTokenUrl, String createOrderUrl, String confirmOrderUrl) {
        this.getAccessTokenUrl = getAccessTokenUrl;
        this.createOrderUrl = createOrderUrl;
        this.confirmOrderUrl = confirmOrderUrl;
    }

    public String getGetAccessTokenUrl() {
        return getAccessTokenUrl;
    }

    public String getCreateOrderUrl() {
        return createOrderUrl;
    }

    public String getConfirmOrderUrl(String transactionId) {
        return String.format(confirmOrderUrl, transactionId);
    }
}
