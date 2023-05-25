package logging;

import org.eshop.util.Logger;
import org.junit.jupiter.api.Test;

public class loggingTest {
    @Test
    void loggingTest() {
        Logger logger = new Logger();
        Logger.log("Test");
    }
}
