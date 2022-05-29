package code.graph;

import code.set.UnionFindSet;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import static code.graph.GraphUtils.getTestData;
import static code.graph.GraphUtils.*;

public class MinimumSpanningTree {

    public void kruskal(Graph graph, Consumer<Link> resultCollector) {
        PriorityQueue<Link> heap = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));
        heap.addAll(graph.links);
//        MyUnionFind uf = new MyUnionFind(graph.nodes.values());
        UnionFindSet<Node> uf = new UnionFindSet<>(graph.nodes.values());
        Set<Node> added = new HashSet<>();
        while (!heap.isEmpty() && added.size() != graph.nodes.size()) {
            Link link = heap.poll();
//            if (!uf.inSameSet(link.from, link.to)) {
            if (!uf.isInSameSet(link.from, link.to)) {
                resultCollector.accept(link);
                uf.union(link.from, link.to);
                added.add(link.from);
                added.add(link.to);
            }
        }
    }

    public void prim(Graph graph, Consumer<Link> resultCollector) {
        Set<Node> added = new HashSet<>();
        PriorityQueue<Link> heap = new PriorityQueue<>(Comparator.comparingInt(l -> l.weight));
        for (Node node : graph.nodes.values()) {
            if (!added.contains(node)) {
                added.add(node);
                heap.addAll(node.outgoing);
                while (!heap.isEmpty()) {
                    Link minimumLink = heap.poll();
                    if (!added.contains(minimumLink.to)) {
                        resultCollector.accept(minimumLink);
                        Node toNode = minimumLink.to;
                        added.add(toNode);
                        heap.addAll(toNode.outgoing);
                    }
                }
            }
        }
    }


    /**
     * Suboptimal implementation of a Union Find
     */
    private static class MyUnionFind {
        Map<Node, Set<Node>> sets;

        public MyUnionFind(Collection<Node> nodes) {
            sets = nodes.stream().collect(Collectors.toMap(Function.identity(), o -> {
                HashSet<Node> set = new HashSet<>();
                set.add(o);
                return set;
            }));
        }

        boolean inSameSet(Node a, Node b) {
            return sets.get(a) == sets.get(b);
        }

        void union(Node a, Node b) {
            sets.get(a).addAll(sets.get(b));
            for (Node node : sets.get(b)) {
                if (node != b) {
                    sets.put(node, sets.get(a));
                }
            }
            sets.put(b, sets.get(a));
        }

    }

    public static void main(String[] args) {
        Graph testData = getTestData(5, false, 1, 100, false, 2);
        printAdjacencyMatrix(testData);
        MinimumSpanningTree algo = new MinimumSpanningTree();
        List<Link> results = new ArrayList<>();
        algo.kruskal(testData, results::add);
        results.forEach(System.out::println);
        results.clear();
        algo.prim(testData, results::add);
        results.forEach(System.out::println);
    }
}
