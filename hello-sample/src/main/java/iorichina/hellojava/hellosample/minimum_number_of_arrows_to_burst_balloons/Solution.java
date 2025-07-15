package iorichina.hellojava.hellosample.minimum_number_of_arrows_to_burst_balloons;

import iorichina.hellojava.hellosample.org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int findMinArrowShots(int[][] points) {
        if (points.length == 1) {
            return 1;
        }
        Arrays.sort(points, Comparator.comparingInt(p -> p[1]));
        int left = points[0][0], right = points[0][1], arrows = 1;
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > right) {
                left = points[i][0];
                right = points[i][1];
                arrows++;
                continue;
            }
        }
        return arrows;
    }
}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        /// [[-2147483646,-2147483645],[2147483646,2147483647]]=>2
        {
            int[][] points = {{-2147483646, -2147483645}, {2147483646, 2147483647}};
            int result = solution.findMinArrowShots(points);
            Assert.isTrue(2 == result, "Test failed for points: " + Arrays.deepToString(points));
            System.out.println("Minimum number of arrows to burst balloons: " + result); // Output: 2
        }
        /// [[10,16],[2,8],[1,6],[7,12]]=>2
        {
            int[][] points = {{10, 16}, {2, 8}, {1, 6}, {7, 12}};
            int result = solution.findMinArrowShots(points);
            Assert.isTrue(2 == result, "Test failed for points: " + Arrays.deepToString(points));
            System.out.println("Minimum number of arrows to burst balloons: " + result + " " + Arrays.deepToString(points)); // Output: 2
        }
        /// [[1,2],[3,4],[5,6],[7,8]]=>4
        {
            int[][] points = {{1, 2}, {3, 4}, {5, 6}, {7, 8}};
            int result = solution.findMinArrowShots(points);
            Assert.isTrue(4 == result, "Test failed for points: " + Arrays.deepToString(points));
            System.out.println("Minimum number of arrows to burst balloons: " + result); // Output: 4
        }
        /// [[1,2],[2,3],[3,4],[4,5]]=>2
        {
            int[][] points = {{1, 2}, {2, 3}, {3, 4}, {4, 5}};
            int result = solution.findMinArrowShots(points);
            Assert.isTrue(2 == result, "Test failed for points: " + Arrays.deepToString(points));
            System.out.println("Minimum number of arrows to burst balloons: " + result); // Output: 2
        }
    }
}