package tree;

import java.util.*;
import java.util.function.Consumer;

import static java.lang.Math.max;

public class TreeUtils {

    public static class Node {
        int value;
        Node left;
        Node right;
    }

    public static int maxHeight(Node root) {
        if (root == null) return 0;
        return max(maxHeight(root.left), maxHeight(root.right)) + 1;
    }

    public static Node clone(Node orig) {
        if (orig == null) return null;
        Node newNode = new Node();
        newNode.value = orig.value;
        newNode.left = clone(orig.left);
        newNode.right = clone(orig.right);
        return newNode;
    }

    public static void fillPlaceholderChildren(Node node, int height) {
        if (height == 1) return;
        if (node.left == null) {
            Node newNode = new Node();
            newNode.value = -1;
            node.left = newNode;
        }
        fillPlaceholderChildren(node.left, height - 1);
        if (node.right == null) {
            Node newNode = new Node();
            newNode.value = -1;
            node.right = newNode;
        }
        fillPlaceholderChildren(node.right, height - 1);
    }

    /**
     * apply the given function to the nodes in pre-order
     */
    public static void iterateAndApply(Node node, Consumer<Node> func) {
        if(node == null) return;
        func.accept(node);
        iterateAndApply(node.left, func);
        iterateAndApply(node.right, func);
    }

    /**
     * find the width of the widest level of a tree.
     * this is simpler than the algorithm provided in the video course
     * https://www.bilibili.com/video/BV13g41157hK?p=6 the problem discussed starting at 1:50:27
     */
    public int widest(Node root) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);
        List<Node> currentLevelNodes = new ArrayList<>();
        int maxWidth = 0;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur.left != null) currentLevelNodes.add(cur.left);
            if (cur.right != null) currentLevelNodes.add(cur.right);
            if(queue.isEmpty()) {
                maxWidth = Math.max(maxWidth, currentLevelNodes.size());
                queue.addAll(currentLevelNodes);
                currentLevelNodes.clear();
            }
        }
        return maxWidth;
    }


    public static void printTree(Node root, int height) {
        root = clone(root);
        fillPlaceholderChildren(root, height);
        Queue<Node> q = new ArrayDeque<>();
        q.add(root);
        List<Node> pendingNodes = new ArrayList<>();
        int level = 1;
        boolean firstInRow = true;
        while (!q.isEmpty() || !(pendingNodes.isEmpty())) {
            if (q.isEmpty()) {
                q.addAll(pendingNodes);
                pendingNodes.clear();
                System.out.println();
                level++;
                firstInRow = true;
            } else {
                Node node = q.poll();
                int padding = (int) Math.pow(2, height - level) * 4;
                if (firstInRow) padding -= Math.pow(2, height - level - 1) * 4;
                if (node.value == -1) {
                    System.out.printf("%" + padding + "s", "");
                } else {
                    System.out.printf("%" + padding + "d", node.value);
                }
                if (node.left != null) pendingNodes.add(node.left);
                if (node.right != null) pendingNodes.add(node.right);
                firstInRow = false;
            }
        }
        System.out.println();
    }

    public static Node testData(int min, int max, int height) {
        Random random = new Random();
        int level = 1;
        List<Node> parentLevelNodes = new ArrayList<>();
        Node root = new Node();
        root.value = min + random.nextInt(max - min);
        parentLevelNodes.add(root);
        List<Node> currentLevelNodes = new ArrayList<>();
        while (level < height) {
            boolean noChildrenAdded = true;
            for (int i = 0; i < parentLevelNodes.size(); i++) {
                Node parentLevelNode = parentLevelNodes.get(i);
                // 20% chance to not add a node
                if (random.nextInt(10) > 1) {
                    Node newLeft = new Node();
                    newLeft.value = min + random.nextInt(max - min);
                    parentLevelNode.left = newLeft;
                    currentLevelNodes.add(newLeft);
                    noChildrenAdded = false;
                }
                // 20% chance to not add a node
                // if no node has been added in this level and this is the last one, we will add it regardless
                if ((i == parentLevelNodes.size() - 1 && noChildrenAdded) || random.nextInt(10) > 1) {
                    Node newRight = new Node();
                    newRight.value = min + random.nextInt(max - min);
                    parentLevelNode.right = newRight;
                    currentLevelNodes.add(newRight);
                    noChildrenAdded = false;
                }
            }
            parentLevelNodes = currentLevelNodes;
            currentLevelNodes = new ArrayList<>();
            level++;
        }
        return root;
    }
}
