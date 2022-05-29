package code.graph;

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
            target.data = src.data;
            target.inDegree = src.inDegree;
            target.outDegree = src.outDegree;
            nodeMapping.put(src, target);
        }
        for (Link src : graph.links) {
            Link target = new Link();
            target.id = src.id;
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

    public static Graph fromAdjacencyMatrix(Integer[][] matrix) {
        int n = matrix.length;
        Graph graph = new Graph();
        for (int i = 0; i < n; i++) {
            Node node = new Node();
            // let id start from 1
            node.id = i + 1;
            graph.nodes.put(i + 1, node);
        }

        int linkId = 1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                Integer weight = matrix[i][j];
                if (weight != null && weight != 0) {
                    Node from = graph.nodes.get(i + 1);
                    Node to = graph.nodes.get(j + 1);
                    Link link = new Link();
                    link.id = linkId++;
                    link.from = from;
                    link.to = to;
                    link.weight = weight;
                    from.links.add(link);
                    from.outgoing.add(link);
                    from.outDegree++;
                    to.links.add(link);
                    to.inDegree++;
                    graph.links.add(link);
                }
            }
        }
        return graph;
    }

    public static void printAdjacencyMatrix(Graph graph) {
        printAdjacencyMatrix(graph, false);
    }

    public static void printAdjacencyMatrix(Graph graph, boolean nullToZero) {
        Integer[][] matrix = toAdjacencyMatrix(graph, nullToZero);
        for (Integer[] rows : matrix) {
            for (Integer cell : rows) {
                System.out.printf("%5d", cell);
            }
            System.out.println();
        }
    }

    public static Integer[][] toAdjacencyMatrix(Graph graph, boolean nullToZero) {
        Integer[][] result = new Integer[graph.nodes.size()][graph.nodes.size()];
        for (Link link : graph.links) {
            result[link.from.id - 1][link.to.id - 1] = link.weight;
        }
        if (nullToZero) {
            for (Integer[] row : result) {
                for (int i = 0; i < row.length; i++) {
                    if (row[i] == null) row[i] = 0;
                }
            }
        }
        return result;
    }

    public static Graph getTestData(int nNodes, boolean directed, int minWeight, int maxWeight, boolean loopFree, int sparseLevel) {
        Graph graph = new Graph();
        for (int i = 1; i <= nNodes; i++) {
            Node node = new Node();
            node.id = i;
            graph.nodes.put(i, node);
        }

        Random random = new Random();

        int linkId = 1;
        for (int i = 1; i <= nNodes; i++) {
            for (int j = loopFree ? i + 1 : 1; j <= nNodes; j++) {
                if (i != j) {
                    int weight = minWeight + random.nextInt(maxWeight - minWeight);
                    if (random.nextInt(10) >= sparseLevel) {
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
