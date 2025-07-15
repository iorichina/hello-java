package iorichina.hellojava.hellosample.combinations;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SolutionTest {
    @Test
    public void testCombine_n4k2() {
        Solution solution = new Solution();
        List<List<Integer>> result = solution.combine(4, 2);
        printResult(result);

        // 验证组合数量 C(4,2)=6
        assertEquals(6, result.size());

        // 验证包含特定组合
        assertTrue(containsCombination(result, List.of(1, 2)));
        assertTrue(containsCombination(result, List.of(1, 3)));
        assertTrue(containsCombination(result, List.of(1, 4)));
        assertTrue(containsCombination(result, List.of(2, 3)));
        assertTrue(containsCombination(result, List.of(2, 4)));
        assertTrue(containsCombination(result, List.of(3, 4)));
    }

    @Test
    public void testCombine_n4k3() {
        Solution solution = new Solution();
        List<List<Integer>> result = solution.combine(4, 3);
        printResult(result);

        // 验证组合数量 C(4,3)=4
        assertEquals(4, result.size());

        // 验证包含所有预期组合
        assertTrue(containsCombination(result, List.of(1, 2, 3)));
        assertTrue(containsCombination(result, List.of(1, 2, 4)));
        assertTrue(containsCombination(result, List.of(1, 3, 4)));
        assertTrue(containsCombination(result, List.of(2, 3, 4)));
    }

    @Test
    public void testCombine_n1k1() {
        Solution solution = new Solution();
        List<List<Integer>> result = solution.combine(1, 1);
        printResult(result);

        assertEquals(1, result.size());
        assertEquals(List.of(1), result.get(0));
    }

    @Test
    public void testCombine_n20k1() {
        Solution solution = new Solution();
        List<List<Integer>> result = solution.combine(20, 1);
        printResult(result);

        // C(20,1)=20
        assertEquals(20, result.size());

        // 验证包含1到20的单个数字
        for (int i = 1; i <= 20; i++) {
            assertTrue(containsCombination(result, List.of(i)));
        }
    }

    @Test
    public void testCombine_n20k20() {
        Solution solution = new Solution();
        List<List<Integer>> result = solution.combine(20, 20);
        printResult(result);

        // C(20,20)=1
        assertEquals(1, result.size());

        // 验证包含1到20的所有数字
        List<Integer> expected = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            expected.add(i);
        }
        assertEquals(expected, result.get(0));
    }

    @Test
    public void testCombine_n5k3() {
        Solution solution = new Solution();
        List<List<Integer>> result = solution.combine(5, 3);
        printResult(result);

        // C(5,3)=10
        assertEquals(10, result.size());

        // 抽样验证几个组合
        assertTrue(containsCombination(result, List.of(1, 2, 3)));
        assertTrue(containsCombination(result, List.of(1, 3, 5)));
        assertTrue(containsCombination(result, List.of(2, 4, 5)));
    }

    @Test
    public void testCombine_n3k2() {
        Solution solution = new Solution();
        List<List<Integer>> result = solution.combine(3, 2);
        printResult(result);

        // C(3,2)=3
        assertEquals(3, result.size());

        assertTrue(containsCombination(result, List.of(1, 2)));
        assertTrue(containsCombination(result, List.of(1, 3)));
        assertTrue(containsCombination(result, List.of(2, 3)));
    }

    @Test
    public void testCombine_n5k2() {
        Solution solution = new Solution();
        List<List<Integer>> result = solution.combine(5, 2);
        printResult(result);

        // C(5,2)=10
        assertEquals(10, result.size());

        // 抽样验证几个组合
        assertTrue(containsCombination(result, List.of(1, 2)));
        assertTrue(containsCombination(result, List.of(3, 5)));
        assertTrue(containsCombination(result, List.of(2, 4)));
    }

    // 辅助方法：检查结果列表中是否包含特定组合
    private boolean containsCombination(List<List<Integer>> result, List<Integer> combination) {
        for (List<Integer> list : result) {
            if (list.equals(combination)) {
                return true;
            }
        }
        return false;
    }

    // 辅助方法：格式化打印结果
    private void printResult(List<List<Integer>> result) {
        System.out.println("[");
        for (List<Integer> combination : result) {
            System.out.println("  " + combination + ",");
        }
        System.out.println("]");
    }
}
