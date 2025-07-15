package iorichina.hellojava.hellosample.combinations;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> combine(int n, int k) {
        if (n == 1) {
            return List.of(List.of(1));
        }
        return combines(n, k);
    }

    private List<List<Integer>> combines(int maxNum, int needNums) {
        List<List<Integer>> result = new java.util.ArrayList<>();
        /// 1,2,3;1,2,4;2,3,4;
        int max = maxNum - needNums + 1;
        for (int from = 1; from <= max; from++) {
//            if (maxNum - from + 1 < needNums) {
//                break;
//            }
            ArrayList<Integer> list = new ArrayList<>();
            list.add(from);
            putNext(result, list, maxNum, from, needNums - 1);
        }
        return result;
    }

    private void putNext(List<List<Integer>> result, List<Integer> list, int maxNum, int from, int needNums) {
        if (needNums <= 0) {
            result.add(new ArrayList<>(list));
            return;
        }
        if (from == maxNum) {
            result.add(new ArrayList<>(list));
            return;
        }
        int max = maxNum - needNums + 1;
        for (int i = from + 1; i <= max; i++) {
            list.add(i);
            putNext(result, list, maxNum, i, needNums - 1);
            list.removeLast();
        }
    }
}