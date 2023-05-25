package iorichina.hellojava.hellosample.test.lang;

import org.junit.Assert;
import org.junit.Test;

public class PrimitiveTest {
    @Test
    public void test_boolean_and() {
        boolean rst = true;
        rst &= true;
        Assert.assertTrue(rst);
        rst &= true;
        Assert.assertTrue(rst);

        rst = true;
        rst &= false;
        Assert.assertFalse(rst);
        rst &= true;
        Assert.assertFalse(rst);

        rst = true;
        rst &= true;
        Assert.assertTrue(rst);
        rst &= false;
        Assert.assertFalse(rst);

        rst = true;
        rst &= false;
        Assert.assertFalse(rst);
        rst &= false;
        Assert.assertFalse(rst);
    }
}
