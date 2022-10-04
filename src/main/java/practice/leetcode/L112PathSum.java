package practice.leetcode;

import helper.TreeNode;

public class L112PathSum {
    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        if (root.left == null && root.right == null) {
            return root.value == targetSum;
        } else {
            return hasPathSum(root.left, targetSum - root.value) || hasPathSum(root.right, targetSum - root.value);
        }
    }

}
