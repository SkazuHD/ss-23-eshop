package Produckt;

import org.eshop.entities.Products;
import org.eshop.shop.ProductManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductTest {

    @Test
    void testProduckt() {
        ProductManager productManger = new ProductManager();
        productManger.addProduct("Horst", 3.99, 4);
        Assertions.assertEquals(1, productManger.products.size());
    }

    @Test
    void removeProductQuantiy() {
        ProductManager productManger = new ProductManager();
        productManger.addProduct("Horst", 3.99, 4);
        Products p = productManger.getProduct("Horst");
        productManger.removeProduct("Horst", 2);
        Assertions.assertEquals(2, p.getQuantity());
    }

    @Test
    void removeProductComplete() {
        ProductManager productManger = new ProductManager();
        productManger.addProduct("Horst", 3.99, 4);
        productManger.removeProduct("Horst", 4);
        Assertions.assertEquals(0, productManger.products.size());

    }

}
