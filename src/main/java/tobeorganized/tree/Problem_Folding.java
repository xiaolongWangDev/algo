package tobeorganized.tree;

import static tobeorganized.tree.TreeUtils.*;

public class Problem_Folding extends TreeTraversal {

    public Node grow(int n, int value) {
        if (n == 0) return null;
        Node node = new Node();
        node.value = value;
        node.left = grow(n - 1, 0);
        node.right = grow(n - 1, 1);
        return node;
    }

    public static void main(String[] args) {
        int height = 4;
        Problem_Folding algo = new Problem_Folding();
        Node root = algo.grow(height, 0);
        printTree(root, height);
        algo.shortInOrder(root, (node) -> System.out.print(node.value));
    }
}
