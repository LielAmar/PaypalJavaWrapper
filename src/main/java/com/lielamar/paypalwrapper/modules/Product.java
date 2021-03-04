package com.lielamar.paypalwrapper.modules;

public class Product {

    private String category;
    private String productId;

    private String title;
    private String description;

    private String price;
    private int taxPercentage;

    private int quantity;

    /*
     * Product object to assemble a complete product
     */

    public Product(String category, String productId, String title, String description, String price, int taxPercentage, int quantity) {
        this.category = category;
        this.productId = productId;

        this.title = title;
        this.description = description;

        this.price = price;
        this.taxPercentage = taxPercentage;

        this.quantity = quantity;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getTaxPercentage() {
        return taxPercentage;
    }

    public void setTaxPercentage(int taxPercentage) {
        this.taxPercentage = taxPercentage;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
