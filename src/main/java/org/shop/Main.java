package org.shop;

import java.net.http.HttpClient;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to Shop!");
        HttpClient client = HttpClient.newHttpClient();
        PriceService priceService = new PriceService(client);
        ShoppingCart cart = new ShoppingCart(priceService);

        cart.addProduct("cornflakes", 1);
        cart.addProduct("cornflakes", 1);
        cart.addProduct("weetabix", 1);

        cart.getProducts().forEach(product -> System.out.println("Cart contains " + product.getQuantity() + " x " + product.getName()));
        System.out.println("Subtotal: $" + cart.getCartSubtotal());
        System.out.println("Tax (12.5%): $" + cart.getPayableTax());
        System.out.println("Total Payable: $" + cart.getTotal());
    }

}