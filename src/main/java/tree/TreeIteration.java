package tree;

import java.util.*;
import java.util.function.Consumer;

import static tree.TreeUtils.*;

public class TreeIteration {

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
    private enum IterationOrder {
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

    private static class BstChecker {
        Integer prevValue;
        boolean isBst = true;
        public void check(Node node){
            if(isBst && prevValue != null) {
                isBst = prevValue < node.value;
            }
            prevValue = node.value;
        }
    }

    public boolean isBst(Node root){
        BstChecker checker = new BstChecker();
        shortInOrder(root, checker::check);
        return checker.isBst;
    }

    public static void main(String[] args) {
        int min = 0;
        int max = 100;
        int height = 3;
        Node testDataRoot = testData(min, max, height);
        printTree(testDataRoot, height);
        TreeIteration treeIteration = new TreeIteration();
        List<Node> inOrder = new ArrayList<>();
        treeIteration.recursiveInOrder(testDataRoot, inOrder::add);
//        printOrder(inOrder);
        List<Node> inOrder2 = new ArrayList<>();
        treeIteration.backtrackBased(testDataRoot, inOrder2::add, IterationOrder.IN);
//        printOrder(inOrder2);
        List<Node> inOrder3 = new ArrayList<>();
        treeIteration.shortInOrder(testDataRoot, inOrder3::add);
//        printOrder(inOrder3);
        compareList(inOrder, inOrder2);
        compareList(inOrder, inOrder3);

        List<Node> preOrder = new ArrayList<>();
        treeIteration.recursivePreOrder(testDataRoot, preOrder::add);
//        printOrder(preOrder);
        List<Node> preOrder2 = new ArrayList<>();
        treeIteration.backtrackBased(testDataRoot, preOrder2::add, IterationOrder.PRE);
//        printOrder(preOrder2);
        List<Node> preOrder3 = new ArrayList<>();
        treeIteration.shortPreOrder(testDataRoot, preOrder3::add);
//        printOrder(preOrder3);
        compareList(preOrder, preOrder2);
        compareList(preOrder, preOrder3);


        List<Node> postOrder = new ArrayList<>();
        treeIteration.recursivePostOrder(testDataRoot, postOrder::add);
//        printOrder(postOrder);
        List<Node> postOrder2 = new ArrayList<>();
        treeIteration.backtrackBased(testDataRoot, postOrder2::add, IterationOrder.POST);
//        printOrder(postOrder2);
        List<Node> postOrder3 = new ArrayList<>();
        treeIteration.shortPostOrder(testDataRoot, postOrder3::add);
//        printOrder(postOrder3);
        compareList(postOrder, postOrder2);
        compareList(postOrder, postOrder3);

        List<Node> breadthFirstOrder = new ArrayList<>();
        treeIteration.breadthFirst(testDataRoot, breadthFirstOrder::add);
//        printOrder(breadthFirstOrder);

        System.out.println(treeIteration.isBst(testDataRoot));
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
