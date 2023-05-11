package InvoiceTest;

import org.eshop.entities.Customer;
import org.eshop.entities.User;
import org.eshop.exceptions.CustomerExistsException;
import org.eshop.exceptions.CustomerLoginFailed;
import org.eshop.shop.Shop;
import org.junit.jupiter.api.Test;

public class InvoiceTest {

    @Test
    void testInvoice() throws CustomerExistsException, CustomerLoginFailed {
        Shop shop = new Shop();
        shop.registerUser("test", "test", "test", "test");
        User c = shop.loginUser("test", "test");
        shop.addProductToCart("Milch", 5, (Customer) c);
        shop.addProductToCart("Brot", 5, (Customer) c);
        shop.addProductToCart("Eier", 5, (Customer) c);


        System.out.println(shop.checkout((Customer) c));
    }
}
