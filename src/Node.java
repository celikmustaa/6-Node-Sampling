import java.util.HashMap;

public class Node {
    public int id;
    public int degree;
    public HashMap<Integer, Integer> adjacency_list; // {node_id: 1}

    public Node(int id) {
        this.id = id;
        this.degree = 0;
        this.adjacency_list = new HashMap<>();
    }
}
