import java.util.ArrayList;

public class ExactCountNonInduced {

    public static long cycleSIXCount(Cycle cycle, BipartiteGraph graph){
        long count = 0;


        ArrayList<Node> left_nodes = new ArrayList<Node>();
        ArrayList<Node> right_nodes = new ArrayList<Node>();

        for(Node node: cycle.node_list.values()) {
            if (node.id < 0){
                left_nodes.add(node);
            }else {
                right_nodes.add(node);
            }
        }

        for(int id: left_nodes.get(0).adjacency_list.keySet()) {
            if(!cycle.node_list.containsKey(id)){
                for(int id2: right_nodes.get(0).adjacency_list.keySet()) {
                    if (!cycle.node_list.containsKey(id2) && graph.map.get(id).adjacency_list.containsKey(id2)) {
                        count += 1;
                    }
                }
                for(int id3: right_nodes.get(1).adjacency_list.keySet()) {
                    if (!cycle.node_list.containsKey(id3) && graph.map.get(id).adjacency_list.containsKey(id3)) {
                        count += 1;
                    }
                }
            }
        }

        for(int id: left_nodes.get(1).adjacency_list.keySet()) {
            if(!cycle.node_list.containsKey(id)){
                for(int id2: right_nodes.get(0).adjacency_list.keySet()) {
                    if (!cycle.node_list.containsKey(id2) && graph.map.get(id).adjacency_list.containsKey(id2)) {
                        count += 1;
                    }
                }
                for(int id3: right_nodes.get(1).adjacency_list.keySet()) {
                    if (!cycle.node_list.containsKey(id3) && graph.map.get(id).adjacency_list.containsKey(id3)) {
                        count += 1;
                    }
                }
            }
        }

        return count;
    }

    public static long cycleSEVENCount(Cycle cycle, BipartiteGraph graph) {
        long count = 0;

        ArrayList<Node> left_nodes = new ArrayList<Node>();
        ArrayList<Node> right_nodes = new ArrayList<Node>();

        for (Node node : cycle.node_list.values()) {
            if (node.id < 0) {
                left_nodes.add(node);
            } else {
                right_nodes.add(node);
            }
        }

        for (Node left_node : left_nodes) {
            for (Node right_node : right_nodes) {
                // If node doesn't have any other neighbours apart from the cycle, continue to the next loop
                if (left_node.degree <= 2 || right_node.degree <= 2) {
                    continue;
                }
                count += (long) (left_node.degree - 2) * (right_node.degree - 2);
            }
        }

        return count;
    }


    public static long cycleEIGHTCount(Cycle cycle, BipartiteGraph graph) {
        long count = 0;

        for (Node node : cycle.node_list.values()) {
            long neigbours = 0;
            for (int neighbour_id : node.adjacency_list.keySet()) {
                if (!cycle.node_list.containsKey(neighbour_id)) {
                    neigbours++;
                }
            }
            count += neigbours * (neigbours - 1) / 2;
        }

        return count;
    }


    // TODO: L - R bilgisini kullan
    public static long cycleNINECount(Cycle cycle, BipartiteGraph graph) {
        ArrayList<Integer> edge = new ArrayList<>();
        int node1 = 0, node2 = 0;
        for (int id : cycle.node_list.keySet()) {
            if (id > 0 && node1 == 0) {
                node1 = id;
            } else if (id < 0 && node2 == 0) {
                node2 = id;
            }
        }
        edge.add(node1);
        edge.add(node2);

        int first_end = edge.get(0);
        int second_end = edge.get(1);

        int opposite_first_end = 0;
        int opposite_second_end = 0;
        for (int node : cycle.node_list.keySet()) {
            if (cycle.node_list.get(first_end).adjacency_list.containsKey(node) &&
                    !cycle.node_list.get(second_end).adjacency_list.containsKey(node)) {
                opposite_second_end = node;
            }

            if (cycle.node_list.get(second_end).adjacency_list.containsKey(node) &&
                    !cycle.node_list.get(first_end).adjacency_list.containsKey(node)) {
                opposite_first_end = node;
            }
        }

        long first_end_count = 0, second_end_count = 0, first_opposite_count = 0, second_opposite_count = 0;

        for (int node : cycle.node_list.get(first_end).adjacency_list.values()) {
            if (!cycle.node_list.containsKey(node)) {
                first_end_count++;
            }
        }

        for (int node : cycle.node_list.get(second_end).adjacency_list.values()) {
            if (!cycle.node_list.containsKey(node)) {
                second_end_count++;
            }
        }

        for (int node : cycle.node_list.get(opposite_first_end).adjacency_list.values()) {
            if (!cycle.node_list.containsKey(node)) {
                first_opposite_count++;
            }
        }

        for (int node : cycle.node_list.get(opposite_second_end).adjacency_list.values()) {
            if (!cycle.node_list.containsKey(node)) {
                second_opposite_count++;
            }
        }

        return first_end_count * first_opposite_count + second_end_count * second_opposite_count;
    }


    public static long cycleTENCount(Cycle cycle, BipartiteGraph graph) {
        long count = 0;

        for (Node node : cycle.node_list.values()) {
            for (int neighbour_id : node.adjacency_list.keySet()) {
                if (!cycle.node_list.containsKey(neighbour_id)) {
                    count += graph.map.get(neighbour_id).degree - 1;
                }

            }
        }

        return count;
    }

}






