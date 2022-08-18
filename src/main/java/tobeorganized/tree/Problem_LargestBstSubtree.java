package tobeorganized.tree;


import helper.TreeNode;

import static helper.TreeUtils.printTree;
import static helper.TreeUtils.testData;

public class Problem_LargestBstSubtree {
    private static class Info {
        TreeNode bstRoot;
        int bstNodeCounts;
        Integer min;
        Integer max;

        public Info(TreeNode bstRoot, int bstNodeCounts, int min, int max) {
            this.bstRoot = bstRoot;
            this.bstNodeCounts = bstNodeCounts;
            this.min = min;
            this.max = max;
        }

        public Info(TreeNode bstRoot, int bstNodeCounts) {
            this.bstRoot = bstRoot;
            this.bstNodeCounts = bstNodeCounts;
        }
    }

    public Info solveConcise(TreeNode node) {
        if (node == null) {
            return new Info(null, 0);
        }
        Info leftInfo = solveConcise(node.left);
        Info rightInfo = solveConcise(node.right);

        if (leftInfo.bstRoot == node.left
                && rightInfo.bstRoot == node.right
                && (leftInfo.max == null || leftInfo.max < node.value)
                && (rightInfo.min == null || node.value < rightInfo.min)) {
            return new Info(
                    node,
                    leftInfo.bstNodeCounts + rightInfo.bstNodeCounts + 1,
                    leftInfo.min == null ? node.value : leftInfo.min,
                    rightInfo.max == null ? node.value : rightInfo.max);
        } else {
            return new Info(leftInfo.bstNodeCounts > rightInfo.bstNodeCounts ? leftInfo.bstRoot : rightInfo.bstRoot,
                    Math.max(leftInfo.bstNodeCounts, rightInfo.bstNodeCounts));
        }
    }

    public static void main(String[] args) {
        Problem_LargestBstSubtree p = new Problem_LargestBstSubtree();
        int height = 4;
        while (true) {
            TreeNode testData = testData(0, 100, height);
            Info result = p.solveConcise(testData);
            if (result.bstNodeCounts > 6) {
                printTree(testData, height);
                System.out.println(result.bstRoot.value);
                System.out.println(result.bstNodeCounts);
                break;
            }
        }
    }
}
