package org.shop;

import java.math.BigDecimal;

public class Product {
    private final String name;
    private final BigDecimal price;
    private int quantity;

    public Product(String name, BigDecimal price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
