package practice.zuosbook.chapter3;

import algorithm.tree.BacktrackBasedTreeTraversal;
import helper.TreeNode;
import helper.TreeUtils;

import java.util.*;
import java.util.stream.Collectors;

public class TreeEdge2 {

    public static void solve(TreeNode root, List<TreeNode> result) {
        TreeNode cur = root;

        LinkedHashSet<TreeNode> leftNodes = new LinkedHashSet<>();
        while (cur != null) {
            leftNodes.add(cur);
            cur = cur.left != null ? cur.left : cur.right;
        }

        Stack<TreeNode> rightNodes = new Stack<>();
        cur = root;
        while (cur != null) {
            if (!leftNodes.contains(cur)) {
                rightNodes.push(cur);
            }
            cur = cur.right != null ? cur.right : cur.left;
        }

        Set<TreeNode> coveredNodes = new HashSet<>();
        coveredNodes.addAll(leftNodes);
        coveredNodes.addAll(rightNodes);

        List<TreeNode> leafNodes = new ArrayList<>();
        BacktrackBasedTreeTraversal.traverse(BacktrackBasedTreeTraversal.Order.IN, root, (node) -> {
            if (!coveredNodes.contains(node) && node.left == null && node.right == null) {
                leafNodes.add(node);
            }
        });

        for (var leftNode : leftNodes) {
            result.add(leftNode);
//            System.out.printf("%4d", leftNode.value);
        }
//        System.out.println();
        for (var leafNode : leafNodes) {
            result.add(leafNode);
//            System.out.printf("%4d", leafNode.value);
        }
        System.out.println();
        while (!rightNodes.isEmpty()) {
            result.add(rightNodes.pop());
//            System.out.printf("%4d", rightNodes.pop().value);
        }
    }

    public static void main(String[] args) {
        int height = 10;
        TreeNode root = TreeUtils.testData(0, 100, height, 3);
//        TreeUtils.printTree(root, height);

        List<TreeNode> myResults = new ArrayList<>();
        solve(root, myResults);
//        myResults.forEach(o -> System.out.printf("%4d", o.value));

        List<TreeNode> bookResults = new ArrayList<>();
        TreeEdge2Book.printEdge2(root, bookResults);
//        bookResults.forEach(o -> System.out.printf("%4d", o.value));

        // so my solution yields the same results as the book, but it's more straightforward to understand
        TreeUtils.compareList(bookResults, myResults);
    }


}
