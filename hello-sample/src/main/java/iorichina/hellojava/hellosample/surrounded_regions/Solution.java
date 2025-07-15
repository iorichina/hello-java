package iorichina.hellojava.hellosample.surrounded_regions;

class Solution {
    public void solve(char[][] board) {
        // 查边界的值
        // 用dfs，从第一个O开始上下左右找所有的O，标记
        // 再循环所有位更新非标记的O
        //up
        for (int i = 0; i < board.length; i++) {
            if (board[i][0] == 'O') {
                dfs(board, i, 0);
            }
        }
        //down
        for (int i = 0; i < board.length; i++) {
            if (board[i][board[0].length - 1] == 'O') {
                dfs(board, i, board[0].length - 1);
            }
        }
        //left
        for (int i = 0; i < board[0].length; i++) {
            if (board[0][i] == 'O') {
                dfs(board, 0, i);
            }
        }
        //right
        for (int i = 0; i < board[0].length; i++) {
            if (board[board.length - 1][i] == 'O') {
                dfs(board, board.length - 1, i);
            }
        }
        //将O设置为X，将.设置为O
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == '.') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    void dfs(char[][] board, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] == '.' || board[i][j] == 'X') {
            return;
        }
        board[i][j] = '.';
        dfs(board, i - 1, j);
        dfs(board, i + 1, j);
        dfs(board, i, j - 1);
        dfs(board, i, j + 1);
    }
}
