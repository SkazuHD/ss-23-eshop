package customer;

import org.eshop.entities.Customer;
import org.eshop.entities.Products;
import org.eshop.exceptions.NotInStockException;
import org.eshop.exceptions.ProductNotFound;
import org.eshop.shop.Shop;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
public class ShoppingCartTest {
    Shop shop = new Shop();
    Customer customer = new Customer("test", "test", "test", "test", "1111");

    @Test
    public void cartTooMuch() {
        Products p = shop.getProduct("test0");
        Assertions.assertThrows(NotInStockException.class, () -> {
            shop.addProductToCart("test0", 10000, customer);
        });
        Assertions.assertEquals(100, customer.getCart().get(p));

    }

    @Test
    public void addProduct() throws ProductNotFound, NotInStockException {
        shop.addProductToCart("test0", 1, customer);
    }

    @Test
    public void removeProductComplete() throws ProductNotFound, NotInStockException {
        shop.addProductToCart("test0", 1, customer);
        Assertions.assertEquals(1, customer.getCart().size());
        shop.removeProductFromCart("test0", 1, customer);
        Assertions.assertEquals(0, customer.getCart().size());
    }

    @Test
    public void decreaseQuantity() throws ProductNotFound, NotInStockException {
        shop.addProductToCart("test0", 2, customer);
        Products p = shop.getProduct("test0");
        Assertions.assertEquals(2, customer.getCart().get(p));
        shop.removeProductFromCart("test0", 1, customer);
        Assertions.assertEquals(1, customer.getCart().get(p));
    }

    @Test
    public void increaseQuantity() throws ProductNotFound, NotInStockException {
        shop.addProductToCart("test0", 2, customer);
        Products p = shop.getProduct("test0");
        Assertions.assertEquals(2, customer.getCart().get(p));
        shop.addProductToCart("test0", 1, customer);
        Assertions.assertEquals(3, customer.getCart().get(p));
    }

    @Test
    public void increaseQuantityOverStock() throws ProductNotFound, NotInStockException {
        Products p = shop.getProduct("test0");
        shop.addProductToCart("test0", 49, customer);
        Assertions.assertEquals(49, customer.getCart().get(p));
        shop.addProductToCart("test0", 49, customer);
        Assertions.assertEquals(49 * 2, customer.getCart().get(p));
        Assertions.assertThrows(NotInStockException.class, () -> {
            shop.addProductToCart("test0", 3, customer);
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
            shop.removeProductFromCart("test0 die 2TE", 20, customer);
        });
    }
}
