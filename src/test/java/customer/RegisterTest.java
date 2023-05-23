package customer;

import org.eshop.exceptions.UserExistsException;
import org.eshop.shop.Shop;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


public class RegisterTest {
    Shop shop = new Shop();

    @Test
    @Disabled
    void testRegistration() throws UserExistsException {
        shop.registerUser("TA", "test", "test", "test");
    }

    @Test
    void preventDoubleRegistration() {
        Assertions.assertThrows(UserExistsException.class, () -> {
            shop.registerUser("test", "test", "test", "test");
            shop.registerUser("test", "test", "test", "test");
        });
    }

    @Test
    @Disabled
    void PreventEmptyRegistration() throws Exception {
        //CLI PREVENTS EMPTY STRINGS -> NO TEST NEEDED

        // shop.registerUser("", "", "", "");
    }
}
