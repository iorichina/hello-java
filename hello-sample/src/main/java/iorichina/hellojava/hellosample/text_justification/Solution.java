package iorichina.hellojava.hellosample.text_justification;

import iorichina.hellojava.hellosample.org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        /// ["a"]+2=>["a "]
        {
            String[] words = {"a"};
            int maxWidth = 2;
            List<String> result = solution.fullJustify(words, maxWidth);
            for (String line : result) {
                System.out.println(line);
            }
            List<String> expected = new ArrayList<>(Arrays.asList("a "));
            Assert.isTrue(result.equals(expected), "Test failed: " + result.stream().collect(Collectors.joining("\"\n\"", "\n\"", "\"\n")));
        }
        ///["What","must","be","acknowledgment","shall","be"]+16=>[
        ///"What   must   be",
        ///   "acknowledgment  ",
        ///   "shall be        "
        /// ]
        {
            String[] words = {"What", "must", "be", "acknowledgment", "shall", "be"};
            int maxWidth = 16;
            List<String> result = solution.fullJustify(words, maxWidth);
            for (String line : result) {
                System.out.println(line);
            }
            List<String> expected = new ArrayList<>(Arrays.asList(
                    "What   must   be",
                    "acknowledgment  ",
                    "shall be        "
            ));
            Assert.isTrue(result.equals(expected), "Test failed: " + result.stream().collect(Collectors.joining("\"\n\"", "\n\"", "\"\n")));
        }
        ///["Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"]+20=>[
        ///"Science  is  what we",
        ///   "understand      well",
        ///   "enough to explain to",
        ///   "a  computer.  Art is",
        ///   "everything  else  we",
        ///   "do                  "
        /// ]
        {
            String[] words = {"Science", "is", "what", "we", "understand", "well", "enough", "to", "explain", "to", "a", "computer.", "Art", "is", "everything", "else", "we", "do"};
            int maxWidth = 20;
            List<String> result = solution.fullJustify(words, maxWidth);
            for (String line : result) {
                System.out.println(line);
            }
            List<String> expected = new ArrayList<>(Arrays.asList(
                    "Science  is  what we",
                    "understand      well",
                    "enough to explain to",
                    "a  computer.  Art is",
                    "everything  else  we",
                    "do                  "
            ));
            Assert.isTrue(result.equals(expected), "Test failed: " + result.stream().collect(Collectors.joining("\"\n\"", "\n\"", "\"\n")));
        }
        /// ["This", "is", "an", "example", "of", "text", "justification."]+16=>[
        ///"This    is    an",
        ///    "example  of text",
        ///    "justification.  "
        /// ]
        {
            String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
            int maxWidth = 16;
            List<String> result = solution.fullJustify(words, maxWidth);
            for (String line : result) {
                System.out.println(line);
            }
            List<String> expected = new ArrayList<>(Arrays.asList(
                    "This    is    an",
                    "example  of text",
                    "justification.  "
            ));
            Assert.isTrue(result.equals(expected), "Test failed: " + result.stream().collect(Collectors.joining("\"\n\"", "\n\"", "\"\n")));
        }
    }

    /// 给定一个单词数组 words 和一个长度 maxWidth ，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。
    ///
    /// 你应该使用 “贪心算法” 来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。
    ///
    /// 要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
    ///
    /// 文本的最后一行应为左对齐，且单词之间不插入额外的空格。
    ///
    /// 注意:
    ///
    /// 单词是指由非空格字符组成的字符序列。
    /// 每个单词的长度大于 0，小于等于 maxWidth。
    /// 输入单词数组 words 至少包含一个单词。
    /// ```
    /// 提示:
    ///
    /// 1 <= words.length <= 300
    /// 1 <= words[i].length <= 20
    /// words[i] 由小写英文字母和符号组成
    /// 1 <= maxWidth <= 100
    /// words[i].length <= maxWidth
    ///```
    public List<String> fullJustify(String[] words, int maxWidth) {
        if (words.length == 1) {
            return new ArrayList<>(Arrays.asList(words[0] + " ".repeat(maxWidth - words[0].length())));
        }
        if (1 == maxWidth) {
            return new ArrayList<>(Arrays.asList(words));
        }
        String space = " ".repeat(maxWidth);
        List<String> res = new ArrayList<>();
        //每行先尽可能多的加入单词
        //如果行长度超过maxWidth，则回退到上一个单词
        String[] line = new String[maxWidth];
        //记录行的单词数
        int ln = 0;
        //记录每行非空格长度、含空格长度
        int len = 0, slen = 0;
        //记录单词数组的index
        //再循环判断：
        // slen+空格+单词长度<=maxWidth 则更新len+=单词长度,slen=空格+单词长度，单词加入line
        // slen+空格+单词长度>maxWidth 计算max-len的空格数：
        //  若ln==1 则直接添加line[0]，并填充空格数
        //  /(ln-1)得到单词间填充空格数，若%(ln-1)不为0，则第一个单词填充多一个空格
        //换行，初始化
        //若+最后一个单词，长度小于maxWidth，则填充空格
        for (int i = 0; i < words.length; i++) {
            //每行首先添加一个单词,len+=单词长度,slen+=单词长度
            line[ln++] = words[i];
            len = slen = words[i].length();
            for (int j = i + 1; j < words.length; j++) {
                int m = slen + 1 + words[j].length();
                if (m <= maxWidth) {
                    len += words[j].length();
                    slen += 1 + words[j].length();
                    line[ln++] = words[j];
                    i = j;
                    continue;
                }
                i = j - 1;
                break;
            }
            StringBuilder sb = new StringBuilder();
            //最后一行
            if (i == words.length - 1) {
                //最后一行，直接添加line中的单词
                for (int n = 0; n < ln; n++) {
                    sb.append(line[n]);
                    if (n < ln - 1) {
                        sb.append(" ");
                    }
                }
                //填充空格
                if (slen < maxWidth) {
                    sb.append(space, 0, maxWidth - slen);
                }
                res.add(sb.toString());
                break;
            }

            //空格数量
            int spl = maxWidth - len;
            if (1 == ln) {
                sb.append(line[0]);
                if (spl > 0) {
                    sb.append(space, 0, spl);
                }
            } else {
                int spln = spl / (ln - 1);//spln>=1
                int first = spl - (spln * (ln - 1));
                for (int n = 0; n < ln; n++) {
                    sb.append(line[n]);
                    if (n == ln - 1) {
                        break;
                    }
                    if (n < first) {
                        sb.append(space, 0, spln + 1);
                        continue;
                    }
                    sb.append(space, 0, spln);
                }
            }

            res.add(sb.toString());
            ln = 0;
        }
        return res;
    }
}