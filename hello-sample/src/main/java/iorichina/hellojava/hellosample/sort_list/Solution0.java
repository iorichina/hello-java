package iorichina.hellojava.hellosample.sort_list;

import java.util.SortedMap;
import java.util.TreeMap;

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

class Solution0 {
    public ListNode sortList(ListNode head) {
        if (null == head) {
            return null;
        }
        SortedMap<Integer, ListNode> map = new TreeMap<>();
        while (head != null) {
            map.put(head.val, head);
            head = head.next;
        }
        ListNode res = head = map.pollFirstEntry().getValue();
        while (!map.isEmpty()) {
            head.next = map.pollFirstEntry().getValue();
            head = head.next;
        }
        return res;
    }
}