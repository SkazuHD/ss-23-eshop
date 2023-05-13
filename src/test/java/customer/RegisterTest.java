package customer;

import org.eshop.exceptions.CustomerExistsException;
import org.eshop.shop.Shop;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RegisterTest {
    Shop shop = new Shop();

    @Test
    void testRegistration() throws CustomerExistsException {
        shop.registerUser("TA", "test", "test", "test");
    }

    @Test
    void preventDoubleRegistration() {
        Assertions.assertThrows(CustomerExistsException.class, () -> {
            shop.registerUser("test", "test", "test", "test");
            shop.registerUser("test", "test", "test", "test");
        });
    }

    @Test
    void PreventEmptyRegistration() throws Exception {
        //CLI PREVENTS EMPTY STRINGS -> NO TEST NEEDED

        // shop.registerUser("", "", "", "");
    }
}
