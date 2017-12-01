/**
 * Created by simen on 28.11.2017.
 */
public class WEdge extends Edge {
    int weight;
    public WEdge(Node n, Edge e, int weight){
        super(n, e);
        this.weight = weight;
    }
}
