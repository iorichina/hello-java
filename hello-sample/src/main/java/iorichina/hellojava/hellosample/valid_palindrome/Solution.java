package iorichina.hellojava.hellosample.valid_palindrome;

import iorichina.hellojava.hellosample.org.springframework.util.Assert;

public class Solution {
    /// 示例 1：
    ///
    /// 输入: s = "A man, a plan, a canal: Panama"
    /// 输出：true
    /// 解释："amanaplanacanalpanama" 是回文串。
    /// 示例 2：
    ///
    /// 输入：s = "race a car"
    /// 输出：false
    /// 解释："raceacar" 不是回文串。
    /// 示例 3：
    ///
    /// 输入：s = " "
    /// 输出：true
    /// 解释：在移除非字母数字字符之后，s 是一个空字符串 "" 。
    /// 由于空字符串正着反着读都一样，所以是回文串。
    public static void main(String[] args) {
        Solution solution = new Solution();

        boolean palindrome = solution.isPalindrome("A man, a plan, a canal: Panama");
        Assert.isTrue(palindrome, "A man, a plan, a canal: Panama");
    }

    /// 如果在将所有大写字符转换为小写字符、并移除所有非字母数字字符之后，短语正着读和反着读都一样。则可以认为该短语是一个 回文串 。
    ///
    /// 字母和数字都属于字母数字字符。
    ///
    /// 给你一个字符串 s，如果它是 回文串 ，返回 true ；否则，返回 false 。
    ///
    /// 1 <= s.length <= 2 * 105
    ///
    /// s 仅由可打印的 ASCII 字符组成
    public boolean isPalindrome(String s) {
        if (null == s) {
            return true;
        }
        s = s.trim().toLowerCase();
        if (s.isEmpty()) {
            return true;
        }
        int length = 0;
        char[] chars = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9')) {
                chars[length++] = c;
            }
        }
        System.out.println(new String(chars, 0, length));
        if (length <= 1) {
            return true;
        }
        int haftLength = length / 2;
        for (int i = 0; i < haftLength; i++) {
            if (chars[i] != chars[length - 1 - i]) {
                return false;
            }
        }
        return true;
    }
}
