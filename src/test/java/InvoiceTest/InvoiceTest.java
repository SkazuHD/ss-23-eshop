package InvoiceTest;

import org.eshop.entities.Customer;
import org.eshop.entities.Invoice;
import org.eshop.exceptions.CustomerExistsException;
import org.eshop.exceptions.CustomerLoginFailed;
import org.eshop.shop.Shop;
import org.junit.jupiter.api.Test;

public class InvoiceTest {

    @Test
    void testInvoice() throws CustomerExistsException, CustomerLoginFailed {
        Shop shop = new Shop();
        shop.registerUser("test", "test", "test", "test");
        Customer c = shop.loginUser("test", "test");
        shop.addProductToCart("Milch", 5, c);
        shop.addProductToCart("Brot", 5, c);
        shop.addProductToCart("Eier", 5, c);
        Invoice invoice = new Invoice(c);
        
        System.out.println(invoice);
    }
}
