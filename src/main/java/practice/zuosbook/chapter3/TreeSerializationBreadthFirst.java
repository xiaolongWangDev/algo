package practice.zuosbook.chapter3;

import helper.TreeNode;
import helper.TreeUtils;

import java.util.Arrays;
import java.util.LinkedList;

public class TreeSerializationBreadthFirst {
    public static String serialize(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            TreeNode cur = queue.pollFirst();
            if (cur == null) {
                sb.append("x|");
            } else {
                sb.append(cur.value).append("|");
                queue.add(cur.left);
                queue.add(cur.right);
            }
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    public static TreeNode deserialize(String s) {
        if (s == null) {
            return null;
        }
        String[] parts = s.split("\\|");
        TreeNode root;
        int i = 0;
        root = createTreeNode(parts, i++);
        if (root == null) return null;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            cur.left = createTreeNode(parts, i++);
            cur.right = createTreeNode(parts, i++);
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }

        return root;
    }

    private static TreeNode createTreeNode(String[] parts, int i) {
        TreeNode node;
        if ("x".equals(parts[i])) {
            node = null;
        } else {
            node = new TreeNode();
            node.value = Integer.parseInt(parts[i]);
        }
        return node;
    }


    public static void main(String[] args) {
        int height = 4;
        TreeNode root = TreeUtils.testData(0, 100, height, 3);
        TreeUtils.printTree(root, height);

        String serialized = serialize(root);
        System.out.println(serialized);

        TreeNode deserialized = deserialize(serialized);
        TreeUtils.printTree(deserialized, height);
    }
}
