package iorichina.hellojava.hellosample.encrypt_word;

import java.util.HashSet;
import java.util.Set;

/// 一、题目描述
//输入一个英文句子，句子中包含若干个单词，每个单词间有一个空格需要将句子中的每个单词按照要求加密输出。
//
//要求：
//
//单词中包括元音字符 (aeiou、AEIOU)，大小写都算)，则将元音字符替换成*”
//单词中不包括元音字符，将单词首尾字符进行对换
//二、输入描述
//输入只有一行，包含一个长度都不超过100的字符串，表示英文句子
//
//三、输出描述
//输出只有一行，即按要求输出加密处理后的英文句子
//
//四、测试用例
//测试用例1：
//1、输入
//Hello world
//
//2、输出
//H*ll* w*rld
//
//测试用例2：
//1、输入
//I am a good programmer
//
//2、输出
//* *m * g**d pr*gr*mm*r
//
//五、解题思路
//将输入句子按空格分割成单

public class Solution {
    public String solve(String input) {
        //分割
        String[] words = input.split(" ");
        //元音字符
        Set<Character> vowels = new HashSet<>() {{
            add('a');
            add('e');
            add('i');
            add('o');
            add('u');
            add('A');
            add('E');
            add('I');
            add('O');
            add('U');
        }};
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            boolean hasVowel = false;
            for (int j = 0; j < words[i].length(); j++) {
                if (vowels.contains(words[i].charAt(j))) {
                    hasVowel = true;
                    result.append("*");
                    continue;
                }
                result.append(words[i].charAt(j));
            }
            if (!hasVowel && words[i].length() > 1) {
                result.setCharAt(result.length() - words[i].length(), words[i].charAt(words[i].length() - 1));
                result.setCharAt(result.length() - 1, words[i].charAt(0));
            }
            result.append(" ");
        }
        if (!result.isEmpty()) {
            result.deleteCharAt(result.length() - 1);
        }
        return result.toString();
    }
}
