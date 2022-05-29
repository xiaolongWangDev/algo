package code.tree;

import java.util.List;

import static code.tree.TreeUtils.printTree;
import static code.tree.TreeUtils.testData;

public class Problem_TreeToDoubleLinkedList {
    public List<TreeUtils.Node> convert(TreeUtils.Node node) {
        TreeUtils.Node head = node;
        TreeUtils.Node tail = node;
        if (node.left != null) {
            List<TreeUtils.Node> leftListHeadAndTail = convert(node.left);
            head = leftListHeadAndTail.get(0);
            TreeUtils.Node leftTail = leftListHeadAndTail.get(1);
            leftTail.right = node;
            node.left = leftTail;
        }
        if (node.right != null) {
            List<TreeUtils.Node> rightListHeadAndTail = convert(node.right);
            tail = rightListHeadAndTail.get(1);
            TreeUtils.Node rightHead = rightListHeadAndTail.get(0);
            rightHead.left = node;
            node.right = rightHead;
        }
        return List.of(head, tail);
    }

    public static void main(String[] args) {
        Problem_TreeToDoubleLinkedList p = new Problem_TreeToDoubleLinkedList();
        int height = 4;
        TreeUtils.Node testData = testData(0, 100, height);
        printTree(testData, height);
        List<TreeUtils.Node> results = p.convert(testData);
        TreeUtils.Node head = results.get(0);
        TreeUtils.Node tail = results.get(1);
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
