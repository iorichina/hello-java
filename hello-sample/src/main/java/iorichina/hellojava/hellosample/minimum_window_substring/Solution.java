package iorichina.hellojava.hellosample.minimum_window_substring;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }
        int len = t.length();
        Map<Character, Integer> charMap = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            charMap.putIfAbsent(t.charAt(i), 0);
            charMap.computeIfPresent(t.charAt(i), (key, value) -> value + 1);
        }
        //循环s字符数组，找到t的字符，则computeIfPresent(v=v-1)，直到charMap为空，计算出窗口大小
        int left = -1, right = -1;
        int minLen = Integer.MAX_VALUE;
        String minLenS = "";
        for (right = 0; right < s.length(); right++) {
            if (!charMap.containsKey(s.charAt(right))) {
                continue;
            }
            charMap.computeIfPresent(s.charAt(right), (key, value) -> value - 1);
            //第一个字符
            if (left == -1) {
                left = right;
                len--;
                //只有一个字符的场景
                if (len <= 0) {
                    return t;
                }
                continue;
            }
            // 如果当前字符在t中，并且计数从正数变为0，则表示该字符首次达到所需数量
            if (charMap.get(s.charAt(right)) >= 0) {
                len--;
            }
            if (len > 0) {
                continue;
            }
            // 当找到有效窗口时，尝试收缩左边界以找到最小窗口
            while (len == 0) {
                // 更新最小窗口大小
                if (minLen > right - left + 1) {
                    minLen = right - left + 1;
                    minLenS = s.substring(left, right + 1);
                }

                // 将左指针向右移动并更新字符计数
                char leftChar = s.charAt(left);
                charMap.computeIfPresent(leftChar, (key, value) -> value + 1);

                left++;
                while (!charMap.containsKey(s.charAt(left))) {
                    left++;
                }

                // 如果移出窗口的字符是t中的且计数变为正数，说明需要更多该字符才能满足条件
                if (charMap.get(leftChar) > 0) {
                    len++;
                }
            }
        }
        return minLenS;
    }
}