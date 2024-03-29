package tobeorganized.tree;

import helper.TreeNode;
import helper.TreeUtils;

import java.util.*;
import java.util.function.Consumer;

import static helper.TreeUtils.testData;

public class TreeTraversal {

    // recursive fashion
    public void recursiveInOrder(TreeNode node, Consumer<TreeNode> resultCollector) {
        if (node == null) return;
        recursiveInOrder(node.left, resultCollector);
        resultCollector.accept(node);
        recursiveInOrder(node.right, resultCollector);
    }

    public void recursivePreOrder(TreeNode node, Consumer<TreeNode> resultCollector) {
        if (node == null) return;
        resultCollector.accept(node);
        recursivePreOrder(node.left, resultCollector);
        recursivePreOrder(node.right, resultCollector);
    }

    public void recursivePostOrder(TreeNode node, Consumer<TreeNode> resultCollector) {
        if (node == null) return;
        recursivePostOrder(node.left, resultCollector);
        recursivePostOrder(node.right, resultCollector);
        resultCollector.accept(node);
    }


    // non-recursive fashion, DFS based
    protected enum IterationOrder {
        PRE,
        POST,
        IN
    }

    public void shortPreOrder(TreeNode root, Consumer<TreeNode> resultCollector) {
        TreeNode cur = root;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                resultCollector.accept(cur);
                stack.push(cur.right);
                stack.push(cur.left);
            }
            cur = stack.pop();
        }
    }

    public void shortPostOrder(TreeNode root, Consumer<TreeNode> resultCollector) {
        TreeNode cur = root;
        Stack<TreeNode> reverseStack = new Stack<>();
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                reverseStack.add(cur);
                stack.push(cur.left);
                stack.push(cur.right);
            }
            cur = stack.pop();
        }
        while (!reverseStack.isEmpty()) {
            resultCollector.accept(reverseStack.pop());
        }
    }


    public void shortInOrder(TreeNode root, Consumer<TreeNode> resultCollector) {
        TreeNode cur = root;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                resultCollector.accept(cur);
                cur = cur.right;
            }
        }
    }

    public void breadthFirst(TreeNode root, Consumer<TreeNode> resultCollector) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            resultCollector.accept(cur);
            if (cur.left != null) queue.add(cur.left);
            if (cur.right != null) queue.add(cur.right);
        }
    }

    public void morris(TreeNode root, Consumer<TreeNode> resultCollector, IterationOrder order) {
        if (root == null) return;
        TreeNode cur = root;
        TreeNode rightmostInLeftTree;
        while (cur != null) {
            if (cur.left != null) {
                rightmostInLeftTree = cur.left;
                while (rightmostInLeftTree.right != null
                        && rightmostInLeftTree.right != cur // when it's search the 2nd time, it'll point to cur
                ) {
                    rightmostInLeftTree = rightmostInLeftTree.right;
                }
                if (rightmostInLeftTree.right == null) {
                    // cur being traversed the 1st time
                    rightmostInLeftTree.right = cur;
                    if (order == IterationOrder.PRE) {
                        resultCollector.accept(cur);
                    }
                    cur = cur.left;

                    continue;
                } else {
                    // cur being traversed the 2nd time
                    rightmostInLeftTree.right = null;
                    if (order == IterationOrder.IN) {
                        resultCollector.accept(cur);
                    }
                    if (order == IterationOrder.POST) {
                        collectRightEdgeBottomUp(cur.left, resultCollector);
                    }
                }
            } else {
                if (order == IterationOrder.IN || order == IterationOrder.PRE) {
                    resultCollector.accept(cur);
                }
            }
            cur = cur.right;
        }

        if (order == IterationOrder.POST) {
            collectRightEdgeBottomUp(root, resultCollector);
        }
    }

    private void collectRightEdgeBottomUp(TreeNode root, Consumer<TreeNode> resultCollector) {
        TreeNode bottomNode = reverseRightEdge(root);
        TreeNode cur = bottomNode;
        while (cur != null) {
            resultCollector.accept(cur);
            cur = cur.right;
        }
        reverseRightEdge(bottomNode);
    }

    private TreeNode reverseRightEdge(TreeNode root) {
        TreeNode cur = root;
        TreeNode pre = null;
        while (cur != null) {
            TreeNode temp = cur.right;
            cur.right = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    // some handy utils for others to use
    public static class NthNodeResult {
        TreeNode node;
        int count;

        public NthNodeResult(TreeNode node, int count) {
            this.node = node;
            this.count = count;
        }
    }

    public TreeNode getRandomNode(TreeNode root) {
        Random random = new Random();
        int stopAt = 1 + random.nextInt(count(root));
//        System.out.println(stopAt);
        return nthNodeInOrder(root, stopAt).node;
    }

    private static class Counter {
        int count;

        public void add() {
            count++;
        }
    }

    private int count(TreeNode root) {
        Counter counter = new Counter();
        shortInOrder(root, node -> counter.add());
        return counter.count;
    }

    /**
     * this finds the nth node in the tree in in-order.
     * it's more of a practice of using divide and conquer, so it might not be the optimal way of doing this
     */
    public NthNodeResult nthNodeInOrder(TreeNode node, int n) {
        if (node == null) {
            return new NthNodeResult(null, 0);
        }
        NthNodeResult leftResult = nthNodeInOrder(node.left, n);
        if (leftResult.node != null) {
            return new NthNodeResult(leftResult.node, 0);
        }
        if (leftResult.count + 1 == n) {
            return new NthNodeResult(node, 0);
        }
        NthNodeResult rightResult = nthNodeInOrder(node.right, n - leftResult.count - 1);
        if (rightResult.node != null) {
            return new NthNodeResult(rightResult.node, 0);
        }
        return new NthNodeResult(null, leftResult.count + 1 + rightResult.count);
    }

    public static void main(String[] args) {
        int min = 0;
        int max = 100;
        int height = 10;
        TreeNode testDataRoot = testData(min, max, height);
//        printTree(testDataRoot, height);
        TreeTraversal treeTraversal = new TreeTraversal();
        System.out.println();
        List<TreeNode> result = new ArrayList<>();
        treeTraversal.morris(testDataRoot, result::add, IterationOrder.PRE);
//        printOrder(result);
        List<TreeNode> expected = new ArrayList<>();
        treeTraversal.shortPreOrder(testDataRoot, expected::add);
        TreeUtils.compareList(expected, result);

        result.clear();
        expected.clear();
        treeTraversal.morris(testDataRoot, result::add, IterationOrder.IN);
//        printOrder(result);
        treeTraversal.shortInOrder(testDataRoot, expected::add);
        TreeUtils.compareList(expected, result);

        result.clear();
        expected.clear();
        treeTraversal.morris(testDataRoot, result::add, IterationOrder.POST);
//        printOrder(result);
        treeTraversal.shortPostOrder(testDataRoot, expected::add);
        TreeUtils.compareList(expected, result);

    }

    private static void myOldTests(TreeTraversal treeTraversal, TreeNode testDataRoot) {
        List<TreeNode> inOrder = new ArrayList<>();
        treeTraversal.recursiveInOrder(testDataRoot, inOrder::add);
//        printOrder(inOrder);
        List<TreeNode> inOrder3 = new ArrayList<>();
        treeTraversal.shortInOrder(testDataRoot, inOrder3::add);
//        printOrder(inOrder3);
        TreeUtils.compareList(inOrder, inOrder3);

        List<TreeNode> preOrder = new ArrayList<>();
        treeTraversal.recursivePreOrder(testDataRoot, preOrder::add);
//        printOrder(preOrder);
        List<TreeNode> preOrder3 = new ArrayList<>();
        treeTraversal.shortPreOrder(testDataRoot, preOrder3::add);
//        printOrder(preOrder3);
        TreeUtils.compareList(preOrder, preOrder3);


        List<TreeNode> postOrder = new ArrayList<>();
        treeTraversal.recursivePostOrder(testDataRoot, postOrder::add);
//        printOrder(postOrder);
        List<TreeNode> postOrder3 = new ArrayList<>();
        treeTraversal.shortPostOrder(testDataRoot, postOrder3::add);
//        printOrder(postOrder3);
        TreeUtils.compareList(postOrder, postOrder3);

        List<TreeNode> breadthFirstOrder = new ArrayList<>();
        treeTraversal.breadthFirst(testDataRoot, breadthFirstOrder::add);
//        printOrder(breadthFirstOrder);
    }

    private static void printOrder(List<TreeNode> nodes) {
        nodes.forEach(o -> System.out.printf("%4d", o.value));
        System.out.println();
    }

}
