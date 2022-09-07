package practice.leetcode;

import helper.TreeNode;

public class L606ConstructStringFromBinaryTree {
    public static String tree2str(TreeNode root) {
        if (root == null) return null;
        String s = rec(root);
        return s.substring(1, s.length() - 1);
    }

    public static String rec(TreeNode root) {
        if (root == null) return "()";
        String left = rec(root.left);
        String right = rec(root.right);
        if (left.equals("()") && right.equals("()")) {
            return "(" + root.value + ")";
        } else if (right.equals("()")) {
            return "(" + root.value + left + ")";
        } else {
            return "(" + root.value + left + right + ")";
        }
    }
    public static void main(String[] args) {

    }
}

