package algorithm.tree;

import helper.TreeNode;
import helper.TreeUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.function.Consumer;

public class BacktrackBasedTreeTraversal {
    public enum Order {
        IN,
        PRE,
        POST
    }

    public enum Way {

        LEFT,
        RIGHT;

        boolean isValid(TreeNode node) {
            if (this == LEFT) {
                return node.left != null;
            } else {
                return node.right != null;
            }
        }

        public TreeNode go(TreeNode node) {
            if (this == LEFT) {
                return node.left;
            } else {
                return node.right;
            }
        }
    }

    static List<Way> ALL_WAYS = List.of(Way.LEFT, Way.RIGHT);

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

        TreeNode cur = root;
        Queue<Way> waysToGo = new LinkedList<>(ALL_WAYS);
        while (true) {
            Way wayToGo = null;
            while (true) {
                if (waysToGo.size() == ALL_WAYS.size() && Order.PRE == order) {
                    businessLogic.accept(cur);
                }

                if (waysToGo.size() == 1 && Order.IN == order) {
                    businessLogic.accept(cur);
                }

                if (waysToGo.size() == 0) {
                    if (Order.POST == order) {
                        businessLogic.accept(cur);
                    }
                    break;
                }

                Way candidate = waysToGo.poll();
                if (candidate.isValid(cur)) {
                    wayToGo = candidate;
                    break;
                }
            }

            if (wayToGo == null) {
                if (stack.isEmpty()) return;
                Context prevContext = stack.pop();
                cur = prevContext.node;
                waysToGo = prevContext.waysToGo;
            } else {
                stack.push(new Context(cur, waysToGo));
                cur = wayToGo.go(cur);
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
