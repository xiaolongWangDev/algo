package practice.zuosbook.chapter2;

import helper.DListNode;
import helper.ListUtils;
import helper.TreeNode;

public class BinaryTreeToDlist {

    public static class HeadAndTail {
        DListNode head;
        DListNode tail;

        public HeadAndTail(DListNode head, DListNode tail) {
            this.head = head;
            this.tail = tail;
        }
    }

    public static HeadAndTail solve(TreeNode root) {
        if (root == null) return null;
        DListNode node = new DListNode();
        node.val = root.value;

        HeadAndTail leftResult = solve(root.left);
        HeadAndTail rightResult = solve(root.right);
        DListNode head = leftResult == null ? node : leftResult.head;
        DListNode tail = rightResult == null ? node : rightResult.tail;

        if(leftResult != null) {
            node.prev = leftResult.tail;
            leftResult.tail.next = node;
        }
        if(rightResult != null) {
            rightResult.head.prev = node;
            node.next = rightResult.head;
        }

        return new HeadAndTail(head, tail);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode();
        root.value = 6;
        root.left = new TreeNode();
        root.right = new TreeNode();
        root.left.value = 4;
        root.left.left = new TreeNode();
        root.left.right = new TreeNode();
        root.left.left.value = 2;
        root.left.right.value = 5;
        root.left.left.left = new TreeNode();
        root.left.left.right = new TreeNode();
        root.left.left.left.value = 1;
        root.left.left.right.value = 3;
        root.right.value = 7;
        root.right.right = new TreeNode();
        root.right.right.value = 9;
        root.right.right.left = new TreeNode();
        root.right.right.left.value = 8;

        ListUtils.printDList(solve(root).head);
    }
}
