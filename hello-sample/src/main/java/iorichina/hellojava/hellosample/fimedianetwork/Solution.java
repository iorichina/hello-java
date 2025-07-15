package iorichina.hellojava.hellosample.fimedianetwork;

public class Solution {
    public void removeValNode(Node link, int val) {
        if (link == null) {
            return;
        }

        Node left = link, right = link.next;

        while (right != null) {
            if (right.val == val) {
                left.next = right.next;
                left = right.next;
                right = left != null ? left.next : null;
                continue;
            }
            left = right;
            right = right.next;
        }
    }

    public static class Node {
        int val;
        Node next;
    }
}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Solution.Node head = new Solution.Node();
        head.val = 1;
        head.next = new Solution.Node();
        head.next.val = 2;
        head.next.next = new Solution.Node();
        head.next.next.val = 3;
        head.next.next.next = new Solution.Node();
        head.next.next.next.val = 2;

        solution.removeValNode(head, 2);

        // Print the modified linked list
        Solution.Node current = head;
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
    }
}