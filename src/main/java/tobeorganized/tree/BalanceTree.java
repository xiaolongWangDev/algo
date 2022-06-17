package tobeorganized.tree;

import static helper.TreeUtils.*;

public abstract class BalanceTree {
    public TreeNode root;

//    abstract void balance(Node node);

    public TreeNode add(int value) {
        TreeNode node = new TreeNode();
        node.value = value;
        if (root == null) {
            root = node;
        } else {
            TreeNode cur = root;
            while (true) {
                if (value < cur.value) {
                    if (cur.left == null) {
                        cur.left = node;
                        node.parent = cur;
                        break;
                    }
                    cur = cur.left;
                } else if (value > cur.value) {
                    if (cur.right == null) {
                        cur.right = node;
                        node.parent = cur;
                        break;
                    }
                    cur = cur.right;
                } else {
                    // value is already added
                    // we don't allow it in our simple tree, ignore it
                    return null;
                }
            }
        }
        return node;
    }

    public TreeNode rotateLeft(TreeNode node) {
        if (node.right != null) {
            TreeNode parent = node.parent;
            TreeNode oldRightNode = node.right;
            TreeNode oldRightLeftNode = oldRightNode.left;
            if (parent != null) {
                if (parent.left == node) {
                    parent.left = oldRightNode;
                } else {
                    parent.right = oldRightNode;
                }
            }
            oldRightNode.parent = parent;
            oldRightNode.left = node;
            node.right = oldRightLeftNode;
            node.parent = oldRightNode;
            if (node == root) {
                root = oldRightNode;
            }
            return oldRightNode;
        }
        return node;
    }

    public TreeNode rotateRight(TreeNode node) {
        if (node.left != null) {
            TreeNode parent = node.parent;
            TreeNode oldLeftNode = node.left;
            TreeNode oldLeftRightNode = oldLeftNode.right;
            if (parent != null) {
                if (parent.left == node) {
                    parent.left = oldLeftNode;
                } else {
                    parent.right = oldLeftNode;
                }
            }
            oldLeftNode.parent = parent;
            oldLeftNode.right = node;
            node.left = oldLeftRightNode;
            node.parent = oldLeftNode;
            if (node == root) {
                root = oldLeftNode;
            }
            return oldLeftNode;
        }
        return node;
    }

}
