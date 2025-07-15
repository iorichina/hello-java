package iorichina.hellojava.hellosample.leetcode_3sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        if (null == nums || nums.length < 3) {
            return List.of();
        }
        Arrays.sort(nums);
        if (nums[0] > 0) {
            return List.of();
        }
        List<List<Integer>> result = new ArrayList<>();
        int cur = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == cur) {
                continue;
            }
            cur = nums[i];
            int left = i + 1, right = nums.length - 1;
            int valLeft = Integer.MIN_VALUE, valRight = Integer.MAX_VALUE;
            while (left < right) {
                if (nums[left] == valLeft) {
                    left++;
                    continue;
                }
                if (nums[right] == valRight) {
                    right--;
                    continue;
                }
                int val = cur + nums[left] + nums[right];
                if (val == 0) {
                    valLeft = nums[left];
                    valRight = nums[right];
                    result.add(List.of(cur, nums[left], nums[right]));
                    right--;
                    left++;
                    continue;
                }
                if (val < 0) {
                    left++;
                    continue;
                }
                right--;
            }
        }
        return result;
    }
}