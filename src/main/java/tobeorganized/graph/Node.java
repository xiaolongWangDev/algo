package tobeorganized.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Node {
    int id;
    int inDegree;
    int outDegree;
    List<Link> links = new ArrayList<>();
    List<Link> outgoing = new ArrayList<>();

    // not required. added for certain problems
    Object data;

    public List<Link> getIncoming(){
        List<Link> incoming = new ArrayList<>(links);
        incoming.removeAll(outgoing);
        return incoming;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                "data=" + ((data == null) ? "" : data) +
                ", inDegree=" + inDegree +
                ", outDegree=" + outDegree +
                ", links=" + links.stream().map(l -> l.id).collect(Collectors.toList()) +
                ", outgoing=" + outgoing.stream().map(l -> l.id).collect(Collectors.toList()) +
                '}';
    }
}
