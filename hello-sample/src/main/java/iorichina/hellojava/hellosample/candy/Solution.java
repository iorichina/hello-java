package iorichina.hellojava.hellosample.candy;

import iorichina.hellojava.hellosample.org.springframework.util.Assert;

import java.util.Arrays;

class Solution {
    public static void main(String[] args) {

        Solution solution = new Solution();
        /// [1,2,3,1,0]=>9
        {
            int[] ratings = {1, 2, 3, 1, 0};
            int res = solution.candy(ratings);
            System.out.println(res); // 输出: 9
            Assert.isTrue(9 == res, "9!=" + res + "\n" + Arrays.toString(ratings));
        }
        /// [1,3,2,2,1]=>7
        {
            int[] ratings = {1, 3, 2, 2, 1};
            int res = solution.candy(ratings);
            System.out.println(res); // 输出: 7
            Assert.isTrue(7 == res, "7!=" + res + "\n" + Arrays.toString(ratings));
        }
    }

    public int candy(int[] ratings) {
        //只有一个孩子
        if (ratings.length == 1) {
            return 1;
        }
        //两个孩子评分不相等至少3个
        if (ratings.length == 2) {
            return ratings[0] == ratings[1] ? 2 : 3;
        }

        //只循环一遍，轮到的人先分配最少的糖果
        int total = 1;
        //下一个孩子分数更低，上一个孩子需要分多一个糖果，上上一个。。。
        //可以记录连续有多少个孩子分数下降，比如记住最开始连续下降孩子的坐标
        int left = 0;
        //连续上升时，需要分配比上一个小孩多一个糖果，记录上一个小孩的糖果数
        int lastCandy = 1;
        int[] candy = new int[ratings.length];
        candy[0] = 1; //第一个孩子分配1个糖果
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] < ratings[i - 1]) {//连续下降
                candy[i] = 1; //当前孩子分配1个糖果
                total += candy[i];
                for (int right = i; right > left; right--) {//有可能出现糖果数跳跃式下降，所以需要循环
                    if (candy[right - 1] - candy[right] < 1) {
                        candy[right - 1]++; //上一个孩子需要分多一个糖果，上上一个。。。
                        total += 1;//上一个孩子需要分多一个糖果，上上一个。。。
                        continue;
                    }
                    break;
                }
                lastCandy = 1;//重置上一个孩子的糖果数
                continue;
            }
            left = i;//连续下降中断
            if (ratings[i] > ratings[i - 1]) {//连续上升
                candy[i] = lastCandy + 1; //当前孩子分配上一个孩子的糖果数+1
                total += candy[i];
                lastCandy++;//上一个孩子的糖果数+1
                continue;
            }
            candy[i] = 1;//不升不降，轮到的孩子分配最少糖果
            total += candy[i];
            lastCandy = 1;//重置上一个孩子的糖果数
        }
        return total;
    }
}