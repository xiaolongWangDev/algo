package tobeorganized.tree;

import static helper.TreeUtils.*;

public class Problem_Folding extends TreeTraversal {

    public TreeNode grow(int n, int value) {
        if (n == 0) return null;
        TreeNode node = new TreeNode();
        node.value = value;
        node.left = grow(n - 1, 0);
        node.right = grow(n - 1, 1);
        return node;
    }

    public static void main(String[] args) {
        int height = 4;
        Problem_Folding algo = new Problem_Folding();
        TreeNode root = algo.grow(height, 0);
        printTree(root, height);
        algo.shortInOrder(root, (node) -> System.out.print(node.value));
    }
}
