package org.businessLayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CompositeProduct implements MenuItem, Serializable {
    private String productName;
    private float price;
    private List<MenuItem> menuItemList = new ArrayList<MenuItem>();

    public CompositeProduct(String productName, List<MenuItem> menuItemList) {
        this.productName = productName;
        this.menuItemList = menuItemList;
        this.price = computePrice();
    }

    @Override
    public float computePrice() {
        for (MenuItem item : menuItemList) {
            price += item.computePrice();
        }
        return price;
    }

    public String getProductName() {
        return productName;
    }

    public float getPrice() {
        return price;
    }

    public List<MenuItem> getMenuItemList() {
        return menuItemList;
    }
}