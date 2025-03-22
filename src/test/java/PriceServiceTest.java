import org.shop.PriceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PriceServiceTest {
    private PriceService priceService;
    private HttpClient httpClient;

    @BeforeEach
    public void setUp() {
        this.httpClient = mock(HttpClient.class);
        this.priceService = new PriceService(httpClient);
    }

    @Test
    public void testFetchPriceForProduct_ReturnPrice() throws Exception {
        // Mock API response
        HttpResponse<Object> mockResponse = mock(HttpResponse.class);
        when(mockResponse.statusCode()).thenReturn(200);
        when(mockResponse.body()).thenReturn("{\"price\":100.00}");

        when(httpClient.send(any(HttpRequest.class), any())).thenReturn(mockResponse);

        BigDecimal resultPrice = priceService.fetchPriceForProduct("Apple");

        assertEquals(new BigDecimal("100.00"), resultPrice);
    }

    @Test
    public void testFetchPriceForProduct_APIError() throws Exception {
        // Mock API response
        HttpResponse<Object> mockResponse = mock(HttpResponse.class);
        when(mockResponse.statusCode()).thenReturn(400);

        when(httpClient.send(any(HttpRequest.class), any())).thenReturn(mockResponse);

        BigDecimal resultPrice = priceService.fetchPriceForProduct("Apple");

        assertNull(resultPrice);
    }

    @Test
    public void testFetchPriceForProduct_ExceptionHandling() throws Exception {
        when(httpClient.send(any(HttpRequest.class), any())).thenThrow(new IOException());

        BigDecimal resultPrice = priceService.fetchPriceForProduct("Apple");

        assertNull(resultPrice);
    }
}
