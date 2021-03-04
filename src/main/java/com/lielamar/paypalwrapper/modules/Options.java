package com.lielamar.paypalwrapper.modules;

public class Options {

    private String currencyCode;

    private double shippingPercentage;
    private double handlingPercentage;
    private double shippingDiscountPercentage;

    public Options() {
        this("USD", 0.0, 0.0, 0.0);
    }

    public Options(String currencyCode, double shippingPercentage, double handlingPercentage, double shippingDiscountPercentage) {
        this.currencyCode = currencyCode;
        this.shippingPercentage = shippingPercentage;
        this.handlingPercentage = handlingPercentage;
        this.shippingDiscountPercentage = shippingDiscountPercentage;
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
