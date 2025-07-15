package iorichina.hellojava.hellosample.gas_station;

import iorichina.hellojava.hellosample.org.springframework.util.Assert;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        /// [3,3,4]+[3,4,4]=>-1
        {
            int[] gas = {3, 3, 4};
            int[] cost = {3, 4, 4};
            int res = solution.canCompleteCircuit(gas, cost);
            System.out.println(res); // 输出: -1
            Assert.isTrue(-1 == res, "-1!=" + res);
        }
        /// [1,2,3,4,5]+[3,4,5,1,2]=>3
        {
            int[] gas = {1, 2, 3, 4, 5};
            int[] cost = {3, 4, 5, 1, 2};
            int res = solution.canCompleteCircuit(gas, cost);
            System.out.println(res); // 输出: 3
            Assert.isTrue(3 == res, "3!=" + res);
        }
        /// [2,3,4]+[3,4,3]=>-1
        {
            int[] gas2 = {2, 3, 4};
            int[] cost2 = {3, 4, 3};
            int res = solution.canCompleteCircuit(gas2, cost2);
            System.out.println(res);
            Assert.isTrue(-1 == res, "-1!=" + res);
        }
    }

    /// 在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
    ///
    /// 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
    ///
    /// 给定两个整数数组 gas 和 cost ，如果你可以按顺序绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。如果存在解，则 保证 它是 唯一 的。
    ///
    /// ```
    /// 提示:
    /// n == gas.length == cost.length
    /// 1 <= n <= 105
    /// 0 <= gas[i], cost[i] <= 104
    /// 输入保证答案唯一。
    ///```
    /// 输入: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
    /// 输出: 3
    ///
    /// 输入: gas = [2,3,4], cost = [3,4,3]
    /// 输出: -1
    ///
    /// [3,3,4]+[3,4,4]=>-1
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas.length == 1) {
            return gas[0] >= cost[0] ? 0 : -1;
        }
        int totalStation = gas.length;
        for (int i = 0; i < totalStation; ) {
            int pos = i;
            int fuel = gas[pos];
            int pass = 1;
            while (true) {
                fuel = fuel - cost[pos];//前往新站点所消耗汽油
                if (fuel < 0) {
                    break;//无法到达下一个站点
                }
                pass++;//可以到达下一个站点
                if (pass > totalStation) {//已经回到起点
                    return i;
                }
                pos = (pos + 1) % totalStation;//新站点索引
                fuel = fuel + gas[pos];//在新站点加油
            }
            i = pass + i;//从无法到达的下一个站点重新开始
        }
        return -1;
    }

    /// 超时
    public int canCompleteCircuit0(int[] gas, int[] cost) {
        if (gas.length == 1) {
            return gas[0] >= cost[0] ? 0 : -1;
        }
        //获得所有可以作为起点的点
        int totalStation = gas.length;
        int[] starts = new int[totalStation];
        int startLen = 0;
        for (int i = 0; i < totalStation; i++) {
            if (gas[i] >= cost[i]) {
                starts[startLen++] = i;
            }
        }
        //
        for (int i = 0; i < startLen; i++) {
            final int start = starts[i];
            int fuel = gas[start];
            int pos = start % totalStation;
            int pass = 0;
            while (fuel >= cost[pos]) {//可以前往下一站
                fuel = fuel - cost[pos];//消耗汽油
                pass++;//到达新站点
                if (pass >= totalStation) {//已经回到起点
                    return start;
                }
                pos = (pos + 1) % totalStation;//新站点索引
                fuel = fuel + gas[pos];//在新站点加油
            }
        }
        return -1;
    }
}
