package graph;

import heap.MutableHeap;

import java.util.*;

import static graph.GraphUtils.getTestData;
import static graph.GraphUtils.printAdjacencyMatrix;

public class ShortestPath {

    public static class NodeAndDistance {
        Node node;
        int distance;

        public NodeAndDistance(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }

    }

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

    public Map<Node, Integer> dijkstra2(Graph graph, Node startNode) {
        Map<Node, Integer> distanceMap = new HashMap<>();
        graph.nodes.values().forEach(o -> distanceMap.put(o, null));
        MutableHeap<NodeAndDistance, Node> minHeap =
                new MutableHeap<>(Comparator.comparingInt((NodeAndDistance o) -> o.distance).reversed(), o -> o.node);

        minHeap.add(new NodeAndDistance(startNode, 0));
        Set<Node> added = new HashSet<>();
        while (!minHeap.isEmpty()) {
            NodeAndDistance nodeAndDistance = minHeap.poll();
            added.add(nodeAndDistance.node);
            distanceMap.put(nodeAndDistance.node, nodeAndDistance.distance);
            for (Link link : nodeAndDistance.node.outgoing) {
                if (!added.contains(link.to)) {
                    NodeAndDistance existingToNodeDistance = minHeap.getValue(link.to);
                    if (existingToNodeDistance == null) {
                        minHeap.add(new NodeAndDistance(link.to, nodeAndDistance.distance + link.weight));
                    } else {
                        minHeap.setValue(link.to, new NodeAndDistance(link.to, Math.min(existingToNodeDistance.distance, nodeAndDistance.distance + link.weight)));
                    }

                }
            }
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
        Graph testData = getTestData(100, true, 1, 100, false, 5);
//        printAdjacencyMatrix(testData);
        ShortestPath algo = new ShortestPath();
//        Instant time1 = Instant.now();
        Map<Node, Integer> result = algo.dijkstra(testData, testData.nodes.get(1));
//        Instant time2 = Instant.now();
//        result.forEach((k, v) -> System.out.printf("%4d: %5d\n", k.id, v));
//        System.out.println("----");
//        Instant time3 = Instant.now();
        Map<Node, Integer> result2 = algo.dijkstra2(testData, testData.nodes.get(1));
//        Instant time4 = Instant.now();
//        result2.forEach((k, v) -> System.out.printf("%4d: %5d\n", k.id, v));

        for(Map.Entry<Node, Integer> entry: result.entrySet()){
            assert Objects.equals(result2.get(entry.getKey()), entry.getValue());
        }
//        System.out.printf("time comparison: %d vs %d\n", time2.toEpochMilli() - time1.toEpochMilli(), time4.toEpochMilli() - time3.toEpochMilli());
    }
}
