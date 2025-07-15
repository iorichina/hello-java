package iorichina.hellojava.hellosample.surrounded_regions;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        /// [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
        {
            char[][] board = {
                    {'X', 'X', 'X', 'X'},
                    {'X', 'O', 'O', 'X'},
                    {'X', 'X', 'O', 'X'},
                    {'X', 'O', 'X', 'X'}
            };
            solution.solve(board);
            for (char[] chars : board) {
                for (char aChar : chars) {
                    System.out.print(aChar + " ");
                }
                System.out.println();
            }
        }
    }


    public void solve(char[][] board) {
        //用贪心算法，从第一个O开始上下左右找所有的O
        //如果这个O区域没有连接边缘，则全部置为X
        boolean[][] visited = new boolean[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (visited[i][j]) {
                    continue;
                }
                if (board[i][j] == 'O') {
                    List<int[]> isO = new ArrayList<>();
                    boolean escape = find(board, visited, isO, i, j);
                    if (!escape) {
                        for (int[] ints : isO) {
                            board[ints[0]][ints[1]] = 'X';
                        }
                    }
                }
            }
        }
    }

    boolean find(char[][] board, boolean[][] visited, List<int[]> isO, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return true;
        }
        if (visited[i][j] || board[i][j] == 'X') {
            return false;
        }
        visited[i][j] = true;
        isO.add(new int[]{i, j});
        boolean left = find(board, visited, isO, i - 1, j);
        boolean right = find(board, visited, isO, i + 1, j);
        boolean up = find(board, visited, isO, i, j - 1);
        boolean down = find(board, visited, isO, i, j + 1);
        return left || right || up || down;
    }
}
