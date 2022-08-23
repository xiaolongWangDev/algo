package practice.zuosbook.chapter3;

import helper.TreeNode;
import helper.TreeUtils;

import java.util.Arrays;
import java.util.LinkedList;

public class TreeSerializationPreOrder {
    public static String serialize(TreeNode root) {
        if (root == null) {
            return "x";
        } else {
            return root.value + "|" +
                    serialize(root.left) + "|" +
                    serialize(root.right);
        }
    }

    public static TreeNode deserialize(String s) {
        if (s == null) {
            return null;
        }
        LinkedList<String> parts = new LinkedList<>(Arrays.asList(s.split("\\|")));
        return deserializeRec(parts);
    }

    public static TreeNode deserializeRec(LinkedList<String> remainingParts) {
        String part = remainingParts.pollFirst();
        if ("x".equals(part)) {
            return null;
        }

        TreeNode root = new TreeNode();
        root.value = Integer.parseInt(part);
        root.left = deserializeRec(remainingParts);
        root.right = deserializeRec(remainingParts);
        return root;
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
