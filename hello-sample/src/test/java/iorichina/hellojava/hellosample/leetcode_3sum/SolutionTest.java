package iorichina.hellojava.hellosample.leetcode_3sum;

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
    public void testThreeSum_1() {

    }

    @Test
    public void testThreeSum_2() {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(-1, -1, 2),
                Arrays.asList(-1, 0, 1)
        );
        assertEquals(expected, solution.threeSum(nums));
    }

    @Test
    public void testThreeSum_3() {
        int[] nums = {0, 1, 1};
        List<List<Integer>> expected = Arrays.asList();
        assertEquals(expected, solution.threeSum(nums));
    }

    @Test
    public void testThreeSum_standardCase() {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(-1, -1, 2),
                Arrays.asList(-1, 0, 1)
        );
        assertEquals(expected, solution.threeSum(nums));
    }

    @Test
    public void testThreeSum_allZeros() {
        int[] nums = {0, 0, 0};
        List<List<Integer>> expected = Arrays.asList(Arrays.asList(0, 0, 0));
        assertEquals(expected, solution.threeSum(nums));
    }

    @Test
    public void testThreeSum_noSolution() {
        int[] nums = {0, 1, 1};
        List<List<Integer>> expected = Arrays.asList();
        assertEquals(expected, solution.threeSum(nums));
    }

    @Test
    public void testThreeSum_positiveAndNegative() {
        int[] nums = {-2, 0, 1, 1, 2};
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(-2, 0, 2),
                Arrays.asList(-2, 1, 1)
        );
        assertEquals(expected, solution.threeSum(nums));
    }

    @Test
    public void testThreeSum_emptyInput() {
        int[] nums = {};
        List<List<Integer>> expected = Arrays.asList();
        assertEquals(expected, solution.threeSum(nums));
    }

    @Test
    public void testThreeSum_lengthLessThanThree() {
        int[] nums = {1, 2};
        List<List<Integer>> expected = Arrays.asList();
        assertEquals(expected, solution.threeSum(nums));
    }

    @Test
    public void testThreeSum_allPositiveNoSolution() {
        int[] nums = {1, 2, 3, 4};
        List<List<Integer>> expected = Arrays.asList();
        assertEquals(expected, solution.threeSum(nums));
    }

    @Test
    public void testThreeSum_boundaryValues() {
        int[] nums = {-5, 2, 2, 3};
        List<List<Integer>> expected = Arrays.asList(Arrays.asList(-5, 2, 3));
        assertEquals(expected, solution.threeSum(nums));
    }

    @Test
    public void testThreeSum_complexCase() {
        int[] nums = {2, -3, 0, -2, -5, -5, -4, 1, 2, -2, 2, 0, 2, -4, 5, 5, -10};
        List<List<Integer>> expected = Arrays.asList(
            Arrays.asList(-10, 5, 5),
            Arrays.asList(-5, 0, 5),
            Arrays.asList(-4, 2, 2),
            Arrays.asList(-3, -2, 5),
            Arrays.asList(-3, 1, 2),
            Arrays.asList(-2, 0, 2)
        );
        assertEquals(expected, solution.threeSum(nums));
    }
}
