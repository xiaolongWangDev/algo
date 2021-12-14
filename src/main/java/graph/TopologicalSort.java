package graph;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import static graph.GraphUtils.*;

public class TopologicalSort {
    public void sort(Graph graph, Consumer<Node> resultCollector) {
        Map<Node, Integer> inDegreeMap = graph.nodes.values().stream().collect(Collectors.toMap(Function.identity(), o -> o.inDegree));

        Queue<Node> queue = new LinkedList<>();
        for (Map.Entry<Node, Integer> entry : inDegreeMap.entrySet()) {
            if (entry.getValue() == 0) {
                Node key = entry.getKey();
                queue.add(key);
            }
        }
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            resultCollector.accept(node);
            for(Link link : node.outgoing) {
                inDegreeMap.put(link.to, inDegreeMap.get(link.to) - 1);
                if(inDegreeMap.get(link.to) == 0) {
                    queue.add(link.to);
                }
            }
        }
    }

    public static void main(String[] args) {
        Graph testData = getTestData(10, true, 1, 100, true, 6);
        printAdjacencyMatrix(testData);
        TopologicalSort algo = new TopologicalSort();
        List<Node> results = new ArrayList<>();
        algo.sort(testData, results::add);
        results.forEach(System.out::println);
    }
}
