package iorichina.hellojava.hellosample.test.lang;

import org.junit.Test;

public class SwitchTest {
    @Test(expected = NullPointerException.class)
    public void test_null() {
        String nil = null;
        switch (nil) {
            case "gogo":
                System.out.println("gogo");
                break;
            default:
                System.out.println("nil");
        }
        switch (nil) {
            case "gogo":
                System.out.println("gogo");
                break;
        }
    }
}
