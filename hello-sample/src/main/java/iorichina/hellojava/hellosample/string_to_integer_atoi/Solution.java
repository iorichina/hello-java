package iorichina.hellojava.hellosample.string_to_integer_atoi;

import iorichina.hellojava.hellosample.org.springframework.util.Assert;

import java.math.BigDecimal;

class Solution {
    public static void main(String[] args) {
        //
//        0 <= s.length <= 200
//        s 由英文字母（大写和小写）、数字（0-9）、' '、'+'、'-' 和 '.' 组成
        Solution solution = new Solution();
        String s = "42";
        int i = solution.myAtoi(s);
        System.out.println(i); // 输出: 42
        Assert.isTrue(42 == i, "测试失败，预期值为42，实际值为" + i);

        s = "   -042";
        i = solution.myAtoi(s);
        System.out.println(i); // 输出: -42
        Assert.isTrue(-42 == i, "测试失败，预期值为-42，实际值为" + i);

        s = "419 with words";
        i = solution.myAtoi(s);
        System.out.println(i); // 输出: 419
        Assert.isTrue(419 == i, "测试失败，预期值为419，实际值为" + i);

        s = "words and 987";
        i = solution.myAtoi(s);
        System.out.println(i); // 输出: 0
        Assert.isTrue(0 == i, "测试失败，预期值为0，实际值为" + i);

        s = "-91283472332";
        i = solution.myAtoi(s);
        System.out.println(i); // 输出: -2147483648
        Assert.isTrue(Integer.MIN_VALUE == i, "测试失败，预期值为" + Integer.MIN_VALUE + "，实际值为" + i);

        s = "+-12";
        i = solution.myAtoi(s);
        System.out.println(i);
        Assert.isTrue(0 == i, "测试失败，预期值为0，实际值为" + i);
    }

    /**
     * 函数 myAtoi(string s) 的算法如下：
     * <p>
     * 空格：读入字符串并丢弃无用的前导空格（" "）
     * 符号：检查下一个字符（假设还未到字符末尾）为 '-' 还是 '+'。如果两者都不存在，则假定结果为正。
     * 转换：通过跳过前置零来读取该整数，直到遇到非数字字符或到达字符串的结尾。如果没有读取数字，则结果为0。
     * 舍入：如果整数数超过 32 位有符号整数范围 [−231,  231 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231 的整数应该被舍入为 −231 ，大于 231 − 1 的整数应该被舍入为 231 − 1 。
     *
     * @param s 输入字符串
     * @return 转换后的整数值
     */
    public int myAtoi(String s) {
        if (null == s) {
            return 0; // 如果字符串为null，返回0
        }
        s = s.trim();
        if (s.isEmpty()) {
            return 0; // 如果字符串为空，返回0
        }
        int startIdx = 0, maxIdx = 0;
        if (s.charAt(0) == '+' || s.charAt(0) == '-') {
            maxIdx++;
            startIdx = 1;
        }
        for (int i = startIdx; i < s.length(); i++) {
            if ('0' <= s.charAt(i) && s.charAt(i) <= '9') {
                maxIdx++;
            } else {
                break;
            }
        }
        if (maxIdx <= startIdx) {
            return 0; // 没有数字字符，返回0
        }
        BigDecimal d = new BigDecimal(s.substring(0, maxIdx));
        if (d.compareTo(new BigDecimal(Integer.MIN_VALUE)) < 0) {
            return Integer.MIN_VALUE; // 小于最小值，返回最小值
        } else if (d.compareTo(new BigDecimal(Integer.MAX_VALUE)) > 0) {
            return Integer.MAX_VALUE; // 大于最大值，返回最大值
        } else {
            return d.intValue(); // 返回转换后的整数值
        }
    }
}