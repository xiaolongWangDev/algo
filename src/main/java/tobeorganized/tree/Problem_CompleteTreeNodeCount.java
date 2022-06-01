package tobeorganized.tree;

import static tobeorganized.tree.TreeUtils.*;

public class Problem_CompleteTreeNodeCount {
    public int count(TreeUtils.Node node) {
        int leftTreeLeftHeight = countLeftmostHeight(node.left);
        int rightTreeLeftHeight = countLeftmostHeight(node.right);

        int sum = 1;
        if (leftTreeLeftHeight == rightTreeLeftHeight) {
            // left is full
            sum += (1 << leftTreeLeftHeight) - 1;
            if (node.right != null) sum += count(node.right);
        } else {
            // right is full but with low height
            sum += (1 << rightTreeLeftHeight) - 1;
            if (node.left != null) sum += count(node.left);
        }

        return sum;
    }

    private int countLeftmostHeight(TreeUtils.Node node) {
        int i = 0;
        while (node != null) {
            node = node.left;
            i++;
        }
        return i;
    }

    /*
                        1
                    2               3
            4           5       6       7
        8       9   10    11   12 13   14 15
    16   17   18  19
     */
    public static void main(String[] args) {
        ConstructFromTraversalOrders construct = new ConstructFromTraversalOrders();
        Integer[] preOrder = new Integer[]{1, 2, 4, 8, 16, 17, 9, 18, 19, 5, 10, 20, 11, 3, 6, 12, 13, 7, 14, 15};
        Integer[] inOrder = new Integer[]{16, 8, 17, 4, 18, 9, 19, 2, 20, 10, 5, 11, 1, 12, 6, 13, 3, 14, 7, 15};
        TreeUtils.Node root = construct.constructTree(preOrder, inOrder, 0, preOrder.length - 1, 0, inOrder.length - 1);
        printTree(root, maxHeight(root));

        Problem_CompleteTreeNodeCount p = new Problem_CompleteTreeNodeCount();
        System.out.println(p.count(root));
    }
}
