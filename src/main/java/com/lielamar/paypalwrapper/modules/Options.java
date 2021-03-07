package com.lielamar.paypalwrapper.modules;

public class Options {

    private Environment environment;
    private String currencyCode;

    private double shippingPercentage;
    private double handlingPercentage;
    private double shippingDiscountPercentage;

    /*
     * An options object with basic data for requests, such as currencyCode, percentage for other services etc.
     */

    public Options(Environment environment) {
        this(environment, "USD", 0.0, 0.0, 0.0);
    }

    public Options(Environment environment, String currencyCode, double shippingPercentage, double handlingPercentage, double shippingDiscountPercentage) {
        this.environment = environment;
        this.currencyCode = currencyCode;

        this.shippingPercentage = shippingPercentage;
        this.handlingPercentage = handlingPercentage;
        this.shippingDiscountPercentage = shippingDiscountPercentage;
    }


    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }


    public double getShippingPercentage() {
        return shippingPercentage;
    }

    public void setShippingPercentage(double shippingPercentage) {
        this.shippingPercentage = shippingPercentage;
    }

    public double getHandlingPercentage() {
        return handlingPercentage;
    }

    public void setHandlingPercentage(double handlingPercentage) {
        this.handlingPercentage = handlingPercentage;
    }

    public double getShippingDiscountPercentage() {
        return shippingDiscountPercentage;
    }

    public void setShippingDiscountPercentage(double shippingDiscountPercentage) {
        this.shippingDiscountPercentage = shippingDiscountPercentage;
    }
}
