package customer;

import org.eshop.exceptions.CustomerExistsException;
import org.eshop.shop.Shop;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RegisterTest {
    @Test
    void testRegistration() throws CustomerExistsException {
        Shop shop = new Shop();
        shop.registerUser("TA", "test", "test", "test");
    }

    @Test
    void preventDoubleRegistration() {
        Assertions.assertThrows(CustomerExistsException.class, () -> {
            Shop shop = new Shop();
            shop.registerUser("test", "test", "test", "test");
            shop.registerUser("test", "test", "test", "test");
        });
    }

    @Test
    void PreventEmptyRegistration() throws Exception {
        Shop shop = new Shop();
        shop.registerUser("", "", "", "");
        //throw new Exception("Not implemented");
    }
}
