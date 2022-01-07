package tree;

import static tree.TreeUtils.*;

public class Problem_TreeMaxWeight extends TreeTraversal {
    public int maxWeight(Node root) {
        if (root == null) {
            return 0;
        }

        int left = maxWeight(root.left);
        int right = maxWeight(root.right);
        return root.value + Math.max(left, right);
    }

    public static void main(String[] args) {
        int height = 4;
        Problem_TreeMaxWeight p = new Problem_TreeMaxWeight();
        Node testData = testData(0, 100, height);
        printTree(testData, height);
        System.out.println(p.maxWeight(testData));
    }
}
