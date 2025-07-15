package iorichina.hellojava.hellosample.spiral_matrix;

import java.util.ArrayList;
import java.util.List;
///给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
//
//
//
//示例 1：
//
//
//输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
//输出：[1,2,3,6,9,8,7,4,5]
//示例 2：
//
//
//输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
//输出：[1,2,3,4,8,12,11,10,9,5,6,7]
//
//
//提示：
//
//m == matrix.length
//n == matrix[i].length
//1 <= m, n <= 10
//-100 <= matrix[i][j] <= 100

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        // 设定上下左右四个边界
        int top = 0, bottom = matrix.length - 1, left = 0, right = matrix[0].length - 1;
        //设定方向
        int direction = 0;
        List<Integer> result = new ArrayList<>();
        while (top <= bottom && left <= right) {
            switch (direction) {
                case 0://left to right
                    for (int i = left; i <= right; i++) {
                        result.add(matrix[top][i]);
                    }
                    top++;
                    direction = (direction + 1) % 4;
                    break;
                case 1://top to bottom
                    for (int i = top; i <= bottom; i++) {
                        result.add(matrix[i][right]);
                    }
                    right--;
                    direction = (direction + 1) % 4;
                    break;
                case 2://right to left
                    for (int i = right; i >= left; i--) {
                        result.add(matrix[bottom][i]);
                    }
                    bottom--;
                    direction = (direction + 1) % 4;
                    break;
                case 3://bottom to top
                    for (int i = bottom; i >= top; i--) {
                        result.add(matrix[i][left]);
                    }
                    left++;
                    direction = (direction + 1) % 4;
                    break;
            }
        }
        return result;
    }
}