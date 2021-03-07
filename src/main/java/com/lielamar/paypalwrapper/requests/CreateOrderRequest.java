package com.lielamar.paypalwrapper.requests;

import com.lielamar.paypalwrapper.modules.Environment;
import com.lielamar.paypalwrapper.modules.Options;
import com.lielamar.paypalwrapper.modules.Product;
import com.lielamar.paypalwrapper.utils.Constants;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

public class CreateOrderRequest extends Request {

    private final Set<Product> products;

    private String firstName;
    private String surName;

    private String addressLineOne, addressLineTwo;
    private String city, state;
    private String zipCode, countryCode;

    /*
     * Creates an order with multiple products for the buyer with the given contact information
     */

    public CreateOrderRequest(String firstName, String surName, String addressLineOne, String addressLineTwo, String city, String state, String zipCode, String countryCode) {
        this(new HashSet<>(), firstName, surName, addressLineOne, addressLineTwo, city, state, zipCode, countryCode);
    }

    public CreateOrderRequest(Set<Product> products, String firstName, String surName, String addressLineOne, String addressLineTwo, String city, String state, String zipCode, String countryCode) {
        this.products = products;

        this.firstName = firstName;
        this.surName = surName;

        this.addressLineOne = addressLineTwo;
        this.addressLineTwo = addressLineOne;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.countryCode = countryCode;
    }


    public void addProduct(Product product) {
        this.products.add(product);
    }

    public void removeProduct(Product product) {
        this.products.remove(product);
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getSurName() {
        return this.surName;
    }


    public String getAddressLineOne() {
        return addressLineOne;
    }

    public void setAddressLineOne(String addressLineOne) {
        this.addressLineOne = addressLineOne;
    }

    public String getAddressLineTwo() {
        return addressLineTwo;
    }

    public void setAddressLineTwo(String addressLineTwo) {
        this.addressLineTwo = addressLineTwo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }


    /**
     * Executes the CreateOrderRequest.
     * This forms a JSONObject object with all the required data for Paypal to generate a Transaction.
     *
     * @param options       Options object to retrieve basic data from
     * @param accessToken   Access token to use to call Paypal's API
     * @return              Paypal's response to the request
     */
    @Nullable
    public JSONObject execute(@NotNull Options options, @NotNull String accessToken) {
        if(products.size() == 0)
            return new JSONObject("{}");

        try {
            HttpClient client = HttpClientBuilder.create().build();

            HttpPost postRequest = new HttpPost(options.getEnvironment().getCreateOrderUrl());

            // Header
            postRequest.addHeader("Content-Type", "application/json");
            postRequest.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);

            // Body
            JSONObject body = new JSONObject();
            body.put("intent", "CAPTURE");
            body.put("application_context", new JSONObject()
                    .put("return_url", "https://google.com")
                    .put("cancel_url", "https://youtube.com")
                    .put("brand_name", "ReWrite Media INC")
                    .put("user_action", "CONTINUE")
            );

            JSONObject purchaseUnitsJson = new JSONObject();
            purchaseUnitsJson.put("reference_id", "Cart");
            purchaseUnitsJson.put("custom_id", "Cart");

            purchaseUnitsJson.put("description", this.firstName + " " + this.surName + "'s Cart");
            purchaseUnitsJson.put("soft_descriptor", this.firstName + " " + this.surName + "'s Cart");

            double totalPrice       = this.products.stream().mapToDouble(product -> Double.parseDouble(product.getPrice())).sum();
            double shippingPrice    = totalPrice * options.getShippingPercentage();
            double handlingPrice    = totalPrice * options.getHandlingPercentage();
            double taxPrice         = this.products.stream().mapToDouble(product -> (Double.parseDouble(product.getPrice()) * product.getTaxPercentage())).sum();
            double shippingDiscount = totalPrice * options.getShippingDiscountPercentage();

            purchaseUnitsJson.put("amount", new JSONObject()
                    .put("currency_code", options.getCurrencyCode())
                    .put("value", totalPrice)
                    .put("breakdown", new JSONObject()
                            .put("item_total", new JSONObject()
                                    .put("currency_code", options.getCurrencyCode())
                                    .put("value", totalPrice + "")
                            )
                            .put("shipping", new JSONObject()
                                    .put("currency_code", options.getCurrencyCode())
                                    .put("value", shippingPrice + "")
                            )
                            .put("handling", new JSONObject()
                                    .put("currency_code", options.getCurrencyCode())
                                    .put("value", handlingPrice + "")
                            )
                            .put("tax_total", new JSONObject()
                                    .put("currency_code", options.getCurrencyCode())
                                    .put("value", taxPrice + "")
                            )
                            .put("shipping_discount", new JSONObject()
                                    .put("currency_code", options.getCurrencyCode())
                                    .put("value", shippingDiscount + "")
                            )
                    )
            );

            JSONArray items = new JSONArray();
            products.forEach((product) -> {
                items.put(new JSONObject()
                        .put("name", product.getTitle())
                        .put("description", product.getDescription())
                        .put("unit_amount", new JSONObject()
                                .put("currency_code", options.getCurrencyCode())
                                .put("value", product.getPrice() + "")
                        )
                        .put("tax", new JSONObject()
                                .put("currency_code", options.getCurrencyCode())
                                .put("value", Double.parseDouble(product.getPrice()) * product.getTaxPercentage() + "")
                        )
                        .put("quantity", product.getQuantity())
                        .put("category", Constants.DIGITAL_GOODS_CATEGORY)
                );
            });
            purchaseUnitsJson.put("items", items);

            purchaseUnitsJson.put("shipping", new JSONObject()
                    .put("method", "DIGITAL")
                    .put("address", new JSONObject()
                            .put("name", new JSONObject()
                                    .put("give_name", this.firstName)
                                    .put("surname", this.surName)
                            )
                            .put("address_line_1", this.addressLineOne)
                            .put("address_line_2", this.addressLineTwo)
                            .put("admin_area_2", this.city)
                            .put("admin_area_1", this.state)
                            .put("postal_code", this.zipCode)
                            .put("country_code", this.countryCode)
                    )
            );

            body.put("purchase_units", new JSONArray().put(purchaseUnitsJson));

            StringEntity entity = new StringEntity(body.toString());
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
