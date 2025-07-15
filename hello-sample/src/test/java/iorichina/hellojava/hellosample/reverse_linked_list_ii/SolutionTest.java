package iorichina.hellojava.hellosample.reverse_linked_list_ii;

import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {

    // 辅助方法：创建链表
    private ListNode createList(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        ListNode head = new ListNode(nums[0]);
        ListNode curr = head;
        for (int i = 1; i < nums.length; i++) {
            curr.next = new ListNode(nums[i]);
            curr = curr.next;
        }
        return head;
    }

    // 辅助方法：将链表转为字符串
    private String listToString(ListNode head) {
        StringBuilder sb = new StringBuilder();
        ListNode curr = head;
        while (curr != null) {
            sb.append(curr.val);
            if (curr.next != null) sb.append(" -> ");
            curr = curr.next;
        }
        return sb.toString();
    }

    // 辅助方法：检查两个链表是否相等
    private boolean listsEqual(ListNode l1, ListNode l2) {
        ListNode p1 = l1, p2 = l2;
        while (p1 != null && p2 != null) {
            if (p1.val != p2.val) return false;
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1 == null && p2 == null;
    }

    // 测试空链表
    @Test
    public void testReverseBetween_EmptyList() {
        Solution solution = new Solution();
        assertNull("Expected null for empty list", solution.reverseBetween(null, 1, 2));
    }

    // 测试单个节点
    @Test
    public void testReverseBetween_SingleNode() {
        Solution solution = new Solution();
        ListNode result = solution.reverseBetween(createList(new int[]{1}), 1, 1);
        assertEquals("Expected list with single node 1", "1", listToString(result));
    }

    // 测试反转范围为整个链表
    @Test
    public void testReverseBetween_ReverseEntireList() {
        Solution solution = new Solution();
        ListNode result = solution.reverseBetween(createList(new int[]{1, 2, 3, 4, 5}), 1, 5);
        assertTrue("Expected reversed list [5 -> 4 -> 3 -> 2 -> 1]", listsEqual(createList(new int[]{5, 4, 3, 2, 1}), result));
    }

    // 测试正常反转范围
    @Test
    public void testReverseBetween_NormalRange() {
        Solution solution = new Solution();
        ListNode result = solution.reverseBetween(createList(new int[]{1, 2, 3, 4, 5}), 2, 4);
        assertTrue("Expected list [1 -> 4 -> 3 -> 2 -> 5]", listsEqual(createList(new int[]{1, 4, 3, 2, 5}), result));
    }

    // 测试left等于right的情况
    @Test
    public void testReverseBetween_LeftEqualsRight() {
        Solution solution = new Solution();
        ListNode result = solution.reverseBetween(createList(new int[]{1, 2, 3, 4, 5}), 3, 3);
        assertTrue("Expected unchanged list [1 -> 2 -> 3 -> 4 -> 5]", listsEqual(createList(new int[]{1, 2, 3, 4, 5}), result));
    }

    // 测试反转范围在链表中间
    @Test
    public void testReverseBetween_MiddleRange() {
        Solution solution = new Solution();
        ListNode result = solution.reverseBetween(createList(new int[]{1, 2, 3, 4, 5, 6, 7}), 3, 5);
        assertTrue("Expected list [1 -> 2 -> 5 -> 4 -> 3 -> 6 -> 7]", listsEqual(createList(new int[]{1, 2, 5, 4, 3, 6, 7}), result));
    }

    // 测试反转范围从第一个节点开始
    @Test
    public void testReverseBetween_StartFromFirst() {
        Solution solution = new Solution();
        ListNode result = solution.reverseBetween(createList(new int[]{1, 2, 3, 4, 5}), 1, 3);
        assertTrue("Expected list [3 -> 2 -> 1 -> 4 -> 5]", listsEqual(createList(new int[]{3, 2, 1, 4, 5}), result));
    }

    // 测试反转范围到链表末尾
    @Test
    public void testReverseBetween_EndAtLast() {
        Solution solution = new Solution();
        ListNode result = solution.reverseBetween(createList(new int[]{1, 2, 3, 4, 5}), 3, 5);
        assertTrue("Expected list [1 -> 2 -> 5 -> 4 -> 3]", listsEqual(createList(new int[]{1, 2, 5, 4, 3}), result));
    }
}