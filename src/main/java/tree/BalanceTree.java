package tree;

import java.util.ArrayList;
import java.util.List;

import static tree.TreeUtils.*;

public abstract class BalanceTree {
    public Node root;

//    abstract void balance(Node node);

    public Node add(int value) {
        Node node = new Node();
        node.value = value;
        if (root == null) {
            root = node;
        } else {
            Node cur = root;
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

    public Node rotateLeft(Node node) {
        if (node.right != null) {
            Node parent = node.parent;
            Node oldRightNode = node.right;
            Node oldRightLeftNode = oldRightNode.left;
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

    public Node rotateRight(Node node) {
        if (node.left != null) {
            Node parent = node.parent;
            Node oldLeftNode = node.left;
            Node oldLeftRightNode = oldLeftNode.right;
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
