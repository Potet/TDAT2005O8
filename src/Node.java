/**
 * Created by simen on 28.11.2017.
 */
public class Node {
    int num;
    Edge firstEdge;
    Object data;

    public Node(int num){
        this.num = num;
    }

    public String toString(Node s){
        if(this.equals(s)) return num + "    start  0";
        if(data!=null){
            return num + "    " + ((Previous)data).toString(s);
        }else return "";
    }
}