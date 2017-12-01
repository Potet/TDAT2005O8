/**
 * Created by simen on 28.11.2017.
 */
public class Edge {
    Node toNode;
    Edge nextEdge;

    public Edge(Node toNode, Edge nextEdge) {
        this.toNode = toNode;
        this.nextEdge = nextEdge;
    }
}
