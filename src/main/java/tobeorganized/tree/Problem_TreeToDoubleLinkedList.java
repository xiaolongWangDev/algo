package tobeorganized.tree;

import helper.TreeUtils;

import java.util.List;

import static helper.TreeUtils.printTree;
import static helper.TreeUtils.testData;

public class Problem_TreeToDoubleLinkedList {
    public List<TreeUtils.TreeNode> convert(TreeUtils.TreeNode node) {
        TreeUtils.TreeNode head = node;
        TreeUtils.TreeNode tail = node;
        if (node.left != null) {
            List<TreeUtils.TreeNode> leftListHeadAndTail = convert(node.left);
            head = leftListHeadAndTail.get(0);
            TreeUtils.TreeNode leftTail = leftListHeadAndTail.get(1);
            leftTail.right = node;
            node.left = leftTail;
        }
        if (node.right != null) {
            List<TreeUtils.TreeNode> rightListHeadAndTail = convert(node.right);
            tail = rightListHeadAndTail.get(1);
            TreeUtils.TreeNode rightHead = rightListHeadAndTail.get(0);
            rightHead.left = node;
            node.right = rightHead;
        }
        return List.of(head, tail);
    }

    public static void main(String[] args) {
        Problem_TreeToDoubleLinkedList p = new Problem_TreeToDoubleLinkedList();
        int height = 4;
        TreeUtils.TreeNode testData = testData(0, 100, height);
        printTree(testData, height);
        List<TreeUtils.TreeNode> results = p.convert(testData);
        TreeUtils.TreeNode head = results.get(0);
        TreeUtils.TreeNode tail = results.get(1);
        while (head != null) {
            System.out.printf("%3d", head.value);
            head = head.right;
        }
        System.out.println();
        while (tail != null) {
            System.out.printf("%3d", tail.value);
            tail = tail.left;
        }
    }
}
