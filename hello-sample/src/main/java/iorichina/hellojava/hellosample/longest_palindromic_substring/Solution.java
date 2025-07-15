package iorichina.hellojava.hellosample.longest_palindromic_substring;

import iorichina.hellojava.hellosample.org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Map<String, String> testCases = new HashMap<String, String>() {{
            put("aaaa", "aaaa");
            put("babad", "bab");
            put("cbbd", "bb");
            put("a", "a");
            put("ac", "a");
            put("racecar", "racecar");
            put("aabbccddeeffgghhiiijjjkkklllmmmnnnooopppqqqrrrssstttuuuvvvwwwwxxxxyyyzzz", "wwww");
        }};

        testCases.forEach((k, v) -> {
            String result = solution.longestPalindrome(k);
            System.out.println("Longest Palindrome in \"" + k + "\": " + result);
            Assert.isTrue(Objects.equals(result, v), "Expected: " + v + ", but got: " + result);
        });
    }

    public String longestPalindrome(String s) {
        if (s.length() == 1) {
            return s;
        }
        if (s.length() == 2) {
            return s.charAt(0) == s.charAt(1) ? s : s.substring(0, 1);
        }
        int left = 0, right = 0, maxLength = 0;
        boolean[][] log = new boolean[s.length()][s.length()];//用于保存x~y之间都是回文的坐标x,y
        //从第3个字符开始，判断之前的字符是否是回文
        for (int i = 1; i < s.length(); i++) {
            //从第1个字符开始，判断当前字符与最后一个字符是否相等
            for (int j = 0; j < i; j++) {
                //如果相等，就判断内部是否是回文
                if (s.charAt(i) == s.charAt(j)) {
                    //如果内部都是回文，则保存回文范围
                    if (i - j <= 2 || log[j + 1][i - 1]) {
                        log[j][i] = true;
                        if (i - j + 1 > maxLength) {
                            maxLength = i - j + 1;
                            left = j;
                            right = i;
                        }
                    }
                }
            }
        }
        return s.substring(left, right + 1); // 返回最长回文子串
    }
}