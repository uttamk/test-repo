import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shop.PriceService;
import org.shop.Product;
import org.shop.ShoppingCart;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShoppingCartTest {
    private ShoppingCart shoppingCart;
    private PriceService priceServiceMock;


    @BeforeEach
    public void setUp() {
        priceServiceMock = mock(PriceService.class);
        shoppingCart = new ShoppingCart(priceServiceMock);
    }

    @Test
    public void testAddProduct() {
        when(priceServiceMock.fetchPriceForProduct("Apple")).thenReturn(new BigDecimal("1.50"));

        shoppingCart.addProduct("Apple", 1);

        Product expectedProduct = new Product("Apple", new BigDecimal("1.50"), 1);
        Product resultProduct = shoppingCart.getProducts().get(0);

        assertEquals(resultProduct.getName(), expectedProduct.getName());
        assertEquals(resultProduct.getPrice(), expectedProduct.getPrice());
        assertEquals(resultProduct.getQuantity(), expectedProduct.getQuantity());
    }

    @Test
    public void testAddProductToIncreaseProductQuantity() {
        when(priceServiceMock.fetchPriceForProduct("Apple")).thenReturn(new BigDecimal("1.50"));

        shoppingCart.addProduct("Apple", 1);
        shoppingCart.addProduct("Apple", 1);

        Product expectedProduct = new Product("Apple", new BigDecimal("1.50"), 2);
        Product resultProduct = shoppingCart.getProducts().get(0);

        assertEquals(resultProduct.getName(), expectedProduct.getName());
        assertEquals(resultProduct.getPrice(), expectedProduct.getPrice());
        assertEquals(resultProduct.getQuantity(), expectedProduct.getQuantity());
    }

    @Test
    public void testAddProductToNotHaveNegativeQuantity() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            shoppingCart.addProduct("Apple", -2);
        });

        assertEquals("Quantity must be greater than 0", exception.getMessage());

    }

    @Test
    public void testAddProductWhenPriceServiceReturnsNull() {
        when(priceServiceMock.fetchPriceForProduct("UnknownProduct")).thenReturn(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            shoppingCart.addProduct("UnknownProduct", 1);
        });

        assertEquals("Price could not be fetched.", exception.getMessage());
    }

    @Test
    public void testGetCartSubtotal() {
        when(priceServiceMock.fetchPriceForProduct("cornflakes")).thenReturn(new BigDecimal("2.52"));
        when(priceServiceMock.fetchPriceForProduct("weetabix")).thenReturn(new BigDecimal("9.98"));

        shoppingCart.addProduct("cornflakes", 1);
        shoppingCart.addProduct("cornflakes", 1);
        shoppingCart.addProduct("weetabix", 1);
        BigDecimal subtotal = shoppingCart.getCartSubtotal();
        assertEquals(new BigDecimal("15.02"), subtotal);
    }

    @Test
    public void testGetPayableTax() {
        when(priceServiceMock.fetchPriceForProduct("cornflakes")).thenReturn(new BigDecimal("2.52"));
        when(priceServiceMock.fetchPriceForProduct("weetabix")).thenReturn(new BigDecimal("9.98"));

        shoppingCart.addProduct("cornflakes", 1);
        shoppingCart.addProduct("cornflakes", 1);
        shoppingCart.addProduct("weetabix", 1);
        BigDecimal tax = shoppingCart.getPayableTax();
        assertEquals(new BigDecimal("1.88"), tax);
    }

    @Test
    public void testGetTotalPrice() {
        when(priceServiceMock.fetchPriceForProduct("cornflakes")).thenReturn(new BigDecimal("2.52"));
        when(priceServiceMock.fetchPriceForProduct("weetabix")).thenReturn(new BigDecimal("9.98"));

        shoppingCart.addProduct("cornflakes", 1);
        shoppingCart.addProduct("cornflakes", 1);
        shoppingCart.addProduct("weetabix", 1);
        BigDecimal total = shoppingCart.getTotal();
        assertEquals(new BigDecimal("16.90"), total);
    }
}
