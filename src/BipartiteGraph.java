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
    public HashMap<String, ArrayList<Integer>> wedge_map_right;      // ["n1$n2(key$right)": [-n3, -n1 -n5], "n4-n5(key)": [-n6, -n7 -n5]]
    public ArrayList<KeyCount> key_count;

    public class KeyCount {
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
        this.wedge_map_right = new HashMap<>();
        this.key_count = new ArrayList<>();
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

        // where the middle is left
        for(int head: R.keySet()){
            for(int middle: map.get(head).adjacency_list.keySet()){
                for(int tail: map.get(middle).adjacency_list.keySet()){
                    if(tail > head){
                        String key = head + "$" + tail;
                        if(!wedge_map_right.containsKey(key)){
                            wedge_map_right.put(key, new ArrayList<>());
                        }
                        wedge_map_right.get(key).add(middle);
                    }
                }
            }
        }

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
    public int binarySearch(int randomNumber){
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

}
