import java.util.ArrayList;
import java.util.HashMap;


public class FourPath {
    // TODO add hasmap to check if it contains the node
    // index: 0   1   2   3
    //        +---+---+---+
    public ArrayList<Integer> ids;
    public HashMap<Integer, Node> node_list; // {index: node}

    public FourPath(Node node1, Node node2, Node node3, Node node4) {
        this.node_list = new HashMap<>();
        this.node_list.put(0, node1);
        this.node_list.put(1, node2);
        this.node_list.put(2, node3);
        this.node_list.put(3, node4);

        this.ids = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            this.ids.add(this.node_list.get(i).id);
        }
    }

}
