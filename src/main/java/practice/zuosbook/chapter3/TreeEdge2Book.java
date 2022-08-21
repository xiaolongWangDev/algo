package practice.zuosbook.chapter3;

import algorithm.tree.BacktrackBasedTreeTraversal;
import helper.TreeNode;
import helper.TreeUtils;

import java.util.*;

public class TreeEdge2Book {

    public static void printEdge2(TreeNode head, List<TreeNode> result) {
        if (head == null) {
            return;
        }
        result.add(head);
        if (head.left != null && head.right != null) {
            printLeftEdge(head.left, true, result);
            printRightEdge(head.right, true, result);
        } else {
            printEdge2(head.left != null ? head.left : head.right, result);
        }
    }

    public static void printLeftEdge(TreeNode h, boolean print, List<TreeNode> result) {
        if (h == null) {
            return;
        }
        if (print || (h.left == null && h.right == null)) {
            result.add(h);
        }
        printLeftEdge(h.left, print, result);
        printLeftEdge(h.right, print && h.left == null ? true : false, result);
    }

    public static void printRightEdge(TreeNode h, boolean print, List<TreeNode> result) {
        if (h == null) {
            return;
        }
        printRightEdge(h.left, print && h.right == null ? true : false, result);
        printRightEdge(h.right, print, result);
        if (print || (h.left == null && h.right == null)) {
            result.add(h);
        }
    }

}
