package com.lielamar.paypalwrapper.modules;

public class Product {

    private String category;
    private String packageId;

    private String title;
    private String description;

    private String price;
    private int taxPercentage;

    private int quantity;

    public Product(String category, String packageId, String title, String description, String price, int taxPercentage, int quantity) {
        this.category = category;
        this.packageId = packageId;

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

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
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
