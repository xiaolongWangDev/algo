package graph;

import java.util.*;

import static graph.GraphUtils.getTestData;
import static graph.GraphUtils.printAdjacencyMatrix;

public class ShortestPath {

    public Map<Node, Integer> dijkstra(Graph graph, Node startNode) {
        Map<Node, Integer> distanceMap = new HashMap<>();
        graph.nodes.values().forEach(o -> distanceMap.put(o, null));
        distanceMap.put(startNode, 0);

        Set<Node> added = new HashSet<>();
        Node node = findNearestUnexplored(distanceMap, added);
        while (node != null) {
            added.add(node);
            for (Link link : node.outgoing) {
                Integer existingToNodeDistance = distanceMap.get(link.to);
                if (existingToNodeDistance == null) {
                    distanceMap.put(link.to, distanceMap.get(node) + link.weight);
                } else {
                    distanceMap.put(link.to, Math.min(existingToNodeDistance, distanceMap.get(node) + link.weight));
                }
            }
            node = findNearestUnexplored(distanceMap, added);
        }
        return distanceMap;
    }

    private Node findNearestUnexplored(Map<Node, Integer> distanceMap, Set<Node> added) {
        int min = 0;
        Node nearest = null;
        for (Map.Entry<Node, Integer> entry : distanceMap.entrySet()) {
            if (!added.contains(entry.getKey()) && entry.getValue() != null) {
                if (nearest == null || min > entry.getValue()) {
                    nearest = entry.getKey();
                    min = entry.getValue();
                }
            }
        }

        return nearest;
    }


    public static void main(String[] args) {
        Graph testData = getTestData(10, true, 1, 100, false, 2);
        printAdjacencyMatrix(testData);
        ShortestPath algo = new ShortestPath();
        Map<Node, Integer> result = algo.dijkstra(testData, testData.nodes.get(1));
        result.forEach((k, v) -> System.out.printf("%4d: %5d\n", k.id, v));
    }
}
