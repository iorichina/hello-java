package iorichina.hellojava.hellosample.minimum_window_substring;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SolutionTest {
    private final Solution solution = new Solution();

    @Test
    public void testMinWindowWhenSIsShorterThanT() {
        assertEquals("", solution.minWindow("aa", "aaa"));
    }

    @Test
    public void testMinWindowWhenNoValidWindowExists() {
        assertEquals("", solution.minWindow("a", "b"));
    }

    @Test
    public void testMinWindowWithMultipleValidWindows() {
        assertEquals("BANC", solution.minWindow("ADOBECODEBANCDDD", "ABC"));
    }

    @Test
    public void testMinWindowWithExactMatch() {
        assertEquals("A", solution.minWindow("A", "A"));
    }

    @Test
    public void testMinWindowWithDuplicateCharacters() {
        assertEquals("AA", solution.minWindow("AA", "AA"));
    }

    @Test
    public void testMinWindowWithSpecificCase() {
        assertEquals("ba", solution.minWindow("bba", "ab"));
    }

    @Test
    public void testMinWindowWithBbaaAndAba() {
        assertEquals("baa", solution.minWindow("bbaa", "aba"));
    }
}