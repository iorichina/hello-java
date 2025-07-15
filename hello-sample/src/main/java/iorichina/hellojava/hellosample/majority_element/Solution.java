package iorichina.hellojava.hellosample.majority_element;

import java.util.Arrays;

public class Solution {
    /**
     * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     * @param args
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {2,2,1,1,1,2,2};
        int result = solution.majorityElement(nums);
        System.out.println("Majority Element: " + result); // Expected output: 2
        assert result == 3 : "Test failed, expected 2 but got " + result;
    }
    /**
     * n == nums.length
     * 1 <= n <= 5 * 104
     * -109 <= nums[i] <= 109
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        int base = nums.length/2;
        Arrays.sort(nums);
        int k=0,num=nums[0],idx=-1;
        for (int i = 0; i < nums.length; i++) {
            if (idx >= 0) {
                if (num == nums[i]) {
                    k++;
                    continue;
                }else {
                    idx = -1;
                }
            }
            if (i+base-1 < nums.length && nums[i] == nums[i+base-1]) {
                num = nums[i];
                k ++;
                idx = i;
            }
        }
        return k;
    }
}
