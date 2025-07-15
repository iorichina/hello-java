package iorichina.hellojava.hellosample.sort_list;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

class Solution {
    public ListNode sortList(ListNode head) {
        return sortList(head, null);
    }

    private ListNode sortList(ListNode head, ListNode tail) {
        if (null == head) {
            return tail;
        }
        if (head == tail || head.next == null) {
            return head;
        }
        ListNode mid = head, end = head.next;
        if (end.next != null) {
            end = end.next;
        }
        while (end.next != null) {
            mid = mid.next;
            end = end.next;
            if (end.next != null) {
                end = end.next;
            }
        }
        ListNode next = mid.next;
        mid.next = null;
        head = sortList(head, mid);
        mid = sortList(next, tail);
        return merge(head, mid);
    }

    private ListNode merge(ListNode left, ListNode right) {
        ListNode res = new ListNode();
        ListNode target = res;
        while (left != null && right != null) {
            if (left.val <= right.val) {
                res.next = left;
                res = res.next;
                left = left.next;
            } else {
                res.next = right;
                res = res.next;
                right = right.next;
            }
        }
        if (left != null) {
            res.next = left;
        } else if (right != null) {
            res.next = right;
        }
        return target.next;
    }
}