import java.util.ArrayList;
import java.util.HashMap;

public class Cycle {
    public HashMap<Integer, Node> map; // {node_id: node}
    public Node[] left_nodes;
    public Node[] right_nodes;

    public Cycle(){
        this.map = new HashMap<>();
        this.left_nodes = new Node[2];
        this.right_nodes = new Node[2];
    }
}
