package org.shop;

import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PriceService {
    private final HttpClient client;
    private static final String BASE_API_URL = "https://equalexperts.github.io/backend-take-home-test-data/";

    public PriceService(HttpClient httpClient) {
        this.client = httpClient;
    }

    public BigDecimal fetchPriceForProduct(String productName) {
        final String API_URL = BASE_API_URL + productName + ".json";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .GET()
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                JSONObject obj = new JSONObject(response.body());
                return obj.getBigDecimal("price");
            } else {
                System.out.println("Error fetching price: " + response.statusCode());
                return null;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
