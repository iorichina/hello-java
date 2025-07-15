package iorichina.hellojava.hellosample.most_ones_in_binary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MostOnesInBinary {

    public static List<Integer> findTopM(int[] nums, int m) {
        if (nums == null || nums.length == 0 || m <= 0) {
            return new ArrayList<>();
        }

        m = Math.min(m, nums.length);
        // 封装数字及其1的个数
        Integer[] numArray = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numArray[i] = nums[i];
        }

        // 排序：1的个数降序，数值升序（数值大的优先）
        Arrays.sort(numArray, (a, b) -> {
            int countA = Integer.bitCount(a);
            int countB = Integer.bitCount(b);
            if (countA != countB) {
                return countB - countA; // 1的个数降序
            } else {
                return b - a; // 数值大的优先
            }
        });

        // 取前m个
        return Arrays.asList(Arrays.copyOf(numArray, m));
    }
}
