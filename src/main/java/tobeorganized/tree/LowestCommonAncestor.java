package tobeorganized.tree;

import static helper.TreeUtils.*;

public class LowestCommonAncestor extends TreeTraversal {

    public TreeNode find(TreeNode node, TreeNode a, TreeNode b) {
        if (node == null) {
            return null;
        }

        if (node == a || node == b) {
            return node;
        }

        TreeNode leftResult = find(node.left, a, b);
        TreeNode rightResult = find(node.right, a, b);

        // a and b are on the 2 sides, so Im the common ancestor
        if (leftResult != null && rightResult != null) {
            return node;
        }
        // neither a nor b is in my tree
        if (leftResult == null && rightResult == null) {
            return null;
        }
        // one of them as the result. it's either the lca, or a target (a or b)
        // in either case, just returning it is fine
        return leftResult != null ? leftResult : rightResult;
    }


    public static void main(String[] args) {
        int min = 0;
        int max = 100;
        int height = 4;
        // build a random tree of height
        TreeNode testDataRoot = testData(min, max, height);
        printTree(testDataRoot, height);
        LowestCommonAncestor algo = new LowestCommonAncestor();

        // select 2 random nodes
        TreeNode nodeA = algo.getRandomNode(testDataRoot);
//        System.out.println(nodeA.value);
        TreeNode nodeB = algo.getRandomNode(testDataRoot);
//        System.out.println(nodeB.value);

        TreeNode lca = algo.find(testDataRoot, nodeA, nodeB);
        System.out.printf("common ancestor for %s and %s is ", nodeA.value, nodeB.value);
        System.out.println(lca.value);

    }
}
