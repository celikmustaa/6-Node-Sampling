import java.util.ArrayList;
import java.util.HashMap;

public class ExactCountNonInduced {

    public static long cycleFIVECount(Cycle cycle, BipartiteGraph graph){
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

        ArrayList<Integer> left_0_adj = new ArrayList<>(left_nodes.get(0).adjacency_list.keySet());
        ArrayList<Integer> left_1_adj = new ArrayList<>(left_nodes.get(0).adjacency_list.keySet());
        ArrayList<Integer> right_0_adj = new ArrayList<>(right_nodes.get(0).adjacency_list.keySet());
        ArrayList<Integer> right_1_adj = new ArrayList<>(right_nodes.get(0).adjacency_list.keySet());

        left_0_adj.retainAll(left_1_adj);
        right_0_adj.retainAll(right_1_adj);

        ArrayList<Integer> left_intersection = left_0_adj;
        ArrayList<Integer> right_intersection = right_0_adj;

        left_intersection.remove(Integer.valueOf(right_nodes.get(0).id));
        left_intersection.remove(Integer.valueOf(right_nodes.get(1).id));
        right_intersection.remove(Integer.valueOf(left_nodes.get(0).id));
        right_intersection.remove(Integer.valueOf(left_nodes.get(1).id));


        // If left cycle is chosen
        for (int id: left_intersection){
            count += graph.map.get(id).degree-2;
        }
        for (int id: right_intersection){
            count += graph.map.get(id).degree-2;
        }

        // If right cycle is chosen
        count += (long) (right_nodes.get(0).degree - 2 + right_nodes.get(1).degree - 2) * left_intersection.size();

        count += (long) (left_nodes.get(0).degree - 2 + left_nodes.get(1).degree - 2) * right_intersection.size();

        // If outer cycle is chosen (same as right cycle)
        count += (long) (right_nodes.get(0).degree - 2 + right_nodes.get(1).degree - 2) * left_intersection.size();

        count += (long) (left_nodes.get(0).degree - 2 + left_nodes.get(1).degree - 2) * right_intersection.size();

        return count;
    }

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

        ArrayList<Integer> left_0_adj = new ArrayList<>(left_nodes.get(0).adjacency_list.keySet());
        ArrayList<Integer> left_1_adj = new ArrayList<>(left_nodes.get(0).adjacency_list.keySet());
        ArrayList<Integer> right_0_adj = new ArrayList<>(right_nodes.get(0).adjacency_list.keySet());
        ArrayList<Integer> right_1_adj = new ArrayList<>(right_nodes.get(0).adjacency_list.keySet());

        for(int id: left_0_adj) {
            if(!cycle.node_list.containsKey(id)){
                HashMap<Integer, Integer> id_adj = graph.map.get(id).adjacency_list;
                for(int id2: right_0_adj) {
                    if (!cycle.node_list.containsKey(id2) && id_adj.containsKey(id2)) {
                        count += 1;
                    }
                }
                for(int id3: right_1_adj) {
                    if (!cycle.node_list.containsKey(id3) && id_adj.containsKey(id3)) {
                        count += 1;
                    }
                }
            }
        }

        for(int id: left_1_adj) {
            if(!cycle.node_list.containsKey(id)){
                HashMap<Integer, Integer> id_adj = graph.map.get(id).adjacency_list;
                for(int id2: right_0_adj) {
                    if (!cycle.node_list.containsKey(id2) && id_adj.containsKey(id2)) {
                        count += 1;
                    }
                }
                for(int id3: right_1_adj) {
                    if (!cycle.node_list.containsKey(id3) && id_adj.containsKey(id3)) {
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

    public static long cycleTENCountSQL(Cycle cycle) {
        long count = 0;

        for (Node node : cycle.node_list.values()) {
            for (int neighbour_id : node.adjacency_list.keySet()) {
                if (!cycle.node_list.containsKey(neighbour_id)) {
                    count += BipartiteGraph.getDegree(neighbour_id) - 1;
                }

            }
        }

        return count;
    }

}






