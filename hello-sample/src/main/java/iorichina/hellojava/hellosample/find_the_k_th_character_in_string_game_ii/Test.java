package iorichina.hellojava.hellosample.find_the_k_th_character_in_string_game_ii;

import iorichina.hellojava.hellosample.org.springframework.util.Assert;

public class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        /// 输入
        /// k =
        /// 33354182522397
        /// operations =
        /// [0,0,0,0,0,1,0,0,0,0,0,1,1,1,1,1,1,0,1,0,0,1,1,0,1,0,1,1,0,1,1,1,0,1,0,1,0,1,0,0,0,0,0,1,1,1,1,0,0,1,1,0,0,1,1,1,1,0,0,0,1,0,1,1,0,1,0,0,0,1,0,1,0,1,1,0,0,0,0,1,0,1,1,0,0,1,0,0,1,1,0,1,1,1,1,1,1,0,0,0]
        /// 预期结果
        /// "k"
        {
            char c = solution.kthCharacter(33354182522397L, new int[]{0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1, 0, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0});
            System.out.println(c);
            Assert.isTrue(c == 'k', "预期输出应为 'k'，但实际输出为 '" + String.valueOf(c) + "'");
        }
        ///输入
        /// k =
        /// 12145134613
        /// operations =
        /// [0,0,0,0,1,0,0,0,1,1,1,1,1,0,1,0,0,0,1,0,0,0,0,0,1,1,0,1,0,0,1,1,1,1,1]
        ///预期结果
        /// "i"
        {
            char c = solution.kthCharacter(12145134613L, new int[]{0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1, 1, 1, 1});
            System.out.println(c);
            Assert.isTrue(c == 'i', "预期输出应为 'i'，但实际输出为 '" + String.valueOf(c) + "'");
        }
        /// 输入：k = 10, operations = [0,1,0,1]
        ///
        /// 输出："b"
        {
            char c = solution.kthCharacter(10, new int[]{0, 1, 0, 1});
            System.out.println(c);
            Assert.isTrue(c == 'b', "预期输出应为 'b'，但实际输出为 '" + c + "'");
        }
        /// 输入：k = 5, operations = [0,0,0]
        ///
        /// 输出："a"
        {
            char c = solution.kthCharacter(5, new int[]{0, 0, 0});
            System.out.println(c);
            Assert.isTrue(c == 'a', "预期输出应为 'a'，但实际输出为 '" + String.valueOf(c) + "'");
        }
    }
}

class Solution0 {
    public char kthCharacter(long k, int[] operations) {
        int len = (int) Math.pow(2, operations.length);
        char[] chars = new char[len];
        chars[0] = 'a';
        for (int i = 0; i < operations.length; i++) {
            int pow = (int) Math.pow(2, i);
            if (pow >= k) {
                return chars[(int) k - 1];
            }
            System.arraycopy(chars, 0, chars, pow, pow);
            if (operations[i] == 0) {
                continue;
            }
            for (int j = 0; j < pow; j++) {
                chars[j + pow] = chars[j] == 'z' ? 'a' : (char) ((int) chars[j] + 1);
            }
        }
        return chars[(int) k - 1];
    }
}