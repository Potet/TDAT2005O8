/**
 * Created by simen on 28.11.2017.
 */
public class Previous {
    int distance;
    static final int INF = 1000000000;
    Node prevNode;

    public Previous(){
        this.distance = INF;
    }

    public String toString(Node s) {
        if (prevNode != null) {
            return prevNode.num + "     " + distance;
        }
        else return "Ikke nådd";
    }
}
