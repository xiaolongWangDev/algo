package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Graph {
    Map<Integer, Node> nodes = new HashMap<>();
    List<Link> links = new ArrayList<>();

    @Override
    public String toString() {
        return "Graph{" +
                "\nnodes=" + nodes.values().stream().map(Node::toString).collect(Collectors.joining("\n")) +
                ",\nlinks=" + links.stream().map(Link::toString).collect(Collectors.joining("\n")) +
                '}';
    }
}
