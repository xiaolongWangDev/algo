package algorithm.tree;

import helper.TreeNode;
import helper.TreeUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.function.Consumer;

public class BacktrackBasedTreeTraversal {
    /**
     * traversal order
     */
    public enum Order {
        IN,
        PRE,
        POST
    }

    /**
     * at each tree node, how many directions/ways it can go. So this is not limited to binary tree
     */
    public enum Way {

        LEFT,
        RIGHT;

        // is it a valid way to go from the current node
        boolean isValid(TreeNode node) {
            if (this == LEFT) {
                return node.left != null;
            } else {
                return node.right != null;
            }
        }

        // move to the next node following current way
        public TreeNode go(TreeNode node) {
            if (this == LEFT) {
                return node.left;
            } else {
                return node.right;
            }
        }
    }

    static List<Way> ALL_WAYS = List.of(Way.LEFT, Way.RIGHT);

    // what to be kept in the stack, your current node, and renaming ways to try
    public static class Context {
        TreeNode node;
        Queue<Way> waysToGo;

        public Context(TreeNode node, Queue<Way> waysToGo) {
            this.node = node;
            this.waysToGo = waysToGo;
        }
    }

    public static void traverse(Order order, TreeNode root, Consumer<TreeNode> businessLogic) {
        Stack<Context> stack = new Stack<>();

        // initial node and ways
        TreeNode cur = root;
        Queue<Way> waysToGo = new LinkedList<>(ALL_WAYS);

        while (true) {
            // find a valid way
            Way wayToGo = null;
            while (true) {

                // pre-order: run business logic before any of my children's is run
                if (waysToGo.size() == ALL_WAYS.size() && Order.PRE == order) {
                    businessLogic.accept(cur);
                }

                // in-order: run business logic before running the last child's
                if (waysToGo.size() == 1 && Order.IN == order) {
                    businessLogic.accept(cur);
                }

                // this does 2 things: 1, run business logic if post-order is required. 2. break out of the loop
                if (waysToGo.size() == 0) {
                    // post-order: run business logic after running all children's
                    if (Order.POST == order) {
                        businessLogic.accept(cur);
                    }
                    break;
                }

                Way candidate = waysToGo.poll();

                // only use the way when it's valid, otherwise we'll loop around and try the next way
                if (candidate.isValid(cur)) {
                    wayToGo = candidate;
                    break;
                }
            }

            // when there's no way going "down", we backtrack
            if (wayToGo == null) {
                if (stack.isEmpty()) return; // no history to back track to, we finished!

                // manually context switch to the last in history (stack)
                Context prevContext = stack.pop();
                cur = prevContext.node;
                waysToGo = prevContext.waysToGo;
            } else {
                // push current step's context to the stack so that we can backtrack later
                stack.push(new Context(cur, waysToGo));

                // next updating cur and ways
                //
                // go the selected way
                cur = wayToGo.go(cur);
                // refresh the ways, so the new step will start will all available ways
                waysToGo = new LinkedList<>(ALL_WAYS);
            }
        }
    }

    public static void main(String[] args) {
        int height = 4;
        TreeNode root = TreeUtils.testData(0, 100, height, 3);
        TreeUtils.printTree(root, height);
        traverse(Order.PRE, root, (node) -> System.out.printf("%4d", node.value));
        System.out.println();
        traverse(Order.IN, root, (node) -> System.out.printf("%4d", node.value));
        System.out.println();
        traverse(Order.POST, root, (node) -> System.out.printf("%4d", node.value));
        System.out.println();
    }

}
