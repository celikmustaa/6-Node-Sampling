import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

// TODO: use long instead of int?
public class BipartiteGraph {
    public HashMap<Integer, Integer> L;             // node_id: node_id
    public HashMap<Integer, Integer> R;             // node_id: node_id
    public HashMap<Integer, Node> map;              // all nodes
    public ArrayList<ArrayList<Integer>> edge_list; // [[n1, -n2],[n3, -n4] ...]
    // TODO: write wedge_map in a file instead of keeping it in in-memory?
    public HashMap<String, ArrayList<Integer>> wedge_map_left;      // ["n1-n2(key-left)": [-n3, -n1 -n5], "n4-n5(key)": [-n6, -n7 -n5]]
//    public HashMap<String, ArrayList<Integer>> wedge_map_right;      // ["n1$n2(key$right)": [-n3, -n1 -n5], "n4$n5(key)": [-n6, -n7 -n5]]
    public static ArrayList<KeyCount> key_count = new ArrayList<>();
    public static class KeyCount {
        String key;
        int cycle_count;

        public KeyCount(String key, int cycle_count){
            this.key = key;
            this.cycle_count = cycle_count;
        }
    }

    public BipartiteGraph() {
        this.L = new HashMap<>();
        this.R = new HashMap<>();
        this.map = new HashMap<>();
        this.edge_list = new ArrayList<>();
        this.wedge_map_left = new HashMap<>();
        // this.wedge_map_right = new HashMap<>();
        //this.key_count = new ArrayList<>();
    }

    public void addNode(int id){
        if(!this.map.containsKey(id)){
            this.map.put(id, new Node(id));
            if (id < 0) {
                this.R.put(id, id);
            }
            else {
                this.L.put(id, id);
            }
        }
    }




