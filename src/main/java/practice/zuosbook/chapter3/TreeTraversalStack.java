package practice.zuosbook.chapter3;

import helper.TreeNode;
import helper.TreeUtils;

import java.util.LinkedList;
import java.util.Stack;

public class TreeTraversalStack {
    public static void inOrder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        var cur = root;
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            System.out.println(cur.value);
            cur = cur.right;
        }
    }

    public static void preOrder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            var cur = stack.pop();
            if (cur == null) continue;
            System.out.println(cur.value);
            stack.push(cur.right);
            stack.push(cur.left);
        }
    }

    public static void postOrder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> outStack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            var cur = stack.pop();
            if (cur == null) continue;
            outStack.push(cur);
            // different from pre order, this time we want to push left first.
            // this way, it will be popped later.
            // when it's popped, it's added to the out stack which later means earlier
            // the insert order into the out stack is middle, then right, then left
            // so when popping from that, we get left, right, middle order

            stack.push(cur.left);
            stack.push(cur.right);
        }

        while (!outStack.isEmpty()) {
            System.out.println(outStack.pop().value);
        }
    }

    public static void postOrderSingleStack(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode justFinished = root;
        stack.push(root);
        while (!stack.isEmpty()) {
            var cur = stack.peek();
            if (cur.left != null && justFinished != cur.left && justFinished != cur.right) {
                stack.push(cur.left);
            } else if (cur.right != null && justFinished != cur.right) {
                stack.push(cur.right);
            } else {
                justFinished = stack.pop();
                System.out.println(justFinished.value);
            }
        }
    }


    public static void main(String[] args) {
        int height = 4;
        TreeNode root = TreeUtils.testData(0, 100, height, 5);
        TreeUtils.printTree(root, height);
        System.out.println("----------in order------------");
        inOrder(root);
        System.out.println("----------pre order-----------");
        preOrder(root);
        System.out.println("----------post order----------");
        postOrder(root);
        System.out.println("----------post order----------");
        postOrderSingleStack(root);
    }
}
