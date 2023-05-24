package InvoiceTest;

import org.eshop.entities.Customer;
import org.eshop.entities.User;
import org.eshop.exceptions.LoginFailed;
import org.eshop.exceptions.NotInStockException;
import org.eshop.exceptions.ProductNotFound;
import org.eshop.exceptions.UserExistsException;
import org.eshop.shop.Shop;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class InvoiceTest {

    @Disabled
    @Test
    void testInvoice() throws UserExistsException, LoginFailed, ProductNotFound, NotInStockException {
        Shop shop = new Shop();
        User c = shop.loginUser("test", "test");
        shop.addProductToCart("test0", 5, (Customer) c);
        shop.addProductToCart("test1", 5, (Customer) c);
        shop.addProductToCart("test2", 5, (Customer) c);


    }
}
