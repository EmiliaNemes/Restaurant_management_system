package org.businessLayer;

import java.io.Serializable;

public class BaseProduct implements MenuItem, Serializable {
    private String productName;
    private int weight;
    private boolean vegetarian;
    private float price;

    public BaseProduct(String productName, int weight, boolean vegetarian, float price) {
        this.productName = productName;
        this.weight = weight;
        this.vegetarian = vegetarian;
        this.price = price;
    }

    @Override
    public float computePrice() {
        return price;
    }

    @Override
    public String getProductName() {
        return productName;
    }

    public int getWeight() {
        return weight;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public float getPrice() {
        return price;
    }
}
