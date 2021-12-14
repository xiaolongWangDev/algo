package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GraphUtils {

    public static Graph cloneGraph(Graph graph) {
        Graph clone = new Graph();
        Map<Node, Node> nodeMapping = new HashMap<>();
        Map<Link, Link> linkMapping = new HashMap<>();
        for (Node src : graph.nodes.values()) {
            Node target = new Node();
            target.id = src.id;
            target.inDegree = src.inDegree;
            target.outDegree = src.outDegree;
            nodeMapping.put(src, target);
        }
        for (Link src : graph.links) {
            Link target = new Link();
            target.weight = src.weight;
            target.from = nodeMapping.get(src.from);
            target.to = nodeMapping.get(src.to);
            linkMapping.put(src, target);
        }
        for (Node src : graph.nodes.values()) {
            Node target = nodeMapping.get(src);
            target.links = src.links.stream().map(linkMapping::get).collect(Collectors.toList());
            target.outgoing = src.outgoing.stream().map(linkMapping::get).collect(Collectors.toList());
        }
        clone.links = new ArrayList<>(linkMapping.values());
        clone.nodes = nodeMapping.values().stream().collect(Collectors.toMap(node -> node.id, Function.identity()));

        return clone;
    }

    public static void printAdjacencyMatrix(Graph graph) {
        Integer[][] matrix = toAdjacencyMatrix(graph);
        for (Integer[] rows : matrix) {
            for (Integer cell : rows) {
                System.out.printf("%5d", cell);
            }
            System.out.println();
        }
    }

    public static Integer[][] toAdjacencyMatrix(Graph graph) {
        Integer[][] result = new Integer[graph.nodes.size()][graph.nodes.size()];
        for (Link link : graph.links) {
            result[link.from.id - 1][link.to.id - 1] = link.weight;
        }
        return result;
    }

    public static Graph getTestData(int nNodes, boolean directed, int minWeight, int maxWeight) {
        Graph graph = new Graph();
        for (int i = 1; i <= nNodes; i++) {
            Node node = new Node();
            node.id = i;
            graph.nodes.put(i, node);
        }

        Random random = new Random();

        int linkId = 1;
        for (int i = 1; i <= nNodes; i++) {
            for (int j = 1; j <= nNodes; j++) {
                if (i != j) {
                    int weight = minWeight + random.nextInt(maxWeight - minWeight);
                    if (random.nextInt(10) > 3) {
                        if (directed) {
                            addLinkBetween(graph, weight, linkId++, i, j);
                        } else {
                            if (i < j) {
                                addLinkBetween(graph, weight, linkId++, i, j);
                                addLinkBetween(graph, weight, linkId++, j, i);

                            }
                        }
                    }
                }
            }
        }

        return graph;
    }

    private static void addLinkBetween(Graph graph, int weight, int id, int from, int to) {
        Link link = new Link();
        link.id = id;
        link.from = graph.nodes.get(from);
        link.to = graph.nodes.get(to);
        link.weight = weight;
        graph.links.add(link);

        link.from.outDegree++;
        link.from.links.add(link);
        link.from.outgoing.add(link);
        link.to.inDegree++;
        link.to.links.add(link);
    }
}
