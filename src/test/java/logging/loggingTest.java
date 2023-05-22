package logging;

import org.eshop.util.Loger;
import org.junit.jupiter.api.Test;

public class loggingTest {
    @Test
    void loggingTest() {
        Loger logger = new Loger();
        Loger.log("Test");
    }
}
