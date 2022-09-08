package practice.leetcode;

import helper.TreeNode;
import helper.TreeUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class L94BinaryTreeInOrderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        rec(root, res);
        return res;
    }

    public void rec(TreeNode node, List<Integer> res) {
        if (node == null) return;
        rec(node.left, res);
        res.add(node.value);
        rec(node.right, res);
    }

    public static List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (true) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else if (stack.isEmpty()) {
                return res;
            } else {
                cur = stack.pop();
                res.add(cur.value);
                cur = cur.right;
            }

        }
    }

    public static void main(String[] args) {
        int height = 4;
        TreeNode root = TreeUtils.testData(0, 100, height, 1);
        TreeUtils.printTree(root, height);
        System.out.println(inorderTraversal1(root));
    }

}
