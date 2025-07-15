package iorichina.hellojava.hellosample.container_with_most_water;

import iorichina.hellojava.hellosample.org.springframework.util.Assert;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        /// [1,0,0,0,0,0,0,2,2]
        int[] height0 = {1, 0, 0, 0, 0, 0, 0, 2, 2};
        int result0 = solution.maxArea(height0);
        System.out.println(result0); // Output: 8
        Assert.isTrue(8 == result0, "8!= " + result0);
        /// [1,8,6,2,5,4,8,3,7]
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int result = solution.maxArea(height);
        System.out.println(result); // Output: 49
        Assert.isTrue(49 == result, "49 != " + result);
        /// [1,1]
        height = new int[]{1, 1};
        result = solution.maxArea(height);
        System.out.println(result); // Output: 1
        Assert.isTrue(1 == result, "1 != " + result);
        /// [4,3,2,1,4]
        height = new int[]{4, 3, 2, 1, 4};
        result = solution.maxArea(height);
        System.out.println(result); // Output: 16
        Assert.isTrue(16 == result, "16 != " + result);
        /// [1,2,1]
        height = new int[]{1, 2, 1};
        result = solution.maxArea(height);
        System.out.println(result); // Output: 2
        Assert.isTrue(2 == result, "2 != " + result);
        /// [2,3,4,5,18,17,6]
        height = new int[]{2, 3, 4, 5, 18, 17, 6};
        result = solution.maxArea(height);
        System.out.println(result); // Output: 17
        Assert.isTrue(17 == result, "17 != " + result);
        /// [1,3,2,5,25,24,5]6/15/5|15/8/12|15/6/9
        height = new int[]{1, 3, 2, 5, 25, 24, 5};
        result = solution.maxArea(height);
        System.out.println(result); // Output: 24
        Assert.isTrue(24 == result, "24!= " + result);
    }

    /// n == height.length
    /// 2 <= n <= 105
    /// 0 <= height[i] <= 104
    public int maxArea(int[] height) {
        if (height.length == 2) {
            return Math.min(height[0], height[1]);
        }
        //first and last index and their area
        int left = 0, right = height.length - 1;
        int maxArea = 0;
        //loop until left and right meet
        int area = 0;
        while (left < right) {
            area = Math.min(height[left], height[right]) * Math.abs(right - left);
            if (area > maxArea) {
                maxArea = area;
            }
            if (height[left] < height[right]) {
                int leftHeight = height[left];
                while (left < right && height[left] <= leftHeight) {
                    left++;
                }
            } else {
                int rightHeight = height[right];
                while (left < right && height[right] <= rightHeight) {
                    right--;
                }
            }
        }
        return maxArea;
    }

    public int maxArea2(int[] height) {
        if (height.length == 2) {
            return Math.min(height[0], height[1]);
        }
        int[][] sortedHeight = new int[height.length][2];
        for (int i = 0; i < height.length; i++) {
            sortedHeight[i][0] = height[i];
            sortedHeight[i][1] = i;
        }
        Arrays.sort(sortedHeight, (a, b) -> b[0] - a[0]);
        int left = 0, right = 1;
        int maxArea = sortedHeight[right][0] * Math.abs(sortedHeight[left][1] - sortedHeight[right][1]);
        //loop until left and right meet
        int area = 0, areaLeft = 0, areaRight = 0;
        while (right < height.length - 1) {
            right++;
            areaRight = sortedHeight[right][0] * Math.abs(sortedHeight[left][1] - sortedHeight[right][1]);
            if (areaRight > maxArea) {
                maxArea = areaRight;
                continue;
            }
            left++;
            areaLeft = sortedHeight[right][0] * Math.abs(sortedHeight[left][1] - sortedHeight[right][1]);
            if (areaLeft > maxArea) {
                maxArea = areaLeft;
                continue;
            }
            break;
        }
        return maxArea;
    }

    public int maxArea1(int[] height) {
        if (height.length == 2) {
            return Math.min(height[0], height[1]);
        }
        //first and last index and their area
        int left = 0, right = height.length - 1;
        int maxArea = Math.min(height[left], height[right]) * Math.abs(right - left);
        //loop until left and right meet
        int areaLeft = 0, areaRight = 0;
        while (left < right) {
            areaLeft = Math.min(height[left + 1], height[right]) * Math.abs(right - left - 1);
            areaRight = Math.min(height[left], height[right - 1]) * Math.abs(right - left - 1);
            if (areaLeft <= maxArea && areaRight <= maxArea) {
                if (areaLeft > areaRight) {
                    left++;
                } else {
                    right--;
                }
                continue;
            }
            if (areaLeft > maxArea) {
                maxArea = areaLeft;
                left++;
            } else {
                maxArea = areaRight;
                right--;
            }
        }
        return maxArea;
    }

    public int maxArea0(int[] height) {
        if (height.length == 2) {
            return Math.min(height[0], height[1]);
        }
        int maxArea = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int area = Math.min(height[i], height[j]) * Math.abs(j - i);
                if (area > maxArea) {
                    maxArea = area;
                }
            }
        }
        return maxArea;
    }
}
