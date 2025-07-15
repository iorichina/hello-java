package iorichina.hellojava.hellosample.reverse_linked_list_ii;

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
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == right || null == head || null == head.next) {
            return head;
        }
        //获取需要反转的元素的上一个元素指针，以及下一个元素指针
        ListNode pre = null, next = null;
        //创建需要反转的元素的所有指针
        ListNode[] arr = new ListNode[right - left + 1];
        ListNode cur = new ListNode();
        cur.next = head;
        int curIndex = 0;
        do {
            curIndex++;
            cur = cur.next;
            //所有指针
            if (curIndex >= left && curIndex <= right) {
                arr[curIndex - left] = cur;
            }
            //上一个元素指针，确保至少是第二个元素
            if (curIndex + 1 == left) {
                pre = cur;
            }
            //下一个元素指针，确保是反转范围之外的元素
            if (curIndex == right) {
                next = cur.next;
                //只需遍历到right即可
                break;
            }
        } while (cur.next != null);
        //对所有反转元素的数组排序
        left = 0;
        right = arr.length - 1;
        while (left < right) {
            ListNode tme = arr[left];
            arr[left] = arr[right];
            arr[right] = tme;
            left++;
            right--;
        }
        //重新链接
        if (pre != null) {
            pre.next = arr[0];
        } else {
            head = arr[0];
        }
        for (int i = 0; i < arr.length - 1; i++) {
            arr[i].next = arr[i + 1];
        }
        arr[arr.length - 1].next = next;
        //返回头节点
        return head;
    }
}