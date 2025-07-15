package iorichina.hellojava.hellosample.minimum_size_subarray_sum;

class Solution {
    /// 给定一个含有 n 个正整数的数组和一个正整数 target 。
    ///
    /// 找出该数组中满足其总和大于等于 target 的长度最小的 子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
    public int minSubArrayLen(int target, int[] nums) {
        if (nums.length == 1) {
            return target <= nums[0] ? 1 : 0;
        }
        int total = 0, len = Integer.MAX_VALUE, left = 0, right = 0;
        while (left <= right && right < nums.length) {
            total = total + nums[right];
            if (total >= target) {
                len = Math.min(len, right - left + 1);
                if (len == 1) {
                    return len;
                }
                while (left <= right && total >= target) {
                    total -= nums[left];
                    left++;
                    if (total >= target) {
                        len = Math.min(len, right - left + 1);
                    }
                    if (len == 1) {
                        return len;
                    }
                }
            }
            right++;
        }
        return len == Integer.MAX_VALUE ? 0 : len;
    }
}