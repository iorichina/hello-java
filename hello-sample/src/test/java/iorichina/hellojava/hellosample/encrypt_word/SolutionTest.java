package iorichina.hellojava.hellosample.encrypt_word;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 单元测试类：SolutionTest
 */
public class SolutionTest {
    private Solution solution;

    @Before
    public void setUp() {
        solution = new Solution();
    }

    @Test
    public void testSolve_standardInput() {
        String input = "hello world";
        String expected = "h*ll* w*rld";
        assertEquals(expected, solution.solve(input));
    }

    @Test
    public void testSolve_standardInput2() {
        String input = "I am a good programmer";
        String expected = "* *m * g**d pr*gr*mm*r";
        assertEquals(expected, solution.solve(input));
    }

    @Test
    public void testSolve_noVowel() {
        String input = "bcdfg";
        String expected = "gcdfb";
        assertEquals(expected, solution.solve(input));
    }

    @Test
    public void testSolve_noVowel2() {
        String input = "SolutionTest jdk testSolve noVowel";
        String expected = "S*l*t**nT*st kdj t*stS*lv* n*V*w*l";
        assertEquals(expected, solution.solve(input));
    }

    @Test
    public void testSolve_allVowels() {
        String input = "aeiou AEIOU";
        String expected = "***** *****";
        assertEquals(expected, solution.solve(input));
    }

    @Test
    public void testSolve_mixedCase() {
        String input = "Apple banana";
        String expected = "*ppl* b*n*n*";
        assertEquals(expected, solution.solve(input));
    }

    @Test
    public void testSolve_emptyInput() {
        String input = "";
        String expected = "";
        assertEquals(expected, solution.solve(input));
    }

    @Test
    public void testSolve_singleCharacter() {
        String input = "a";
        String expected = "*";
        assertEquals(expected, solution.solve(input));
    }

    @Test
    public void testSolve_singleWordWithNoVowel() {
        String input = "xyz";
        String expected = "zyx";
        assertEquals(expected, solution.solve(input));
    }
}