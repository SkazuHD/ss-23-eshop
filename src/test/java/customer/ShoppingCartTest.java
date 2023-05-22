package customer;

import org.eshop.entities.Customer;
import org.eshop.entities.Products;
import org.eshop.exceptions.NotInStockException;
import org.eshop.exceptions.ProductNotFound;
import org.eshop.shop.Shop;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShoppingCartTest {
    Shop shop = new Shop();
    Customer customer = new Customer("test", "test", "test", "test");

    @Test
    public void cartTooMuch() {
        Products p = shop.getProduct("Milch");
        Assertions.assertThrows(NotInStockException.class, () -> {
            shop.addProductToCart("Milch", 10000, customer);
        });
        Assertions.assertEquals(100, customer.getCart().get(p));

    }

    @Test
    public void addProduct() throws ProductNotFound, NotInStockException {
        shop.addProductToCart("Milch", 1, customer);
    }

    @Test
    public void removeProductComplete() throws ProductNotFound, NotInStockException {
        shop.addProductToCart("Milch", 1, customer);
        Assertions.assertEquals(1, customer.getCart().size());
        shop.removeProductFromCart("Milch", 1, customer);
        Assertions.assertEquals(0, customer.getCart().size());
    }

    @Test
    public void decreaseQuantity() throws ProductNotFound, NotInStockException {
        shop.addProductToCart("Milch", 2, customer);
        Products p = shop.getProduct("Milch");
        Assertions.assertEquals(2, customer.getCart().get(p));
        shop.removeProductFromCart("Milch", 1, customer);
        Assertions.assertEquals(1, customer.getCart().get(p));
    }

    @Test
    public void increaseQuantity() throws ProductNotFound, NotInStockException {
        shop.addProductToCart("Milch", 2, customer);
        Products p = shop.getProduct("Milch");
        Assertions.assertEquals(2, customer.getCart().get(p));
        shop.addProductToCart("Milch", 1, customer);
        Assertions.assertEquals(3, customer.getCart().get(p));
    }

    @Test
    public void increaseQuantityOverStock() throws ProductNotFound, NotInStockException {
        Products p = shop.getProduct("Milch");
        shop.addProductToCart("Milch", 49, customer);
        Assertions.assertEquals(49, customer.getCart().get(p));
        shop.addProductToCart("Milch", 49, customer);
        Assertions.assertEquals(49 * 2, customer.getCart().get(p));
        Assertions.assertThrows(NotInStockException.class, () -> {
            shop.addProductToCart("Milch", 3, customer);
        });
        Assertions.assertEquals(p.getQuantity(), customer.getCart().get(p));
    }

    @Test
    public void addProductNotExistent() {
        Assertions.assertThrows(ProductNotFound.class, () -> {
            shop.addProductToCart("TESTER", 200, customer);
        });
    }

    @Test
    public void removeProductNotExistent() throws ProductNotFound {
        Assertions.assertThrows(ProductNotFound.class, () -> {
            shop.removeProductFromCart("Milch die 2TE", 20, customer);
        });
    }
}
