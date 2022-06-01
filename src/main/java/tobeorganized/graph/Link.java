package tobeorganized.graph;

public class Link {
    int id;
    int weight;
    Node from;
    Node to;

    @Override
    public String toString() {
        return "Link{" +
                "id=" + id +
                ", weight=" + weight +
                ", from=" + from.id +
                ", to=" + to.id +
                '}';
    }
}
