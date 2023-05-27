package io.matoshri.demo.tests;

public class SampeTest {

    public int addition(Integer value1, Integer value2) {
        value1 = null;
        if (value1 == null) {
            System.out.println("value 1 is null");
            return 0;
        }
        if (value2==null) {
            System.out.println("value 2 is null");
            return 0;
        }
        return (value1 + value2);
    }

}
