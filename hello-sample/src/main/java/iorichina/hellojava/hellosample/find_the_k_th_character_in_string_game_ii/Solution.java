package iorichina.hellojava.hellosample.find_the_k_th_character_in_string_game_ii;

class Solution {
    /// ```
    /// 1 <= k <= 1014
    /// 1 <= operations.length <= 100
    /// operations[i] 可以是 0 或 1。
    /// 输入保证在执行所有操作后，word 至少有 k 个字符。
    ///```
    public char kthCharacter(long k, int[] operations) {
        return find(k, operations, operations.length, 'a');
    }

    /// 输入：k = 10, operations = [0,1,0,1]
    ///
    /// 输出："b"
    ///
    /// 输入：k = 5, operations = [0,0,0]
    ///
    /// 输出："a"
    ///
    /// 输入
    /// k =
    /// 12145134613
    /// operations =
    /// [0,0,0,0,1,0,0,0,1,1,1,1,1,0,1,0,0,0,1,0,0,0,0,0,1,1,0,1,0,0,1,1,1,1,1]
    /// 预期结果
    /// "i"
    char find(long k, int[] operations, int len, char c) {
        if (len <= 0) {
            return c;
        }
        if (len >= 64) {
            len = 63;
        }
        long haft = 1L << (len - 1);
        if (k <= haft) {
            return find(k, operations, len - 1, c);
        }
        if (operations[len - 1] == 1) {
            c = c == 'z' ? 'a' : (char) ((int) c + 1);
        }
        return find(k - haft, operations, len - 1, c);
    }
}