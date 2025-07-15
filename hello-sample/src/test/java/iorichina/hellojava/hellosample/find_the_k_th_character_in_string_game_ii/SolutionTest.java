package iorichina.hellojava.hellosample.find_the_k_th_character_in_string_game_ii;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class SolutionTest {
    @Test
    public void testKthCharacterBasic() {
        Solution solution = new Solution();
        // Test case 1: Single operation 0, k=1
        assertEquals('a', solution.kthCharacter(1, new int[]{0}));

        // Test case 2: Single operation 1, k=1
        assertEquals('b', solution.kthCharacter(1, new int[]{1}));

        // Test case 3: Multiple operations, k in first half
        assertEquals('a', solution.kthCharacter(2, new int[]{0, 1}));

        // Test case 4: Multiple operations, k in second half
        assertEquals('b', solution.kthCharacter(3, new int[]{0, 1}));

        // Test case 5: Operation 1 at last position, k=1
        assertEquals('b', solution.kthCharacter(1, new int[]{0, 0, 1}));

        // Test case 6: Operation 1 at last position, k=4 (boundary)
        assertEquals('b', solution.kthCharacter(4, new int[]{0, 0, 1}));

        // Test case 7: All operations 1, k=1
        assertEquals('d', solution.kthCharacter(1, new int[]{1, 1, 1}));

        // Test case 8: All operations 1, k=8 (max for 3 operations)
        assertEquals('d', solution.kthCharacter(8, new int[]{1, 1, 1}));

        // Test case 9: Mixed operations, k at boundary
        assertEquals('c', solution.kthCharacter(4, new int[]{1, 0, 1}));

        // Test case 10: Large k (boundary of long)
        assertEquals('b', solution.kthCharacter(1000000000000L, new int[]{0, 0, 0, 1}));
    }
}
