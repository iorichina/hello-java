package iorichina.hellojava.hellosample.leetcode_3sum_closest;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SolutionTest {

    @Test
    public void threeSumClosest_returnsClosestSum_whenTargetIsInRange() {
        Solution solution = new Solution();
        int[] nums = {-1, 2, 1, -4};
        int target = 1;
        assertEquals(2, solution.threeSumClosest(nums, target));
    }

    @Test
    public void threeSumClosest_returnsClosestSum_whenAllNumbersArePositive() {
        Solution solution = new Solution();
        int[] nums = {1, 2, 3, 4};
        int target = 6;
        assertEquals(6, solution.threeSumClosest(nums, target));
    }

    @Test
    public void threeSumClosest_returnsClosestSum_whenAllNumbersAreNegative() {
        Solution solution = new Solution();
        int[] nums = {-8, -6, -5, -3};
        int target = -10;
        assertEquals(-14, solution.threeSumClosest(nums, target));
    }

    @Test
    public void threeSumClosest_returnsClosestSum_whenArrayContainsDuplicates() {
        Solution solution = new Solution();
        int[] nums = {1, 1, 1, 0};
        int target = 100;
        assertEquals(3, solution.threeSumClosest(nums, target));
    }

    @Test
    public void threeSumClosest_returnsClosestSum_whenArrayHasOnlyThreeElements() {
        Solution solution = new Solution();
        int[] nums = {0, 2, 1};
        int target = 3;
        assertEquals(3, solution.threeSumClosest(nums, target));
    }

    @Test
    public void threeSumClosest_returnsClosestSum_whenTargetIsSmallerThanAllSums() {
        Solution solution = new Solution();
        int[] nums = {5, 6, 7, 8};
        int target = 1;
        assertEquals(18, solution.threeSumClosest(nums, target));
    }

    @Test
    public void threeSumClosest_returnsClosestSum_whenTargetIsLargerThanAllSums() {
        Solution solution = new Solution();
        int[] nums = {-10, -8, -6, -4};
        int target = 0;
        assertEquals(-18, solution.threeSumClosest(nums, target));
    }

    @Test
    public void threeSumClosest_returnsClosestSum_whenArrayContainsZeros() {
        Solution solution = new Solution();
        int[] nums = {0, 0, 0};
        int target = 1;
        assertEquals(0, solution.threeSumClosest(nums, target));
    }

    @Test
    public void threeSumClosest_returnsClosestSum_whenArrayContainsLargeNumbers() {
        Solution solution = new Solution();
        int[] nums = {1000, 2000, 3000, -1000};
        int target = 5000;
        assertEquals(6000, solution.threeSumClosest(nums, target));
    }

    @Test
    public void threeSumClosest_returnsClosestSum_whenTargetIsMuchSmallerThanAllSums() {
        Solution solution = new Solution();
        int[] nums = {10, 20, 30, 40, 50, 60, 70, 80, 90};
        int target = 1;
        assertEquals(60, solution.threeSumClosest(nums, target));
    }
}
