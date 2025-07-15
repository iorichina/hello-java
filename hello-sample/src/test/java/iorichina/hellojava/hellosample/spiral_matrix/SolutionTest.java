package iorichina.hellojava.hellosample.spiral_matrix;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

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
    public void testSpiralOrder_standardMatrix() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        List<Integer> expected = Arrays.asList(1, 2, 3, 6, 9, 8, 7, 4, 5);
        assertEquals(expected, solution.spiralOrder(matrix));
    }

    @Test
    public void testSpiralOrder_singleRow() {
        int[][] matrix = {{1, 2, 3}};
        List<Integer> expected = Arrays.asList(1, 2, 3);
        assertEquals(expected, solution.spiralOrder(matrix));
    }

    @Test
    public void testSpiralOrder_singleColumn() {
        int[][] matrix = {{1}, {2}, {3}};
        List<Integer> expected = Arrays.asList(1, 2, 3);
        assertEquals(expected, solution.spiralOrder(matrix));
    }

    @Test
    public void testSpiralOrder_rectangularMatrix() {
        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7);
        assertEquals(expected, solution.spiralOrder(matrix));
    }
}