package tobeorganized.graph;

import java.util.*;
import java.util.function.Consumer;

import static tobeorganized.graph.GraphUtils.*;

public class Traversal {
    public void breadthFirst(Node startNode, Consumer<Link> resultCollector) {
        Set<Node> explored = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(startNode);
        explored.add(startNode);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (Link link : node.outgoing) {
                if (!explored.contains(link.to)) {
                    resultCollector.accept(link);
                    explored.add(link.to);
                    queue.add(link.to);
                }
            }
        }
    }

    public void depthFirst(Node startNode, Consumer<Link> resultCollector) {
        Stack<Node> stack = new Stack<>();
        Set<Node> explored = new HashSet<>();
        explored.add(startNode);
        Node cur = startNode;
        do {
            boolean shouldBacktrace = true;
            for (Link link : cur.outgoing) {
                if (!explored.contains(link.to)) {
                    resultCollector.accept(link);
                    explored.add(link.to);
                    stack.push(cur);
                    cur = link.to;
                    shouldBacktrace = false;
                    break;
                }
            }
            if (shouldBacktrace) {
                cur = stack.pop();
            }
        } while (!stack.isEmpty());
    }

    public void depthFirstShorter(Node startNode, Consumer<Link> resultCollector) {
        Stack<Node> stack = new Stack<>();
        Set<Node> explored = new HashSet<>();
        explored.add(startNode);
        stack.push(startNode);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            for (Link link : cur.outgoing) {
                if (!explored.contains(link.to)) {
                    resultCollector.accept(link);
                    explored.add(link.to);
                    stack.push(cur);
                    stack.push(link.to);
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Graph testData = getTestData(10, true, 1, 100, false, 0);
        printAdjacencyMatrix(testData);
        Traversal algo = new Traversal();
        System.out.println("BF");
        algo.breadthFirst(testData.nodes.get(1), System.out::println);
        System.out.println("DF");
        algo.depthFirst(testData.nodes.get(1), System.out::println);
    }
}
