package tobeorganized.tree;

import helper.TreeNode;

import static helper.TreeUtils.*;

public class ParentAwareTree extends TreeTraversal {

    public TreeNode findInOrderSuccessor(TreeNode node) {
        if (node.right != null) {
            node = node.right;
            // leftmost node in right tree
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }

        // or the first ancestor to which this node is on the left tree
        TreeNode cur = node;
        TreeNode parent = node.parent;
        while (parent != null && cur == parent.right) {
            cur = parent;
            parent = cur.parent;
        }
        return parent;
    }

    public TreeNode findInOrderPredecessor(TreeNode node) {
        if (node.left != null) {
            node = node.left;
            // rightmost node in left tree
            while (node.right != null) {
                node = node.right;
            }
            return node;
        }

        // or the first ancestor to which this node is on the right tree
        TreeNode cur = node;
        TreeNode parent = node.parent;
        while (parent != null && cur == parent.left) {
            cur = parent;
            parent = cur.parent;
        }
        return parent;
    }

    public static void main(String[] args) {
        int min = 0;
        int max = 100;
        int height = 4;
        // build a random tree of height
        TreeNode testDataRoot = testData(min, max, height);
        printTree(testDataRoot, height);
        ParentAwareTree algo = new ParentAwareTree();

        // select 1 random node
        TreeNode nodeA = algo.getRandomNode(testDataRoot);
//        System.out.println(nodeA.value);

        TreeNode predecessor = algo.findInOrderPredecessor(nodeA);
        System.out.printf("predecessor for %s is ", nodeA.value);
        System.out.println(predecessor == null ? null : predecessor.value);

        TreeNode successor = algo.findInOrderSuccessor(nodeA);
        System.out.printf("successor for %s is ", nodeA.value);
        System.out.println(successor == null ? null : successor.value);


    }
}
