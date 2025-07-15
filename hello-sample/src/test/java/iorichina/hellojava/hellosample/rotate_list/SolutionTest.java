package iorichina.hellojava.hellosample.rotate_list;

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

    // 测试空链表旋转
    @Test
    public void testRotateRight_EmptyList() {
        Solution solution = new Solution();
        ListNode result = solution.rotateRight(null, 0);
        System.out.println("空链表旋转结果: " + (result == null ? "null" : result.val));
        assertNull("Expected null for empty list", result);
    }

    // 测试单个节点旋转
    @Test
    public void testRotateRight_SingleNode() {
        Solution solution = new Solution();
        ListNode result = solution.rotateRight(createList(new int[]{1}), 5);
        System.out.println("单个节点旋转结果: " + (result == null ? "null" : result.val));
        assertEquals("Expected list with single node 1", "1", listToString(result));
    }

    // 测试旋转次数等于链表长度
    @Test
    public void testRotateRight_RotateLengthEqualsListLength() {
        Solution solution = new Solution();
        ListNode result = solution.rotateRight(createList(new int[]{1, 2, 3, 4, 5}), 5);
        System.out.println("旋转次数等于链表长度结果: " + listToString(result));
        assertTrue("Expected unchanged list [1 -> 2 -> 3 -> 4 -> 5]", listsEqual(createList(new int[]{1, 2, 3, 4, 5}), result));
    }

    // 测试正常旋转情况
    @Test
    public void testRotateRight_NormalRotation() {
        Solution solution = new Solution();
        ListNode result = solution.rotateRight(createList(new int[]{1, 2, 3, 4, 5}), 2);
        System.out.println("正常旋转结果: " + listToString(result));
        assertTrue("Expected rotated list [4 -> 5 -> 1 -> 2 -> 3]", listsEqual(createList(new int[]{4, 5, 1, 2, 3}), result));
    }

    // 测试旋转次数大于链表长度
    @Test
    public void testRotateRight_RotateCountGreaterThanListLength() {
        Solution solution = new Solution();
        ListNode result = solution.rotateRight(createList(new int[]{1, 2, 3, 4, 5}), 7);
        System.out.println("旋转次数大于链表长度结果: " + listToString(result));
        assertTrue("Expected rotated list [4 -> 5 -> 1 -> 2 -> 3]", listsEqual(createList(new int[]{4, 5, 1, 2, 3}), result));
    }

    // 测试短链表旋转
    @Test
    public void testRotateRight_ShortList() {
        Solution solution = new Solution();
        ListNode result = solution.rotateRight(createList(new int[]{1, 2}), 3);
        System.out.println("短链表旋转结果: " + listToString(result));
        assertTrue("Expected rotated short list [2 -> 1]", listsEqual(createList(new int[]{2, 1}), result));
    }
}