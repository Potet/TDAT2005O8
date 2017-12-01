import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by simen on 28.11.2017.
 */
public class Graph {
    int N,K;
    Node[] nodes;

    public void readTable(BufferedReader bufferedReader)throws IOException{
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(stringTokenizer.nextToken());
        nodes = new Node[N];
        K = Integer.parseInt(stringTokenizer.nextToken());
        for (int i = N-1; i >= 0 ; i--) {
            nodes[i] = new Node(i);
        }
        for (int i = 0; i < K; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int from = Integer.parseInt(stringTokenizer.nextToken());
            int to = Integer.parseInt(stringTokenizer.nextToken());
            int weight = Integer.parseInt(stringTokenizer.nextToken());
            WEdge wEdge = new WEdge(nodes[to], (WEdge)nodes[from].firstEdge, weight);
            nodes[from].firstEdge = wEdge;
        }
    }

    public void initPrev(Node startNode) {
        for (int i = 0; i < N; i++) {
            nodes[i].data = new Previous();
        }
        ((Previous) startNode.data).distance = 0;
        ((Previous)startNode.data).prevNode = startNode;
    }

    public void dijkstra(Node startNode){
        initPrev(startNode);
        PriorityQueue<Node> queue = new PriorityQueue<>(N, new Comparator<Node>(){
            @Override
            public int compare(Node o1, Node o2){
                return (((Previous)o1.data).distance - ((Previous)o2.data).distance);
            }
        });
        queue.addAll(Arrays.asList(nodes));
        for(int i = N; i>1; i--){
            Node node = queue.poll();
            for(WEdge wEdge = (WEdge)node.firstEdge; wEdge!=null; wEdge = (WEdge)wEdge.nextEdge){
                Previous thisPreviousNode = (Previous)node.data;
                Previous previousForEgdeTo = (Previous)wEdge.toNode.data;
                if(thisPreviousNode.distance + wEdge.weight < previousForEgdeTo.distance){
                    previousForEgdeTo.prevNode = node;
                    previousForEgdeTo.distance = thisPreviousNode.distance + wEdge.weight;
                    queue.remove(wEdge.toNode);
                    queue.add(wEdge.toNode);
                }
            }
            //TODO: Vil denne for-løkka loope igjennom alle noder i nodes? Ser ut som om den går igjennom det første elementet i queue N ganger. Burde kanskje queue.add(wEdge.toNode); ikke være med?
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph();
        try{
            FileReader fr = new FileReader("vg2.txt");
            BufferedReader br = new BufferedReader(fr);
            graph.readTable(br);

            Node startNode = graph.nodes[0];
            graph.initPrev(startNode);
            graph.dijkstra(startNode);
            ArrayList<Node> toBeSorted = new ArrayList<>();
            toBeSorted.addAll(Arrays.asList(graph.nodes));
            Collections.sort(toBeSorted, new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                    return (((Previous)o1.data).distance - ((Previous)o2.data).distance);
                }
            });
            for (Node node:toBeSorted) {
                System.out.println(node.toString(startNode));
            }
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
}
