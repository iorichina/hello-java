package iorichina.hellojava.hellosample.rotate_list;

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
    public ListNode rotateRight(ListNode head, int k) {
        if (null == head || null == head.next || 0 == k) {
            return head;
        }
        //链表长度
        int r = 1;
        //尾节点指针
        ListNode t = head;
        while (t.next != null) {
            r++;
            t = t.next;
        }
        //处理边界场景
        if (k % r == 0) {
            return head;
        }
        //找到断开的位置
        r = r - k % r;
        //将尾节点连上头节点
        t.next = head;
        //找到断开的位置
        while (r > 1) {
            head = head.next;
            r--;
        }
        //重新赋值头节点的指针
        t = head.next;
        //设置新尾节点的next=null
        head.next = null;
        return t;
    }
}