    public void addEdge(int left_id, int right_id){
        try {
            Database.insertEdge.setInt(1, left_id);
            Database.insertEdge.setInt(2, right_id);
            Database.insertEdge.addBatch();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addNodes(int left_id, int right_id){
        try {
            Database.insertNode.setInt(1, left_id);
            Database.insertNode.setInt(2, left_id);
            Database.insertNode.setInt(3, left_id);
            Database.insertNode.addBatch();

            Database.insertNode.setInt(1, right_id);
            Database.insertNode.setInt(2, right_id);
            Database.insertNode.setInt(3, right_id);
            Database.insertNode.addBatch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Integer> getAdjacencyList(int node_id) {
        ArrayList<Integer> adjacency_list = new ArrayList<>();

        try {
            Database.getAdjacencyList.setInt(1, node_id);
            Database.getAdjacencyList.setInt(2, node_id);
            Database.getAdjacencyList.setInt(3, node_id);

            ResultSet rs = Database.getAdjacencyList.executeQuery();

            // TODO it traverses!!!! it is no efficient
            while (rs.next()) {
                int id = rs.getInt("id");
                adjacency_list.add(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return adjacency_list;
    }

    public static void fillWedgeMapTable(){
        int maxId = 0;

        try {
            ResultSet rs = Database.getMaxNodeId.executeQuery();

            while (rs.next()) {
                maxId = rs.getInt("max");
            }

            System.out.println("maxId is: " + maxId);

            int head = 0;
            int limit = 100; // TODO 1000

            while(limit < maxId + 101){ // TODO 1001
                while(head < limit && head <= maxId){
                    for(int middle: getAdjacencyList(head)){
                        for(int tail: getAdjacencyList(middle)){
                            if(tail > head){
                                String key = head + "$" + tail;
                                Database.insertWedge.setString(1, key);
                                Database.insertWedge.setInt(2, middle);
                                Database.insertWedge.setInt(3, middle);
                                Database.insertWedge.setString(4, key);
                                Database.insertWedge.addBatch();

                            }
                        }
                    }
                    head += 1;
                    System.out.println("Head is: "+ head);
                }
                Database.insertWedge.executeBatch();
                System.out.println("Batch is executed");
                limit += 100;  // TODO 1000
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void fillKeyCountFromSQL() {
        try {
            ResultSet rs = Database.fillKeyCount.executeQuery();

            int last_key_count = 0;
            while (rs.next()) {
                String key = rs.getString("key");
                int count = rs.getInt("count");
                key_count.add(new KeyCount(key, count + last_key_count));
                last_key_count += count;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Integer[] getWedgeList(String key) {
        Integer[] wedge_list = null;

        try {
            Database.queryWedge.setString(1, key);

            ResultSet rs = Database.queryWedge.executeQuery();

            while (rs.next()) {
                wedge_list = (Integer[])rs.getArray("middles").getArray();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wedge_list;
    }


    public static Cycle getRandomCycleFromSQL(){
        int cycle_count = key_count.get(key_count.size() - 1).cycle_count;

        int random_number = ThreadLocalRandom.current().nextInt(1, cycle_count + 1);

        String key = key_count.get(binarySearch(random_number)).key;

        int first_random_index = ThreadLocalRandom.current().nextInt(0, getWedgeList(key).length);
        int second_random_index = ThreadLocalRandom.current().nextInt(0, getWedgeList(key).length-1);
        if(second_random_index >= first_random_index) {
            second_random_index++;
        }


        Cycle cycle = new Cycle();
        String[] splitted = key.split("\\$");

        ArrayList<Integer> node_ids = new ArrayList<>();
        node_ids.add(Integer.parseInt(splitted[0]));
        node_ids.add(Integer.parseInt(splitted[1]));
        node_ids.add(getWedgeList(key)[(first_random_index)]);
        node_ids.add(getWedgeList(key)[(second_random_index)]);


        for(int id: node_ids){
            Node node = new Node(id);
            ArrayList<Integer> adjacency_list = getAdjacencyList(id);
            HashMap<Integer, Integer> adjacency_map = new HashMap<>();

            for(int neighbour: adjacency_list){
                adjacency_map.put(neighbour, 1);
            }

            node.degree = adjacency_list.size();
            node.adjacency_list = adjacency_map;

            cycle.node_list.put(id, node);
        }

        return cycle;

    }

    public static int getDegree(int node_id) {
        int degree = 0;

        try {
            Database.getDegreeOfNode.setInt(1, node_id);

            ResultSet rs = Database.getDegreeOfNode.executeQuery();

            while (rs.next()) {
                degree = rs.getInt("degree");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return degree;
    }

    public void connect(int left_id, int right_id){
        this.addNode(left_id); this.addNode(right_id);
        Node left = this.map.get(left_id);
        Node right = this.map.get(right_id);

        if(!(right.adjacency_list.containsKey(left.id))){ // no multiple edges
            left.adjacency_list.put(right_id, 1);
            right.adjacency_list.put(left_id, 1);

            left.degree++; right.degree++;

            ArrayList<Integer> edge = new ArrayList<>();
            edge.add(left_id); edge.add(right_id);
            this.edge_list.add(edge);
        }
    }


    // TODO
    public void fillWedgeMap(){
        // where the middle is right
        for(int head: L.keySet()){
            for(int middle: map.get(head).adjacency_list.keySet()){
                for(int tail: map.get(middle).adjacency_list.keySet()){
                    if(tail > head){
                        String key = head + "-" + tail;
                        if(!wedge_map_left.containsKey(key)){
                            wedge_map_left.put(key, new ArrayList<>());
                        }
                        wedge_map_left.get(key).add(middle);
                    }
                }
            }
        }

//        // where the middle is left
//        for(int head: R.keySet()){
//            for(int middle: map.get(head).adjacency_list.keySet()){
//                for(int tail: map.get(middle).adjacency_list.keySet()){
//                    if(tail > head){
//                        String key = head + "$" + tail;
//                        if(!wedge_map_right.containsKey(key)){
//                            wedge_map_right.put(key, new ArrayList<>());
//                        }
//                        wedge_map_right.get(key).add(middle);
//                    }
//                }
//            }
//        }

        fillKeyCount();
    }

    public void fillKeyCount(){
        int counter = 0;
        for(String key: wedge_map_left.keySet()){
            if(wedge_map_left.get(key).size() > 1) {
                int count = wedge_map_left.get(key).size() * (wedge_map_left.get(key).size() - 1) / 2;
                counter += count;
                key_count.add(new KeyCount(key, counter));
            }
        }
    }

    public Cycle getRandomCycle(){
        int cycle_count = key_count.get(key_count.size() - 1).cycle_count;

        int random_number = ThreadLocalRandom.current().nextInt(1, cycle_count + 1);

        String key = key_count.get(binarySearch(random_number)).key;

        int first_random_index = ThreadLocalRandom.current().nextInt(0, wedge_map_left.get(key).size());
        int second_random_index = ThreadLocalRandom.current().nextInt(0, wedge_map_left.get(key).size()-1);
        if(second_random_index >= first_random_index) {
            second_random_index++;
        }


        Cycle cycle = new Cycle();
        String[] splitted = key.split("-");

        ArrayList<Integer> node_ids = new ArrayList<>();
        node_ids.add(Integer.parseInt(splitted[0]));
        node_ids.add(Integer.parseInt(splitted[1]));
        node_ids.add(wedge_map_left.get(key).get(first_random_index));
        node_ids.add(wedge_map_left.get(key).get(second_random_index));

        for(int id: node_ids){
            cycle.node_list.put(id, map.get(id));
        }

        return cycle;

    }

    // returns an index so that randomNumber is either smaller than or equal to the number in
    // the cumulative array at that index but also randomNumber is greater than the number before that index
    public static int binarySearch(int randomNumber){
        int left = 0; int right = key_count.size(); // left inclusive, right exclusive
        while (true){
            if (randomNumber <= key_count.get(left).cycle_count){
                return left;
            }

            int middle = left + (right-left)/2;

            if (randomNumber <= key_count.get(middle).cycle_count && randomNumber > key_count.get(middle-1).cycle_count){
                return middle;
            }

            if (randomNumber <= key_count.get(middle).cycle_count){
                right = middle;
            }
            else{
                left = middle + 1;
            }
        }
    }

    public FourPath getRandomFourPath() {
        FourPath fourPath = new FourPath();
        int random_number = ThreadLocalRandom.current().nextInt(0, edge_list.size());
        ArrayList<Integer> random_edge = edge_list.get(random_number);

        HashMap<Integer, Integer> node0_adj_map = map.get(random_edge.get(0)).adjacency_list;
        HashMap<Integer, Integer> node1_adj_map = map.get(random_edge.get(1)).adjacency_list;

        node0_adj_map.remove(random_edge.get(1));
        node1_adj_map.remove((random_edge.get(0)));

        ArrayList<Integer> node0_adj = new ArrayList<Integer>(node0_adj_map.keySet());
        ArrayList<Integer> node1_adj = new ArrayList<Integer>(node1_adj_map.keySet());

        if ((node0_adj.size() > 0) && (node1_adj.size() > 0)){
            int random_node_index0 = ThreadLocalRandom.current().nextInt(0, node0_adj.size());
            int random_node_index1 = ThreadLocalRandom.current().nextInt(0, node1_adj.size());
            fourPath.node_list.put(0, map.get(node0_adj.get(random_node_index0)));
            fourPath.node_list.put(1, map.get(random_edge.get(0)));
            fourPath.node_list.put(2, map.get(random_edge.get(1)));
            fourPath.node_list.put(3, map.get(node1_adj.get(random_node_index1)));

            ArrayList<Integer> ids = new ArrayList<>();
            for (int i=0; i < 4 ;i++){
                ids.add(fourPath.node_list.get(i).id);
            }
            fourPath.ids = ids;
        }
        else {
//            System.out.println("Fourpath doesn't exist for the randomly chosen edge, choosing new edge");
            fourPath = getRandomFourPath();
        }


        return fourPath;
    }

    public int getFourPathCount(){
        int four_path_count = 0;

        for (ArrayList<Integer> edge : edge_list){
            four_path_count += (map.get(edge.get(0)).degree - 1) * (map.get(edge.get(1)).degree - 1);
        }

        return four_path_count;
    }

}
