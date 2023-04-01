import java.util.ArrayList;
import java.util.HashMap;


public class FourPath {
    // TODO add hasmap to check if it contains the node
    // index: 0   1   2   3
    //        +---+---+---+
    public ArrayList<Integer> ids;
    public HashMap<Integer, Node> node_list; // {index: node}
    public FourPath(){
            this.node_list = new HashMap<>();
        }

}
