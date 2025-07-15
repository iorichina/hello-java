package iorichina.hellojava.hellosample.remove_duplicates_from_sorted_array_ii;

import java.util.Arrays;

public class Solution {
    /**
     * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使得出现次数超过两次的元素只出现两次 ，返回删除后数组的新长度。
     * 
     * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     */
    public static void main(String[] args) {
        int[] nums = new int[] { 0, 0, 1, 1, 1, 1, 2, 3, 3 };
        // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
        int len = new Solution().removeDuplicates(nums);
        System.out.println(len);
        System.out.println(Arrays.toString(nums));

        // 在函数里修改输入数组对于调用者是可见的。
        // 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
        int i = 0;
        for (; i < len-1; i++) {
            System.out.printf("%d,", nums[i]);
        }
        if (i <= nums.length - 1) {
            System.out.println(nums[i]);
        }
    }

    /**
     * 1 <= nums.length <= 3 * 104
     * -104 <= nums[i] <= 104
     * nums 已按升序排列
     * 
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 2) {
            return nums.length;

        }
        int idxLeft = 1, idxRight = 2;
        do {
            if (nums[idxLeft] != nums[idxRight] || nums[idxLeft - 1] != nums[idxRight]) {
                // 如果不相等，说明idxRight指向的元素是可以保留的
                // 将idxRight指向的元素放到idxLeft位置
                nums[++idxLeft] = nums[idxRight];
            }
            // idxRight向右移动
            idxRight++;
        }while (idxRight < nums.length);

        return idxLeft+1; // 返回的是数组的长度，所以需要加1
    }
}
