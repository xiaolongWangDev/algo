package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Node {
    int id;
    int inDegree;
    int outDegree;
    List<Link> links = new ArrayList<>();
    List<Link> outgoing = new ArrayList<>();

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", inDegree=" + inDegree +
                ", outDegree=" + outDegree +
                ", links=" + links.stream().map(l -> l.id).collect(Collectors.toList()) +
                ", outgoing=" + outgoing.stream().map(l -> l.id).collect(Collectors.toList()) +
                '}';
    }
}
