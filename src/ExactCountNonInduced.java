import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class ExactCountNonInduced {
    public static long cycleONECount(Cycle cycle, BipartiteGraph graph){
        long count = 0;

        for(int id: cycle.left_nodes[0].adjacency_list.keySet()) {
            if(!cycle.map.containsKey(id)){
                HashMap<Integer, Integer> id_adj = graph.map.get(id).adjacency_list;
                for(int id2: cycle.right_nodes[0].adjacency_list.keySet()) {
                    if (!cycle.map.containsKey(id2) && id_adj.containsKey(id2)) {
                        if (cycle.left_nodes[1].adjacency_list.containsKey(id) && cycle.right_nodes[1].adjacency_list.containsKey(id2)) {
                            count += 1;
                        }

                    }
                }
            }
        }

        return count;
    }

    public static long cycleTWOCount(Cycle cycle, BipartiteGraph graph){
        long count = 0;

        ArrayList<Integer> left_intersection = getIntersection(cycle.left_nodes);
        ArrayList<Integer> right_intersection = getIntersection(cycle.right_nodes);

        // 6 possible cycle choice
        count += (long) (left_intersection.size() - 2) * (left_intersection.size() - 3) / 2;
        count += (long) (right_intersection.size() - 2) * (right_intersection.size() - 3) / 2;

        return count;
    }

    public static long cycleTHREECount(Cycle cycle, BipartiteGraph graph){
        long count = 0;

        for(int id: cycle.left_nodes[0].adjacency_list.keySet()) {
            if(!cycle.map.containsKey(id)){
                HashMap<Integer, Integer> id_adj = graph.map.get(id).adjacency_list;
                for(int id2: cycle.right_nodes[0].adjacency_list.keySet()) {
                    if (!cycle.map.containsKey(id2) && id_adj.containsKey(id2)) {
                        if (cycle.left_nodes[1].adjacency_list.containsKey(id) || cycle.right_nodes[1].adjacency_list.containsKey(id2)) {
                            count += 1;
                        }

                    }
                }
            }
        }

        for(int id: cycle.left_nodes[1].adjacency_list.keySet()) {
            if(!cycle.map.containsKey(id)){
                HashMap<Integer, Integer> id_adj = graph.map.get(id).adjacency_list;
                for(int id3: cycle.right_nodes[1].adjacency_list.keySet()) {
                    if (!cycle.map.containsKey(id3) && id_adj.containsKey(id3)) {
                        if (cycle.left_nodes[0].adjacency_list.containsKey(id) || cycle.right_nodes[0].adjacency_list.containsKey(id3)) {
                            count += 1;
                        }
                    }
                }
            }
        }

        return count;
    }

    public static long cycleFOURCount(Cycle cycle, BipartiteGraph graph){
        long count = 0;

        ArrayList<Integer> left_intersection = getIntersection(cycle.left_nodes);
        ArrayList<Integer> right_intersection = getIntersection(cycle.right_nodes);

        count += (long) (cycle.left_nodes[0].degree - 2 + cycle.left_nodes[1].degree - 2) * (left_intersection.size() - 2);
        count += (long) (cycle.right_nodes[0].degree - 2 + cycle.right_nodes[1].degree - 2) * (right_intersection.size() - 2);

        return count;
    }

    public static long cycleFIVECount(Cycle cycle, BipartiteGraph graph){
        long count = 0;

        ArrayList<Integer> left_intersection = getIntersection(cycle.left_nodes);
        ArrayList<Integer> right_intersection = getIntersection(cycle.right_nodes);


        // If left cycle is chosen
        for (int id: left_intersection){
            if (!cycle.map.containsKey(id)) {
                count += graph.map.get(id).degree-2;
            }
        }
        for (int id: right_intersection){
            if (!cycle.map.containsKey(id)) {
                count += graph.map.get(id).degree-2;
            }
        }

        // If right cycle is chosen
        count += (long) (cycle.right_nodes[0].degree - 2 + cycle.right_nodes[1].degree - 2) * (left_intersection.size() - 2);
        count += (long) (cycle.left_nodes[0].degree - 2 + cycle.left_nodes[1].degree - 2) * (right_intersection.size() - 2);

        // If outer cycle is chosen (same as right cycle)
        count += (long) (cycle.right_nodes[0].degree - 2 + cycle.right_nodes[1].degree - 2) * (left_intersection.size() - 2);
        count += (long) (cycle.left_nodes[0].degree - 2 + cycle.left_nodes[1].degree - 2) * (right_intersection.size() - 2);

        return count;
    }

    public static long cycleSIXCount(Cycle cycle, BipartiteGraph graph){
        long count = 0;

        for(int id: cycle.left_nodes[0].adjacency_list.keySet()) {
            if(!cycle.map.containsKey(id)){
                HashMap<Integer, Integer> id_adj = graph.map.get(id).adjacency_list;
                for(int id2: cycle.right_nodes[0].adjacency_list.keySet()) {
                    if (!cycle.map.containsKey(id2) && id_adj.containsKey(id2)) {
                        count += 1;
                    }
                }
                for(int id3: cycle.right_nodes[1].adjacency_list.keySet()) {
                    if (!cycle.map.containsKey(id3) && id_adj.containsKey(id3)) {
                        count += 1;
                    }
                }
            }
        }

        for(int id: cycle.left_nodes[1].adjacency_list.keySet()) {
            if(!cycle.map.containsKey(id)){
                HashMap<Integer, Integer> id_adj = graph.map.get(id).adjacency_list;
                for(int id2: cycle.right_nodes[0].adjacency_list.keySet()) {
                    if (!cycle.map.containsKey(id2) && id_adj.containsKey(id2)) {
                        count += 1;
                    }
                }
                for(int id3: cycle.right_nodes[1].adjacency_list.keySet()) {
                    if (!cycle.map.containsKey(id3) && id_adj.containsKey(id3)) {
                        count += 1;
                    }
                }
            }
        }

        return count;
    }

    public static long cycleSEVENCount(Cycle cycle, BipartiteGraph graph) {
        long count = 0;

        for (Node left_node : cycle.left_nodes) {
            for (Node right_node : cycle.right_nodes) {
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

        for (Node node : cycle.map.values()) {
            long neigbours = 0;
            for (int neighbour_id : node.adjacency_list.keySet()) {
                if (!cycle.map.containsKey(neighbour_id)) {
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
        for (int id : cycle.map.keySet()) {
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
        for (int node : cycle.map.keySet()) {
            if (cycle.map.get(first_end).adjacency_list.containsKey(node) &&
                    !cycle.map.get(second_end).adjacency_list.containsKey(node)) {
                opposite_second_end = node;
            }

            if (cycle.map.get(second_end).adjacency_list.containsKey(node) &&
                    !cycle.map.get(first_end).adjacency_list.containsKey(node)) {
                opposite_first_end = node;
            }
        }

        long first_end_count = 0, second_end_count = 0, first_opposite_count = 0, second_opposite_count = 0;

        for (int node : cycle.map.get(first_end).adjacency_list.values()) {
            if (!cycle.map.containsKey(node)) {
                first_end_count++;
            }
        }

        for (int node : cycle.map.get(second_end).adjacency_list.values()) {
            if (!cycle.map.containsKey(node)) {
                second_end_count++;
            }
        }

        if (opposite_first_end != 0) {
            for (int node : cycle.map.get(opposite_first_end).adjacency_list.values()) {
                if (!cycle.map.containsKey(node)) {
                    first_opposite_count++;
                }
            }
        }

        if (opposite_second_end != 0) {
            for (int node : cycle.map.get(opposite_second_end).adjacency_list.values()) {
                if (!cycle.map.containsKey(node)) {
                    second_opposite_count++;
                }
            }
        }

        return first_end_count * first_opposite_count + second_end_count * second_opposite_count;
    }


    public static long cycleTENCount(Cycle cycle, BipartiteGraph graph) {
        long count = 0;

        for (Node node : cycle.map.values()) {
            for (int neighbour_id : node.adjacency_list.keySet()) {
                if (!cycle.map.containsKey(neighbour_id)) {
                    count += graph.map.get(neighbour_id).degree - 1;
                }

            }
        }

        return count;
    }

    public static long cycleTENCountSQL(Cycle cycle) {
        long count = 0;

        for (Node node : cycle.map.values()) {
            for (int neighbour_id : node.adjacency_list.keySet()) {
                if (!cycle.map.containsKey(neighbour_id)) {
                    count += BipartiteGraph.getDegree(neighbour_id) - 1;
                }

            }
        }

        return count;
    }

    public static long fourPathELEVENCount(FourPath fourPath, BipartiteGraph graph){
        long count = 0;

        for (int id0: fourPath.node_list.get(0).adjacency_list.keySet()){
            if (fourPath.ids.contains(id0)) continue;
            Node currNode = graph.map.get(id0);
            for (int id3: fourPath.node_list.get(3).adjacency_list.keySet()){
                if (fourPath.ids.contains(id3)) continue;
                if (currNode.adjacency_list.containsKey(id3)){
                    count++;
                }
            }
        }


        return count;
    }

    //TODO needs wedge sampling
    public static long fourPathTWELVECount(FourPath fourPath, BipartiteGraph graph){
        long count = 0;

        return count;
    }

    public static long fourPathTHIRTEENCount(FourPath fourPath, BipartiteGraph graph){
        long count = 0;

        int node1_degree = (fourPath.node_list.get(1).degree - 2);
        int node2_degree = (fourPath.node_list.get(2).degree - 2);

        count += ((long) node1_degree * (node1_degree - 1)) / 2;
        count += ((long) node2_degree * (node2_degree - 1)) / 2;

        return count;
    }

    public static long fourPathFOURTEENCount(FourPath fourPath, BipartiteGraph graph){
        return (long) (fourPath.node_list.get(1).degree - 2) * (fourPath.node_list.get(2).degree - 2);
    }

    // TODO - Think over the algorithms, should we multiply by 2?
    public static long fourPathFIFTEENCount(FourPath fourPath, BipartiteGraph graph){
        long count = 0;

        for (int id :fourPath.node_list.get(1).adjacency_list.keySet()){
            if (fourPath.ids.contains(id)) continue;

            count += graph.map.get(id).degree - 1;
        }

        for (int id :fourPath.node_list.get(2).adjacency_list.keySet()){
            if (fourPath.ids.contains(id)) continue;

            count += graph.map.get(id).degree - 1;
        }

        count += (long) (fourPath.node_list.get(0).degree - 1) * (fourPath.node_list.get(1).degree - 2) *2;
        count += (long) (fourPath.node_list.get(3).degree - 1) * (fourPath.node_list.get(2).degree - 2) *2;

        return count;
    }

    public static long fourPathSIXTEENCount(FourPath fourPath, BipartiteGraph graph){
        long count = 0;

        int node0_degree = (fourPath.node_list.get(0).degree - 1);
        int node3_degree = (fourPath.node_list.get(3).degree - 1);

        count += (long) (fourPath.node_list.get(1).degree - 2) * (fourPath.node_list.get(3).degree - 1);
        count += (long) (fourPath.node_list.get(2).degree - 2) * (fourPath.node_list.get(0).degree - 1);
        count += ((long) node0_degree * (node0_degree-1))/2;
        count += ((long) node3_degree * (node3_degree-1))/2;

        return count;
    }

    public static long fourPathSEVENTEENCount(FourPath fourPath, BipartiteGraph graph){
        long count = 0;

        count += (long) (fourPath.node_list.get(0).degree - 1) * (fourPath.node_list.get(3).degree - 1);

        for (int id: fourPath.node_list.get(0).adjacency_list.keySet()){
            if (fourPath.ids.contains(id)) continue;
            count += graph.map.get(id).degree - 1;
        }
        for (int id: fourPath.node_list.get(3).adjacency_list.keySet()){
            if (fourPath.ids.contains(id)) continue;
            count += graph.map.get(id).degree - 1;
        }

        return count;
    }

    public static ArrayList<Integer> getIntersection(Node[] nodes) {
        return nodes[0].adjacency_list.keySet().stream()
                .filter(nodes[1].adjacency_list.keySet()::contains).collect(Collectors.toCollection(ArrayList::new));

    }
}






