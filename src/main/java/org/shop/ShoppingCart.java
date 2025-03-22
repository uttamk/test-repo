package org.shop;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private final List<Product> products;
    private final PriceService priceService;
    private static final BigDecimal TAX_RATE = new BigDecimal("0.125");


    public ShoppingCart(PriceService priceService) {
        this.products = new ArrayList<>();
        this.priceService = priceService;

    }

    public void addProduct(String name, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }

        for (Product product : products) {
            if (product.getName().equals(name)) {
                product.setQuantity(product.getQuantity() + quantity);
                return;
            }
        }

        BigDecimal price = priceService.fetchPriceForProduct(name);
        if (price != null) {
            products.add(new Product(name, price, quantity));
        } else {
            throw new IllegalArgumentException("Price could not be fetched.");
        }
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }

    public BigDecimal getCartSubtotal() {
        return products.stream()
                .map(p -> p.getPrice().multiply(BigDecimal.valueOf(p.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getPayableTax() {
        return getCartSubtotal().multiply(TAX_RATE).setScale(2, RoundingMode.HALF_UP);

    }

    public BigDecimal getTotal() {
        return getCartSubtotal().add(getPayableTax());
    }

}
