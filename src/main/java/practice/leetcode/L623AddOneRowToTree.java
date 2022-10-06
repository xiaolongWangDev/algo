package practice.leetcode;

import helper.TreeNode;
import helper.TreeUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class L623AddOneRowToTree {

    public static TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth == 1) {
            TreeNode node = new TreeNode();
            node.value = val;
            node.left = root;
            return node;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        depth--;
        List<TreeNode> temp = new ArrayList<>();
        while (depth > 1) {
            if (q.isEmpty() && temp.size() != 0) {
                q.addAll(temp);
                temp.clear();
                depth--;
            } else {
                TreeNode node = q.poll();
                if(node.left != null) temp.add(node.left);
                if(node.right != null) temp.add(node.right);
            }
        }

        while(!q.isEmpty()) {
            TreeNode node = q.poll();
            TreeNode newLeft = new TreeNode();
            newLeft.value = val;
            newLeft.left = node.left;
            TreeNode newRight = new TreeNode();
            newRight.value = val;
            newRight.right = node.right;
            node.left = newLeft;
            node.right = newRight;
        }

        return root;
    }

    public static void main(String[] args) {
        TreeNode data = TreeUtils.testData(0, 100, 4, 0);
        TreeUtils.printTree(data, 4);
        var newRoot = addOneRow(data, 1, 1);

        TreeUtils.printTree(newRoot, 5);
    }
}
