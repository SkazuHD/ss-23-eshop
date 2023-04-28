package customer;
import org.eshop.exceptions.CustomerExistsException;
import org.eshop.exceptions.CustomerLoginFailed;
import org.eshop.shop.Shop;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class LoginTest {
    Shop shop = new Shop();

    @Test
    void loginWithNewUser() throws CustomerExistsException, CustomerLoginFailed {
        shop.registerUser("test","test","test","test");
        shop.loginUser("test","test");
    }
    @Test
    void loginWithNonExistingUser(){
        Assertions.assertThrows(CustomerLoginFailed.class, () -> {
            shop.loginUser("doesNotExistLoL","test");
        });
    }
    @Test
    void loginWithWrongPassword(){
        Assertions.assertThrows(CustomerLoginFailed.class, () -> {
            shop.loginUser("test","wrongPassword");
        });
    }

}
