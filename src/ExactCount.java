import java.util.ArrayList;
import java.util.Collections;

public class ExactCount {

    // TODO check the results of exactCount algorithms
    // 6-Vertex Counts

//    *---*---*
//    |   |
//    *---*---*
//    |   |
//    *---*---*
    public static long cycleSEVENCount(Cycle cycle, BipartiteGraph graph){
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

        System.out.println(left_nodes.get(0).id +  " " + left_nodes.get(1).id);
        System.out.println(right_nodes.get(0).id +  " " + right_nodes.get(1).id);

        for (Node left_node: left_nodes){
            for (Node right_node: right_nodes){
                System.out.printf("\tChecking pair %d, %d\n",left_node.id, right_node.id);

                // If node doesn't have any other neighbours apart from the cycle, continue to the next loop
                if (left_node.degree <= 2 || right_node.degree <= 2){
                    continue;
                }
                System.out.printf("\t%d , %d\n",left_node.degree - 2, right_node.degree - 2);
                count += (long) (left_node.degree - 2) * (right_node.degree-2);
            }
        }

        return count;
    }

    public static long cycleEIGHTCount(Cycle cycle, BipartiteGraph graph){
        long count = 0;

        for(Node node: cycle.node_list.values()) {
            long neigbours = 0;
            for(int neighbour_id: node.adjacency_list.keySet()) {
                if (!cycle.node_list.containsKey(neighbour_id)){
                    neigbours ++;
                }
            }
            count += neigbours * (neigbours - 1) / 2;
        }

        return count;
    }

    // TODO: L - R bilgisini kullan
    public static long cycleNINECount(Cycle cycle, BipartiteGraph graph){
        ArrayList<Integer> edge = new ArrayList<>();
        int node1 = 0, node2 = 0;
        for(int id: cycle.node_list.keySet()){
            if (id > 0 && node1 == 0){
                node1 = id;
            }else if (id < 0 && node2 == 0){
                node2 = id;
            }
        }
        edge.add(node1); edge.add(node2);

        int first_end = edge.get(0);
        int second_end = edge.get(1);

        int opposite_first_end = 0;
        int opposite_second_end = 0;
        for(int node: cycle.node_list.keySet()){
            if(cycle.node_list.get(first_end).adjacency_list.containsKey(node) &&
                    !cycle.node_list.get(second_end).adjacency_list.containsKey(node)){
                opposite_second_end = node;
            }

            if(cycle.node_list.get(second_end).adjacency_list.containsKey(node) &&
                    !cycle.node_list.get(first_end).adjacency_list.containsKey(node)){
                opposite_first_end = node;
            }
        }

        long first_end_count = 0, second_end_count = 0, first_opposite_count = 0, second_opposite_count = 0;

        for(int node: cycle.node_list.get(first_end).adjacency_list.values()){
            if(!cycle.node_list.containsKey(node)){
                first_end_count++;
            }
        }

        for(int node: cycle.node_list.get(second_end).adjacency_list.values()){
            if(!cycle.node_list.containsKey(node)){
                second_end_count++;
            }
        }

        for(int node: cycle.node_list.get(opposite_first_end).adjacency_list.values()){
            if(!cycle.node_list.containsKey(node)){
                first_opposite_count++;
            }
        }

        for(int node: cycle.node_list.get(opposite_second_end).adjacency_list.values()){
            if(!cycle.node_list.containsKey(node)){
                second_opposite_count++;
            }
        }

        return first_end_count * first_opposite_count + second_end_count * second_opposite_count;
    }

    public static long cycleTENCount(Cycle cycle, BipartiteGraph graph){
        long count = 0;

        for(Node node: cycle.node_list.values()) {
            for(int neighbour_id: node.adjacency_list.keySet()) {
                if (!cycle.node_list.containsKey(neighbour_id)){
                    count += graph.map.get(neighbour_id).degree - 1;
                }

            }
        }

        return count;
    }

    public static long cycleFOURCount(Cycle cycle, BipartiteGraph graph){
        long count = 0;

        ArrayList<Node> left = new ArrayList<>();
        ArrayList<Node> right = new ArrayList<>();

        try {
            for(Node node: cycle.node_list.values()) {
                if (graph.L.containsKey(node.id)) {
                    left.add(node);
                }
                else if (graph.R.containsKey(node.id)) {
                    right.add(node);
                }
                else {
                    throw new Exception("Node id undefined");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (left.get(0).adjacency_list.containsKey(left.get(1).id)){
            count += left.get(0).degree - 3;
            count += left.get(1).degree - 3;
        }

        if (right.get(0).adjacency_list.containsKey(right.get(1).id)){
            count += right.get(0).degree - 3;
            count += right.get(1).degree - 3;
        }
        return count;
    }

    public static long cycleFIVECount(Cycle cycle, BipartiteGraph graph){
        long count = 0;

        ArrayList<Node> left = new ArrayList<>();
        ArrayList<Node> right = new ArrayList<>();

        try {
            for(Node node: cycle.node_list.values()) {
                if (graph.L.containsKey(node.id)) {
                    left.add(node);
                }
                else if (graph.R.containsKey(node.id)) {
                    right.add(node);
                }
                else {
                    throw new Exception("Node id undefined");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (left.get(0).adjacency_list.containsKey(left.get(1).id)){
            count += right.get(0).degree - 2;
            count += right.get(1).degree - 2;
        }

        if (right.get(0).adjacency_list.containsKey(right.get(1).id)){
            count += left.get(0).degree - 2;
            count += left.get(1).degree - 2;
        }
        return count;
    }

}
