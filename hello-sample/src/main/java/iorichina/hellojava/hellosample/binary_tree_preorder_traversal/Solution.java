package iorichina.hellojava.hellosample.binary_tree_preorder_traversal;

import java.util.ArrayList;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */

class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (null == root) {
            return res;
        }
        res.add(root.val);
        if (null != root.left) {
            res.addAll(preorderTraversal(root.left));
        }
        if (null != root.right) {
            res.addAll(preorderTraversal(root.right));
        }
        return res;
    }
}