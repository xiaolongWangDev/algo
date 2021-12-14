package tree;

import static tree.TreeUtils.*;

public class ParentAwareTree extends TreeTraversal {

    public Node findInOrderSuccessor(Node node) {
        if (node.right != null) {
            node = node.right;
            // leftmost node in right tree
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }

        // or the first ancestor to which this node is on the left tree
        Node cur = node;
        Node parent = node.parent;
        while (parent != null && cur == parent.right) {
            cur = parent;
            parent = cur.parent;
        }
        return parent;
    }

    public Node findInOrderPredecessor(Node node) {
        if (node.left != null) {
            node = node.left;
            // rightmost node in left tree
            while (node.right != null) {
                node = node.right;
            }
            return node;
        }

        // or the first ancestor to which this node is on the right tree
        Node cur = node;
        Node parent = node.parent;
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
        Node testDataRoot = testData(min, max, height);
        printTree(testDataRoot, height);
        ParentAwareTree algo = new ParentAwareTree();

        // select 1 random node
        Node nodeA = algo.getRandomNode(testDataRoot);
//        System.out.println(nodeA.value);

        Node predecessor = algo.findInOrderPredecessor(nodeA);
        System.out.printf("predecessor for %s is ", nodeA.value);
        System.out.println(predecessor == null ? null : predecessor.value);

        Node successor = algo.findInOrderSuccessor(nodeA);
        System.out.printf("successor for %s is ", nodeA.value);
        System.out.println(successor == null ? null : successor.value);


    }
}
