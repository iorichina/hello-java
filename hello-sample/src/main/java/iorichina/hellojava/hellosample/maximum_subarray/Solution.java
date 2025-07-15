package iorichina.hellojava.hellosample.maximum_subarray;

class Solution {
    public int maxSubArray(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int maxSum = dp[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
            if (dp[i] > maxSum) {
                maxSum = dp[i];
            }

        }
        return maxSum;
    }

    public int maxSubArray0(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int maxSum = Integer.MIN_VALUE, maxVal = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > maxVal) {
                maxVal = nums[i];
            }
            int sum = nums[i];
            if (sum > maxSum) {
                maxSum = sum;
            }
            for (int j = i + 1; j < nums.length; j++) {
                sum += nums[j];
                if (sum > maxSum) {
                    maxSum = sum;
                }
            }
        }
        return maxSum;
    }
}