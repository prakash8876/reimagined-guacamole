package io.matoshri.demo;

import io.matoshri.demo.tests.SampeTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SampeTestTests {

    @Test
    void testDemoCode() {
        Integer value1 = 10;
        Integer value2 = 20;
        SampeTest test = new SampeTest();
        int result = test.addition(value1, value2);

        Assertions.assertTrue((30 == result));
    }

}
