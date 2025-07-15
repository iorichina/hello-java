package iorichina.hellojava.hellosample.longest_substring_without_repeating_characters;

import iorichina.hellojava.hellosample.org.springframework.util.Assert;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int res;
        /// "dvdf"=>3
        res = solution.lengthOfLongestSubstring("dvdf");
        Assert.isTrue(3 == res, "3!=" + res);
        /// "abcabcbb"=>3
        res = solution.lengthOfLongestSubstring("abcabcbb");
        Assert.isTrue(3 == res, "3!=" + res);
        /// "bbbbb"=>1
        res = solution.lengthOfLongestSubstring("bbbbb");
        Assert.isTrue(1 == res, "1!=" + res);
        /// "pwwkew"=>3
        res = solution.lengthOfLongestSubstring("abcabcbb");
        Assert.isTrue(3 == res, "3!=" + res);
        /// "a"=>1
        res = solution.lengthOfLongestSubstring("a");
        Assert.isTrue(1 == res, "1!=" + res);
        /// "ab"=>2
        res = solution.lengthOfLongestSubstring("ab");
        Assert.isTrue(2 == res, "2!=" + res);
        /// "aa"=>1
        res = solution.lengthOfLongestSubstring("aa");
        Assert.isTrue(1 == res, "1!=" + res);
        /// "aba"=>2
        res = solution.lengthOfLongestSubstring("aba");
        Assert.isTrue(2 == res, "2!=" + res);
        /// "aab"=>2
        res = solution.lengthOfLongestSubstring("aab");
        Assert.isTrue(2 == res, "2!=" + res);
        /// "abcb"=>3
        res = solution.lengthOfLongestSubstring("abcb");
        Assert.isTrue(3 == res, "3!=" + res);
        /// "abcbddde"=>3
        res = solution.lengthOfLongestSubstring("abcbddde");
        Assert.isTrue(3 == res, "3!=" + res);
    }

    /// 给定一个字符串 s ，请你找出其中不含有重复字符的 最长 子串 的长度。
    ///
    /// 0 <= s.length <= 5 * 104
    ///
    /// s 由英文字母、数字、符号和空格组成
    public int lengthOfLongestSubstring(String s) {
        if (s.length() <= 1) {
            return s.length();
        }
        int maxLength = 0, idxLeft = 0, idxRight = 0;
        Set<Character> chars = new HashSet<>();
        while (idxLeft <= idxRight && idxRight < s.length()) {
            char c = s.charAt(idxRight);
            if (chars.contains(c)) {
                chars.remove(s.charAt(idxLeft));
                idxLeft++;
                while (chars.contains(c)) {
                    chars.remove(s.charAt(idxLeft));
                    idxLeft++;
                }
            }
            maxLength = Math.max(maxLength, idxRight - idxLeft + 1);
            chars.add(c);
            idxRight++;
        }
        return maxLength;
    }
}
