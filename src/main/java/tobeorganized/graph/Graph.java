package tobeorganized.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Graph {
    Map<Integer, Node> nodes = new HashMap<>();
    List<Link> links = new ArrayList<>();

    public void removeNode(Node node){
        this.nodes.remove(node.id);
        for (Link link : node.links) {
            if(node == link.from){
                link.to.inDegree--;
                link.to.links.remove(link);
            } else {
                link.from.outDegree--;
                link.from.links.remove(link);
                link.from.outgoing.remove(link);
            }
            this.links.remove(link);
        }
        node.inDegree = 0;
        node.outDegree = 0;
        node.outgoing.clear();
        node.links.clear();
    }

    @Override
    public String toString() {
        return "Graph{" +
                "\nnodes=" + nodes.values().stream().map(Node::toString).collect(Collectors.joining("\n")) +
                ",\nlinks=" + links.stream().map(Link::toString).collect(Collectors.joining("\n")) +
                '}';
    }
}
