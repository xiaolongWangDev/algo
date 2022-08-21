package practice.zuosbook.chapter3;

import algorithm.tree.BacktrackBasedTreeTraversal;
import helper.TreeNode;
import helper.TreeUtils;

import java.util.*;
import java.util.stream.Collectors;

public class TreeEdge1 {
    public static void solve(TreeNode root) {
        List<List<TreeNode>> edgeNodes = getEdgeNodes(root);
        Set<TreeNode> coveredNodes = new HashSet<>();
        coveredNodes.add(root);
        coveredNodes.addAll(edgeNodes.stream().flatMap(Collection::stream).collect(Collectors.toList()));

        List<TreeNode> leafNodes = new ArrayList<>();
        BacktrackBasedTreeTraversal.traverse(BacktrackBasedTreeTraversal.Order.IN, root, (node) -> {
            if (!coveredNodes.contains(node)) {
                leafNodes.add(node);
            }
        });

        System.out.printf("%4d", root.value);
        for(var edgePair: edgeNodes) {
            System.out.printf("%4d", edgePair.get(0).value);
        }
        for(var leafNode: leafNodes) {
            System.out.printf("%4d", leafNode.value);
        }
        for (int i = edgeNodes.size() - 1; i >= 0; i--) {
            List<TreeNode> edgePair = edgeNodes.get(i);
            if(edgePair.size() > 1) {
                System.out.printf("%4d", edgePair.get(1).value);
            }
        }

    }

    private static List<List<TreeNode>> getEdgeNodes(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<TreeNode> waitList = new ArrayList<>();
        queue.add(root);
        List<List<TreeNode>> edgeNodes = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) waitList.add(node.left);
            if (node.right != null) waitList.add(node.right);
            if (queue.isEmpty()) {
                queue.addAll(waitList);
                if (!waitList.isEmpty()) {
                    edgeNodes.add(new ArrayList<>());
                    for (int i = 0; i < waitList.size(); i++) {
                        if (i == 0 || i == waitList.size() - 1) {
                            edgeNodes.get(edgeNodes.size() - 1).add(waitList.get(i));
                        }
                    }
                }
                waitList.clear();
            }
        }
        return edgeNodes;
    }

    public static void main(String[] args) {
        int height = 4;
        TreeNode root = TreeUtils.testData(0, 100, height, 3);
        TreeUtils.printTree(root, height);

        solve(root);
    }
}
