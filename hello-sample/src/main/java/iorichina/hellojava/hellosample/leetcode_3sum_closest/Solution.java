package iorichina.hellojava.hellosample.leetcode_3sum_closest;

import java.util.Arrays;

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int res = nums[0] + nums[1] + nums[2]; // Initialize with the sum of the first three elements
        Arrays.sort(nums);
        int cur = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == cur) {
                continue;
            }
            cur = nums[i];
            int left = i + 1, right = nums.length - 1;
            int sum, valLeft = Integer.MIN_VALUE, valRight = Integer.MAX_VALUE;
            while (left < right) {
                if (valLeft == nums[left]) {
                    left++;
                    continue;
                }
                if (valRight == nums[right]) {
                    right--;
                    continue;
                }
                sum = nums[i] + nums[left] + nums[right];
                if (Math.abs(target - sum) < Math.abs(target - res)) {
                    res = sum;
                }
                if (sum < target) {
                    left++;
                } else if (sum > target) {
                    right--;
                } else {
                    return sum; // Early return if exact match found
                }
            }
        }
        return res;
    }

    public int threeSumClosest0(int[] nums, int target) {
        Integer res = null;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    int sum = nums[i] + nums[j] + nums[k];
                    if (null == res) {
                        res = sum;
                        continue;
                    }
                    if (sum == target) {
                        return sum; // Early return if exact match found
                    }
                    if (Math.abs(sum - target) < Math.abs(res - target)) {
                        res = sum;
                        continue;
                    }
                    if (sum > target) {
                        break;
                    }
                }
            }
        }
        return null == res ? -1 : res;
    }
}