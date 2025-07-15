package iorichina.hellojava.hellosample.merge_sorted_arrays;

import java.util.Arrays;

class Solution {
    public static void main(String[] args) {
        int[] nums1 = { 1, 2, 3, 0, 0, 0 };
        int m1 = 3;
        int[] nums2 = { 2, 5, 6 };
        int n1 = 3;
        System.out.print(Arrays.toString(nums1));
        System.out.print(Arrays.toString(nums2));
        new Solution().merge(nums1, m1, nums2, n1);
        System.out.println(Arrays.toString(nums1));

        int[] nums3 = { 1 };
        int m2 = 1;
        int[] nums4 = {};
        int n2 = 0;
        System.out.print(Arrays.toString(nums3));
        System.out.print(Arrays.toString(nums4));
        new Solution().merge(nums3, m2, nums4, n2);
        System.out.println(Arrays.toString(nums3));

        int[] nums5 = { 0 };
        int m3 = 0;
        int[] nums6 = { 1 };
        int n3 = 1;
        System.out.print(Arrays.toString(nums5));
        System.out.print(Arrays.toString(nums6));
        new Solution().merge(nums5, m3, nums6, n3);
        System.out.println(Arrays.toString(nums5));

        int[] nums7 = { 0, 0, 1, 1, 0, 0, 0 };
        int m4 = 4;
        int[] nums8 = { 2, 5, 6 };
        int n4 = 3;
        System.out.print(Arrays.toString(nums7));
        System.out.print(Arrays.toString(nums8));
        new Solution().merge(nums7, m4, nums8, n4);
        System.out.println(Arrays.toString(nums7));

    }

    /**
     * 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
     * <p>
     * nums1.length == m + n
     * nums2.length == n
     * 0 <= m, n <= 200
     * 1 <= m + n <= 200
     * -109 <= nums1[i], nums2[j] <= 109
     * <p>
     * 进阶：你可以设计实现一个时间复杂度为 O(m + n) 的算法解决此问题吗？
     *
     * @param nums1 非递减顺序 排列的整数数组
     * @param m     nums1.length
     * @param nums2 非递减顺序 排列的整数数组
     * @param n     nums2.length
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) {
            return;
        }
        if (m == 0) {
            System.arraycopy(nums2, 0, nums1, 0, n);
            return;
        }
        int indexNums1 = 0, indexNums2 = 0;
        for (; indexNums2 < n; indexNums2++) {
            for (; indexNums1 < m; indexNums1++) {
                if (nums2[indexNums2] >= nums1[indexNums1]) {
                    continue;
                }
                System.arraycopy(nums1, indexNums1, nums1, indexNums1 + 1, m - indexNums1);
                nums1[indexNums1] = nums2[indexNums2];
                m++;
                break;
            }
            if (indexNums1 >= m) {
                System.arraycopy(nums2, indexNums2, nums1, indexNums1, n - indexNums2);
                break;
            }
        }
    }

    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        System.arraycopy(nums2, 0, nums1, m, n);
        Arrays.sort(nums1);
    }
}