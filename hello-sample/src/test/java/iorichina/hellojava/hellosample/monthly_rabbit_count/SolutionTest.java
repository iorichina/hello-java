package iorichina.hellojava.hellosample.monthly_rabbit_count;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 单元测试类：SolutionTest
 */
public class SolutionTest {
    private Solution solution;

    @Before
    public void setUp() {
        solution = new Solution();
    }

    @Test
    public void testRabbitCount_month1() {
        int month = 1;
        int expected = 1;
        assertEquals(expected, solution.rabbitCount(month));
    }

    @Test
    public void testRabbitCount_month2() {
        int month = 2;
        int expected = 1;
        assertEquals(expected, solution.rabbitCount(month));
    }

    @Test
    public void testRabbitCount_month3() {
        int month = 3;
        int expected = 2;
        assertEquals(expected, solution.rabbitCount(month));
    }

    @Test
    public void testRabbitCount_month4() {
        int month = 4;
        int expected = 3;
        assertEquals(expected, solution.rabbitCount(month));
    }

    @Test
    public void testRabbitCount_month5() {
        int month = 5;
        int expected = 5;
        assertEquals(expected, solution.rabbitCount(month));
    }

    @Test
    public void testRabbitCount_month6() {
        int month = 6;
        int expected = 8;
        assertEquals(expected, solution.rabbitCount(month));
    }

    @Test
    public void testRabbitCount_month10() {
        int month = 10;
        int expected = 55;
        assertEquals(expected, solution.rabbitCount(month));
    }

    @Test
    public void testRabbitCount_invalidMonth() {
        int month = -1;
        int expected = 0;
        assertEquals(expected, solution.rabbitCount(month));
    }
}