package iorichina.hellojava.hellosample.climbing_stairs;

import static org.junit.Assert.assertEquals;

public class SolutionTest {
    @org.junit.Test
    public void test_Solution() {
        Solution solution = new Solution();
        {
            int n = 2;
            long start = System.nanoTime();
            int result = solution.climbStairs(n);
            long end = System.nanoTime();
            System.out.println("Number of ways to climb " + n + " stairs: " + result);
            System.out.println("Time taken for n=" + n + ": " + (end - start) / 1_000_000.0 + " ms");
            assertEquals("There should be 2 ways to climb 2 stairs (1+1 or 2)", 2, result);
        }
        {
            int n = 3;
            long start = System.nanoTime();
            int result = solution.climbStairs(n);
            long end = System.nanoTime();
            System.out.println("Number of ways to climb " + n + " stairs: " + result);
            System.out.println("Time taken for n=" + n + ": " + (end - start) / 1_000_000.0 + " ms");
            assertEquals(3, result);
        }
        {
            int n = 5; // 5阶台阶
            long start = System.nanoTime();
            int result = solution.climbStairs(n);
            long end = System.nanoTime();
            System.out.println("Number of ways to climb " + n + " stairs: " + result);
            System.out.println("Time taken for n=" + n + ": " + (end - start) / 1_000_000.0 + " ms");
            assertEquals("There should be 8 ways to climb 5 stairs", 8, result);
        }
        //会超时
        {
            int n = 44;
            long start = System.nanoTime();
            int result = solution.climbStairs(n);
            long end = System.nanoTime();
            System.out.println("Number of ways to climb " + n + " stairs: " + result);
            System.out.println("Time taken for n=" + n + ": " + (end - start) / 1_000_000.0 + " ms");
            // 44阶台阶的结果是1134903170
            assertEquals("There should be 113490321 ways to climb 44 stairs", 1134903170, result);
        }
        {
            int n = 45;
            long start = System.nanoTime();
            int result = solution.climbStairs(n);
            long end = System.nanoTime();
            System.out.println("Number of ways to climb " + n + " stairs: " + result);
            System.out.println("Time taken for n=" + n + ": " + (end - start) / 1_000_000.0 + " ms");
            // 45阶台阶的结果是1836311903
            assertEquals("There should be 113490321 ways to climb 44 stairs", 1836311903, result);
        }
    }

    @org.junit.Test
    public void test_Solution0() {
        Solution0 solution = new Solution0();
        {
            int n = 2;
            long start = System.nanoTime();
            int result = solution.climbStairs(n);
            long end = System.nanoTime();
            System.out.println("Number of ways to climb " + n + " stairs: " + result);
            System.out.println("Time taken for n=" + n + ": " + (end - start) / 1_000_000.0 + " ms");
            assertEquals("There should be 2 ways to climb 2 stairs (1+1 or 2)", 2, result);
        }
        {
            int n = 3;
            long start = System.nanoTime();
            int result = solution.climbStairs(n);
            long end = System.nanoTime();
            System.out.println("Number of ways to climb " + n + " stairs: " + result);
            System.out.println("Time taken for n=" + n + ": " + (end - start) / 1_000_000.0 + " ms");
            assertEquals(3, result);
        }
        {
            int n = 5; // 5阶台阶
            long start = System.nanoTime();
            int result = solution.climbStairs(n);
            long end = System.nanoTime();
            System.out.println("Number of ways to climb " + n + " stairs: " + result);
            System.out.println("Time taken for n=" + n + ": " + (end - start) / 1_000_000.0 + " ms");
            assertEquals("There should be 8 ways to climb 5 stairs", 8, result);
        }
        //会超时
        {
            int n = 44;
            long start = System.nanoTime();
            int result = solution.climbStairs(n);
            long end = System.nanoTime();
            System.out.println("Number of ways to climb " + n + " stairs: " + result);
            System.out.println("Time taken for n=" + n + ": " + (end - start) / 1_000_000.0 + " ms");
            // 44阶台阶的结果是1134903170
            assertEquals("There should be 113490321 ways to climb 44 stairs", 1134903170, result);
        }
        {
            int n = 45;
            long start = System.nanoTime();
            int result = solution.climbStairs(n);
            long end = System.nanoTime();
            System.out.println("Number of ways to climb " + n + " stairs: " + result);
            System.out.println("Time taken for n=" + n + ": " + (end - start) / 1_000_000.0 + " ms");
            // 45阶台阶的结果是1836311903
            assertEquals("There should be 113490321 ways to climb 44 stairs", 1836311903, result);
        }
    }
}
