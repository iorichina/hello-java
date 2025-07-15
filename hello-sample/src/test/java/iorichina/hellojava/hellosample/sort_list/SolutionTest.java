package iorichina.hellojava.hellosample.sort_list;

import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {
    @Test
    public void testSortList_NullInput() {
        Solution solution = new Solution();
        ListNode head = null;
        ListNode result = solution.sortList(head);
        assertNull(result);
    }

    @Test
    public void testSortList_SingleNodeList() {
        Solution solution = new Solution();
        ListNode head = new ListNode(5);
        ListNode result = solution.sortList(head);

        // Verify the result is the same node (same value)
        assertEquals(5, result.val);
        // Verify there's no next node
        assertNull(result.next);
    }

    @Test
    public void testSortList_TwoNodeList() {
        Solution solution = new Solution();
        ListNode head = new ListNode(5);
        head.next = new ListNode(4);
        ListNode result = solution.sortList(head);

        assertNotNull(result);
        assertNotNull(result.next);
        // Verify the result is the same node (same value)
        assertEquals(5, result.next.val);
        assertEquals(4, result.val);
        // Verify there's no next node
        assertNull(result.next.next);
    }

    @Test
    public void testSortList_FourNodeList() {
        Solution solution = new Solution();
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);
        ListNode result = solution.sortList(head);

        assertNotNull(result);
        assertNotNull(result.next);
        assertNotNull(result.next.next);
        assertNotNull(result.next.next.next);
        // Verify the result is the same node (same value)
        assertEquals(1, result.val);
        assertEquals(2, result.next.val);
        assertEquals(3, result.next.next.val);
        assertEquals(4, result.next.next.next.val);
        // Verify there's no next node
        assertNull(result.next.next.next.next);
    }

    @Test
    public void testAlreadySortedList() {
        Solution solution = new Solution();

        // Create input list: 1 -> 2 -> 3
        ListNode input = new ListNode(1);
        input.next = new ListNode(2);
        input.next.next = new ListNode(3);

        // Expected output list: 1 -> 2 -> 3
        ListNode expected = new ListNode(1);
        expected.next = new ListNode(2);
        expected.next.next = new ListNode(3);

        // Get actual result
        ListNode actual = solution.sortList(input);

        // Verify the result
        assertEquals(expected.val, actual.val);
        assertEquals(expected.next.val, actual.next.val);
        assertEquals(expected.next.next.val, actual.next.next.val);
        assertNull(actual.next.next.next);
    }
}