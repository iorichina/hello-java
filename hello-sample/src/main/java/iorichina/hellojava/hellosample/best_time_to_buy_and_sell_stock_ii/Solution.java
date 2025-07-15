package iorichina.hellojava.hellosample.best_time_to_buy_and_sell_stock_ii;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    /**
     * 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
     * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
     * 返回 你能获得的 最大 利润 。
     */
    public static void main(String[] args) {
        //测试案例
        //[7,1,5,3,6,4]
        //[1,2,3,4,5]
        //[7,6,4,3,1]
        int x = new Solution().maxProfit(new int[]{7, 1, 5, 3, 6, 4});
        System.out.println(x);
        assert x == 7 : String.format("测试案例1失败:%d", x);
        int y = new Solution().maxProfit(new int[]{1, 2, 3, 4, 5});
        System.out.println(y);
        assert y == 4 : String.format("测试案例2失败:%d", y);
        int z = new Solution().maxProfit(new int[]{7, 6, 4, 3, 1});
        System.out.println(z);
        assert z == 0 : String.format("测试案例3失败:%d", z);
    }

    /**
     * 1 <= prices.length <= 3 * 104
     * 0 <= prices[i] <= 104
     *
     * @return 最大总利润
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        //1. 定义 左指针、右指针、最大利润变量
        int left = 0, right = 1, maxProfit = 0;
        List<Integer> maxProfits = new ArrayList<>();
        //2. 循环 prices 数组
        for (; right < prices.length; right++) {
            //3. 如果 prices[right] > prices[left]，则计算利润
            if (prices[right] >= prices[right - 1]) {
                maxProfit = Math.max(maxProfit, prices[right] - prices[left]);
            } else {
                left = right;
                if (maxProfit > 0) {
                    maxProfits.add(maxProfit);
                }
                maxProfit = 0;
            }
        }
        if (maxProfit > 0) {
            maxProfits.add(maxProfit);
        }
        return maxProfits.stream().mapToInt(Integer::intValue).sum();
    }
}
