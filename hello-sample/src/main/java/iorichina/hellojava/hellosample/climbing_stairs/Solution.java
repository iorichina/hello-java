package iorichina.hellojava.hellosample.climbing_stairs;

class Solution {
    public int climbStairs(int n) {
        //爬一层有一种方法
        int p1 = 1;
        if (1 == n) {
            return p1;
        }
        //爬两层有两种方法 1+1 / 2
        int p2 = 2;
        if (2 == n) {
            return p2;
        }
        int p = 0;
        //从第三层开始，爬到第n层的方法数等于爬到第n-1层和第n-2层的方法数之和 = p1 + p2 => p1=p2,p2=p1+p2
        for (int i = 3; i <= n; i++) {
            p = p1 + p2;
            p1 = p2;
            p2 = p;
        }
        return p;
    }
}

/// 会超时
class Solution0 {
    public int climbStairs(int n) {
        return solve(0, n);
    }

    private int solve(int startAt, int n) {
        if (startAt > n) {
            return 0;
        }
        //爬到顶表示路径通达，返回1种路径
        if (startAt == n) {
            return 1;
        }
        int m = 0;
        //从startAt位置开始，向上爬1步或2步
        m += solve(startAt + 1, n);
        m += solve(startAt + 2, n);
        return m;
    }
}