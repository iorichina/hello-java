package iorichina.hellojava.hellosample.remove_element;

import java.util.Arrays;

public class Solution {
    /**
     * 0 <= nums.length <= 100
     * 0 <= nums[i] <= 50
     * 0 <= val <= 100
     * 
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        if (val > 50) {
            return nums.length;
        }
        if (nums.length == 0) {
            return 0;

        }
        int k = 0;
        for (int idx = 0; idx < nums.length; idx++) {
            if (nums[idx] == val) {
                nums[idx] = 100 + nums[idx];
                k++;
            }
        }
        Arrays.sort(nums);
        return nums.length - k;
    }
}
