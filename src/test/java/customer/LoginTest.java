
import org.eshop.exceptions.LoginFailed;
import org.eshop.exceptions.UserExistsException;
import org.eshop.shop.Shop;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginTest {
    Shop shop = new Shop();

    @Test
    void loginUser() throws UserExistsException, LoginFailed {
        shop.logIn("test", "test");
    }

    @Test
    void loginWithNonExistingUser() {
        Assertions.assertThrows(LoginFailed.class, () -> {
            shop.logIn("doesNotExistLoL", "test");
        });
    }

    @Test
    void loginWithWrongPassword() {
        Assertions.assertThrows(LoginFailed.class, () -> {
            shop.logIn("test", "wrongPassword");
        });
    }

}
