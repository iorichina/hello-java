package iorichina.hellojava.hellosample.remove_duplicates_from_sorted_array;

class Solution {
    public static void main(String[] args) {
        int[] nums = { 1, 1, 2 }; // 输入数组
        int[] expectedNums = { 1, 2 }; // 长度正确的期望答案

        int k = new Solution().removeDuplicates(nums); // 调用

        assert k == expectedNums.length;
        for (int i = 0; i < k; i++) {
            assert nums[i] == expectedNums[i];
        }
    }

    /**
     * 1 <= nums.length <= 3 * 104
     * -104 <= nums[i] <= 104
     * nums 已按 非严格递增 排列
     */
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int uniqueCount = 1; // 记录唯一元素的数量
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[uniqueCount - 1]) {
                nums[uniqueCount] = nums[i];
                uniqueCount++;
            }
        }
        return uniqueCount;
    }
}