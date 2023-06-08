package customer;

import org.eshop.entities.Customer;
import org.eshop.exceptions.NotInStockException;
import org.eshop.exceptions.ProductNotFound;
import org.eshop.shop.Shop;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
public class ShoppingCartTest {
    Shop shop = new Shop();
    Customer customer = new Customer("test", "test", "test", "test", "1111");

    @Test
    public void cartTooMuch() {


    }

    @Test
    public void addProduct() throws ProductNotFound, NotInStockException {

    }

    @Test
    public void removeProductComplete() throws ProductNotFound, NotInStockException {

    }

    @Test
    public void decreaseQuantity() throws ProductNotFound, NotInStockException {

    }

    @Test
    public void increaseQuantity() throws ProductNotFound, NotInStockException {

    }

    @Test
    public void increaseQuantityOverStock() throws ProductNotFound, NotInStockException {

    }

    @Test
    public void addProductNotExistent() {

    }

    @Test
    public void removeProductNotExistent() throws ProductNotFound {

    }
}
