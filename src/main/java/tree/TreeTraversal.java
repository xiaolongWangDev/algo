package tree;

import java.util.*;
import java.util.function.Consumer;

import static tree.TreeUtils.*;

public class TreeTraversal {

    private static final Queue<Way> ALL_WAYS = new ArrayDeque<>(List.of(Way.LEFT, Way.RIGHT));

    // recursive fashion
    public void recursiveInOrder(Node node, Consumer<Node> resultCollector) {
        if (node == null) return;
        recursiveInOrder(node.left, resultCollector);
        resultCollector.accept(node);
        recursiveInOrder(node.right, resultCollector);
    }

    public void recursivePreOrder(Node node, Consumer<Node> resultCollector) {
        if (node == null) return;
        resultCollector.accept(node);
        recursivePreOrder(node.left, resultCollector);
        recursivePreOrder(node.right, resultCollector);
    }

    public void recursivePostOrder(Node node, Consumer<Node> resultCollector) {
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

    // non-recursive fashion, DFS based
    private enum Way {
        LEFT,
        RIGHT
    }

    private static class Context {
        Node node;
        Queue<Way> ways;

        public Context(Node node, Queue<Way> ways) {
            this.node = node;
            this.ways = ways;
        }
    }

    public void backtrackBased(Node root, Consumer<Node> resultCollector, IterationOrder order) {
        Node cur = root;
        Queue<Way> ways = new ArrayDeque<>(ALL_WAYS);
        Stack<Context> contextStack = new Stack<>();
        while (true) {
            Way way = null;

            // determine which way to go in depth next
            while (true) {
                // pre-order: run business logic before any of my children's is run
                if (order == IterationOrder.PRE && ways.size() == 2) {
                    resultCollector.accept(cur);
                }
                // in-order: run business logic before running the last child's
                if (order == IterationOrder.IN && ways.size() == 1) {
                    resultCollector.accept(cur);
                }
                // this does 2 things: 1, run business logic if post-order is required. 2. break out of the loop
                if (ways.size() == 0) {
                    // post-order: run business logic after running all children's
                    if (order == IterationOrder.POST) {
                        resultCollector.accept(cur);
                    }
                    break;
                }

                Way poppedWay = ways.poll();
                // only use the way when it's valid
                if (poppedWay == Way.LEFT && cur.left != null) {
                    way = poppedWay;
                    break;
                }

                if (poppedWay == Way.RIGHT && cur.right != null) {
                    way = poppedWay;
                    break;
                }
            }

            // when there's no way going "down", we backtrack
            if (way == null) {
                // no history to back track to, we finished!
                if (contextStack.isEmpty()) {
                    break;
                } else {
                    // manually context switch to the last in history (stack)
                    Context popped = contextStack.pop();
                    cur = popped.node;
                    ways = popped.ways;
                }
            } else {
                // push current step's context to the stack so that we can backtrack later
                contextStack.push(new Context(cur, new ArrayDeque<>(ways)));

                // next updating cur and ways
                //
                // "walk" the selected way
                if (way == Way.LEFT) {
                    cur = cur.left;
                } else {
                    cur = cur.right;
                }
                // refresh the ways, so the new step will start will all available ways
                ways = new ArrayDeque<>(ALL_WAYS);
            }
        }
    }

    public void shortPreOrder(Node root, Consumer<Node> resultCollector) {
        Node cur = root;
        Stack<Node> stack = new Stack<>();
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                resultCollector.accept(cur);
                stack.push(cur.right);
                stack.push(cur.left);
            }
            cur = stack.pop();
        }
    }

    public void shortPostOrder(Node root, Consumer<Node> resultCollector) {
        Node cur = root;
        Stack<Node> reverseStack = new Stack<>();
        Stack<Node> stack = new Stack<>();
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


    public void shortInOrder(Node root, Consumer<Node> resultCollector) {
        Node cur = root;
        Stack<Node> stack = new Stack<>();
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

    public void breadthFirst(Node root, Consumer<Node> resultCollector) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            resultCollector.accept(cur);
            if (cur.left != null) queue.add(cur.left);
            if (cur.right != null) queue.add(cur.right);
        }
    }

    public void morris(Node root, Consumer<Node> resultCollector, IterationOrder order) {
        if (root == null) return;
        Node cur = root;
        Node rightmostInLeftTree;
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

    private void collectRightEdgeBottomUp(Node root, Consumer<Node> resultCollector) {
        Node bottomNode = reverseRightEdge(root);
        Node cur = bottomNode;
        while (cur != null) {
            resultCollector.accept(cur);
            cur = cur.right;
        }
        reverseRightEdge(bottomNode);
    }

    private Node reverseRightEdge(Node root) {
        Node cur = root;
        Node pre = null;
        while (cur != null) {
            Node temp = cur.right;
            cur.right = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    // some handy utils for others to use
    public static class NthNodeResult {
        Node node;
        int count;

        public NthNodeResult(Node node, int count) {
            this.node = node;
            this.count = count;
        }
    }

    public Node getRandomNode(Node root) {
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

    private int count(Node root) {
        Counter counter = new Counter();
        shortInOrder(root, node -> counter.add());
        return counter.count;
    }

    /**
     * this finds the nth node in the tree in in-order.
     * it's more of a practice of using divide and conquer, so it might not be the optimal way of doing this
     */
    public NthNodeResult nthNodeInOrder(Node node, int n) {
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
        Node testDataRoot = testData(min, max, height);
//        printTree(testDataRoot, height);
        TreeTraversal treeTraversal = new TreeTraversal();
        System.out.println();
        List<Node> result = new ArrayList<>();
        treeTraversal.morris(testDataRoot, result::add, IterationOrder.PRE);
//        printOrder(result);
        List<Node> expected = new ArrayList<>();
        treeTraversal.shortPreOrder(testDataRoot, expected::add);
        compareList(expected, result);

        result.clear();
        expected.clear();
        treeTraversal.morris(testDataRoot, result::add, IterationOrder.IN);
//        printOrder(result);
        treeTraversal.shortInOrder(testDataRoot, expected::add);
        compareList(expected, result);

        result.clear();
        expected.clear();
        treeTraversal.morris(testDataRoot, result::add, IterationOrder.POST);
//        printOrder(result);
        treeTraversal.shortPostOrder(testDataRoot, expected::add);
        compareList(expected, result);

    }

    private static void myOldTests(TreeTraversal treeTraversal, Node testDataRoot) {
        List<Node> inOrder = new ArrayList<>();
        treeTraversal.recursiveInOrder(testDataRoot, inOrder::add);
//        printOrder(inOrder);
        List<Node> inOrder2 = new ArrayList<>();
        treeTraversal.backtrackBased(testDataRoot, inOrder2::add, IterationOrder.IN);
//        printOrder(inOrder2);
        List<Node> inOrder3 = new ArrayList<>();
        treeTraversal.shortInOrder(testDataRoot, inOrder3::add);
//        printOrder(inOrder3);
        compareList(inOrder, inOrder2);
        compareList(inOrder, inOrder3);

        List<Node> preOrder = new ArrayList<>();
        treeTraversal.recursivePreOrder(testDataRoot, preOrder::add);
//        printOrder(preOrder);
        List<Node> preOrder2 = new ArrayList<>();
        treeTraversal.backtrackBased(testDataRoot, preOrder2::add, IterationOrder.PRE);
//        printOrder(preOrder2);
        List<Node> preOrder3 = new ArrayList<>();
        treeTraversal.shortPreOrder(testDataRoot, preOrder3::add);
//        printOrder(preOrder3);
        compareList(preOrder, preOrder2);
        compareList(preOrder, preOrder3);


        List<Node> postOrder = new ArrayList<>();
        treeTraversal.recursivePostOrder(testDataRoot, postOrder::add);
//        printOrder(postOrder);
        List<Node> postOrder2 = new ArrayList<>();
        treeTraversal.backtrackBased(testDataRoot, postOrder2::add, IterationOrder.POST);
//        printOrder(postOrder2);
        List<Node> postOrder3 = new ArrayList<>();
        treeTraversal.shortPostOrder(testDataRoot, postOrder3::add);
//        printOrder(postOrder3);
        compareList(postOrder, postOrder2);
        compareList(postOrder, postOrder3);

        List<Node> breadthFirstOrder = new ArrayList<>();
        treeTraversal.breadthFirst(testDataRoot, breadthFirstOrder::add);
//        printOrder(breadthFirstOrder);
    }

    private static void compareList(List<Node> target, List<Node> real) {
        if (target.size() != real.size()) {
            throw new RuntimeException("Not the same size");
        }
        for (int i = 0; i < target.size(); i++) {
            if (target.get(i) != real.get(i)) {
                throw new RuntimeException(String.format("difference found at index %d. target: %d, real: %d", i, target.get(i).value, real.get(i).value));
            }
        }
    }

    private static void printOrder(List<Node> nodes) {
        nodes.forEach(o -> System.out.printf("%4d", o.value));
        System.out.println();
    }

}
