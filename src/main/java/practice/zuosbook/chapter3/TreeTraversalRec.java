package practice.zuosbook.chapter3;

import helper.TreeNode;
import helper.TreeUtils;

public class TreeTraversalRec {
    public static void inOrder(TreeNode node) {
        if(node == null) return;
        inOrder(node.left);
        System.out.println(node.value);
        inOrder(node.right);
    }

    public static void preOrder(TreeNode node) {
        if(node == null) return;
        System.out.println(node.value);
        preOrder(node.left);
        preOrder(node.right);
    }

    public static void postOrder(TreeNode node) {
        if(node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.value);
    }

    public static void main(String[] args) {
        int height = 3;
        TreeNode root = TreeUtils.testData(0, 100, height, 0);
        TreeUtils.printTree(root, height);
        System.out.println("----------in order------------");
        inOrder(root);
        System.out.println("----------pre order-----------");
        preOrder(root);
        System.out.println("----------post order----------");
        postOrder(root);
    }
}
