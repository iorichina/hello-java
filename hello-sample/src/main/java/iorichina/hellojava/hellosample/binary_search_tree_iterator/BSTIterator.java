package iorichina.hellojava.hellosample.binary_search_tree_iterator;

import java.util.ArrayList;
import java.util.List;

public class BSTIterator {
    List<TreeNode> list = new ArrayList<>();
    int idx = 0;

    public BSTIterator(TreeNode root) {
        append(root);
    }

    void append(TreeNode node) {
        if (node == null) {
            return;
        }
        append(node.left);
        list.add(node);
        append(node.right);
    }

    public int next() {
        return list.get(idx++).val;
    }

    public boolean hasNext() {
        return idx < list.size();
    }
}
