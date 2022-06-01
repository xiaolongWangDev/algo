package tobeorganized.tree;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static tobeorganized.tree.TreeUtils.*;

public class AvlTree extends BalanceTree {

    public AvlTree(Set<Integer> values) {
        super();
        for (Integer val : values) {
            super.add(val);
        }
        updateNodeHeight(root);
    }

    public Node add(int value) {
        Node node = super.add(value);
        node.height = 1;
        increaseParentHeight(node);
        return node;
    }

    public int updateNodeHeight(Node node) {
        if (node == null) return 0;
        node.height = Math.max(updateNodeHeight(node.left), updateNodeHeight(node.right)) + 1;
        return node.height;
    }

    public void increaseParentHeight(Node node) {
        while (node.parent != null) {
            node = node.parent;
            node.height++;
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        int min = 0;
        int max = 100;
        Set<Integer> added = new HashSet<>();
        int i = 1;
        int total = 10;
        while (i < total) {
            int val = min + random.nextInt(max - min);
            if (!added.contains(val)) {
                added.add(val);
                i++;
            }
        }
        AvlTree avlTree = new AvlTree(added);
        avlTree.add(50);

        printTree(avlTree.root, maxHeight(avlTree.root));
        avlTree.rotateLeft(avlTree.root);
//        printTree(avlTree.root, maxHeight(avlTree.root));
    }
}
