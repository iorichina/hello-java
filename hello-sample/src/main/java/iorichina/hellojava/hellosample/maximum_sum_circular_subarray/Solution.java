package iorichina.hellojava.hellosample.maximum_sum_circular_subarray;

class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        int n = nums.length;
        if (1 == n) {
            return nums[0];
        }

        int maxSumLeft = nums[0];
        int maxSum = nums[0];

        int sumLeft = nums[0];
        int[] maxLeft = new int[n];
        maxLeft[0] = nums[0];
        for (int i = 1; i < n; i++) {
            maxSumLeft = Math.max(maxSumLeft + nums[i], nums[i]);
            maxSum = Math.max(maxSum, maxSumLeft);

            sumLeft += nums[i];
            maxLeft[i] = Math.max(maxLeft[i - 1], sumLeft);
        }
        int sumRight = 0;
        for (int i = n - 1; i > 0; i--) {
            sumRight += nums[i];
            maxSum = Math.max(maxSum, maxLeft[i - 1] + sumRight);
        }
        return maxSum;
    }

    public int maxSubarraySumCircular0(int[] nums) {
        if (1 == nums.length) {
            return nums[0];
        }
        int maxSum = nums[0];
        for (int i = 0; i < nums.length; i++) {
            int sum = nums[i];
            int max = nums[i];
            for (int j = 1; j < nums.length; j++) {
                int index = (i + j) % nums.length;
                sum += nums[index];
                max = Math.max(max, sum);
            }
            maxSum = Math.max(maxSum, max);
        }
        return maxSum;
    }
}