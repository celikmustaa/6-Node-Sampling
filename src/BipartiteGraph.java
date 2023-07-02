import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class BipartiteGraph {

    // Left nodes have positive id, Right nodes have negative id
    public HashMap<Integer, Node> map;              // all nodes
    public ArrayList<ArrayList<Integer>> edge_list; // [[n1, -n2],[n3, -n4] ...]
    public ArrayList<EdgeFourPathCount> edge_four_path_counts = new ArrayList<>();

    public static class EdgeFourPathCount {
        String edge;
        long four_path_count;

        public EdgeFourPathCount(String edge, long four_path_count) {
            this.edge = edge;
            this.four_path_count = four_path_count;
        }
    }


    public BipartiteGraph() {
        this.map = new HashMap<>();
        this.edge_list = new ArrayList<>();
        this.edge_four_path_counts = new ArrayList<>();
    }

    public void addNode(int id) {
        if (!this.map.containsKey(id)) {
            this.map.put(id, new Node(id));
        }
    }


    public void connect(int left_id, int right_id) {
        this.addNode(left_id);
        this.addNode(right_id);
        Node left = this.map.get(left_id);
        Node right = this.map.get(right_id);

        if (!(right.adjacency_list.containsKey(left.id))) { // no multiple edges
            left.adjacency_list.put(right_id, 1);
            right.adjacency_list.put(left_id, 1);

            left.degree++;
            right.degree++;

            ArrayList<Integer> edge = new ArrayList<>();
            edge.add(left_id);
            edge.add(right_id);
            this.edge_list.add(edge);
        }
    }


    public void fillEdgeFourPathCounts(BipartiteGraph graph) {
        long counter = 0;
        for (ArrayList<Integer> edge : edge_list) {
            long fourPathCount = ((long) graph.map.get(edge.get(0)).degree - 1) * (graph.map.get(edge.get(1)).degree - 1);
            if (fourPathCount > 0) {
                counter += fourPathCount;
                edge_four_path_counts.add(new EdgeFourPathCount((edge.get(0) + "$" + edge.get(1)), counter));
            }
        }
    }



    // returns an index so that randomNumber is either smaller than or equal to the number in
    // the cumulative array at that index but also randomNumber is greater than the number before that index
    public static int binarySearch(long randomNumber, ArrayList<EdgeFourPathCount> edge_four_path_counts) {
        int left = 0;
        int right = edge_four_path_counts.size(); // left inclusive, right exclusive
        while (true) {
            if (randomNumber <= edge_four_path_counts.get(left).four_path_count) {
                return left;
            }

            int middle = left + (right - left) / 2;

            if (randomNumber <= edge_four_path_counts.get(middle).four_path_count && randomNumber > edge_four_path_counts.get(middle - 1).four_path_count) {
                return middle;
            }

            if (randomNumber <= edge_four_path_counts.get(middle).four_path_count) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
    }

    public FourPath getRandomFourPath(BipartiteGraph graph) {
        long four_path_count = edge_four_path_counts.get(edge_four_path_counts.size() - 1).four_path_count;

        long random_number = ThreadLocalRandom.current().nextLong(1, four_path_count + 1);

        String key = edge_four_path_counts.get(binarySearch(random_number, graph.edge_four_path_counts)).edge;


        String[] splitted = key.split("\\$");


        Node node2 = graph.map.get(Integer.parseInt(splitted[0]));
        Node node3 = graph.map.get(Integer.parseInt(splitted[1]));

        Object[] node2_adjacency_list = node2.adjacency_list.keySet().toArray();
        Object[] node3_adjacency_list =  node3.adjacency_list.keySet().toArray();


        int first_random_index = ThreadLocalRandom.current().nextInt(0, node2.degree);
        int node_id1 = (int) node2_adjacency_list[first_random_index];
        // if random node choose by the adjacency list of splitted[0] is actually splitted[1], get another random node
        while(node_id1 == node3.id) {
            first_random_index = ThreadLocalRandom.current().nextInt(0, node2.degree);
            node_id1 = (int) node2_adjacency_list[first_random_index];
        }
        Node node1 = graph.map.get(node_id1);



        int second_random_index = ThreadLocalRandom.current().nextInt(0, node3.degree);
        int node_id4 = (int) node3_adjacency_list[second_random_index];
        while(node_id4 == node2.id) {
            second_random_index = ThreadLocalRandom.current().nextInt(0, node3.degree);
            node_id4 = (int) node3_adjacency_list[second_random_index];
        }
        Node node4 = graph.map.get(node_id4);


        // each "-" represents an edge: node1-node2-node3-node4 (means that this function returns nodes in order)
        return new FourPath(node1, node2, node3, node4);
    }


    public Cycle getRandomCycle(BipartiteGraph graph) {

        FourPath fourPath = getRandomFourPath(graph);

        // If four-path is not a cycle
        while (!fourPath.node_list.get(0).adjacency_list.containsKey(fourPath.node_list.get(3).id)) {
            fourPath = getRandomFourPath(graph);
        }

        Cycle cycle = new Cycle();

        int left_counter = 0, right_counter = 0;
        for (int i = 0; i < 4; i++) {
            cycle.map.put(fourPath.node_list.get(i).id, fourPath.node_list.get(i));
            if (fourPath.node_list.get(i).id > 0) {
                cycle.left_nodes[left_counter++] = fourPath.node_list.get(i);
            } else {
                cycle.right_nodes[right_counter++] = fourPath.node_list.get(i);
            }
        }

        return cycle;

    }

}
