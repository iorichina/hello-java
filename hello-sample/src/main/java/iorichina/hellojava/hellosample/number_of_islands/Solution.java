package iorichina.hellojava.hellosample.number_of_islands;

class Solution {
    /// m == grid.length
    /// n == grid[i].length
    /// 1 <= m, n <= 300
    /// grid[i][j] 的值为 '0' 或 '1'
    public int numIslands(char[][] grid) {
        int numIslands = 0;
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return numIslands;
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // 如果找到一个岛屿
                if (grid[i][j] == '1') {
                    numIslands++;
                    // 使用 DFS 标记整个岛屿为已访问
                    find(grid, i, j);
                }
            }
        }

        return numIslands;
    }

    void find(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0') {
            return;
        }
        // 标记为已访问
        grid[i][j] = '0';
        // 递归访问四个方向
        find(grid, i - 1, j); // 上
        find(grid, i + 1, j); // 下
        find(grid, i, j - 1); // 左
        find(grid, i, j + 1); // 右
    }
}