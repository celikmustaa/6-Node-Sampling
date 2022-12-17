import java.util.ArrayList;
import java.util.Collections;

public class ExactCountInduced {

    // 6-Vertex Counts
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
            if(!cycle.node_list.containsKey(id) && !left_nodes.get(1).adjacency_list.containsKey(id)){
                for(int id2: right_nodes.get(0).adjacency_list.keySet()) {
                    if (!cycle.node_list.containsKey(id2) && !right_nodes.get(1).adjacency_list.containsKey(id2) && graph.map.get(id).adjacency_list.containsKey(id2)) {
                        count += 1;
                    }
                }
                for(int id3: right_nodes.get(1).adjacency_list.keySet()) {
                    if (!cycle.node_list.containsKey(id3) && !right_nodes.get(0).adjacency_list.containsKey(id3) && graph.map.get(id).adjacency_list.containsKey(id3)) {
                        count += 1;
                    }
                }
            }
        }

        for(int id: left_nodes.get(1).adjacency_list.keySet()) {
            if(!cycle.node_list.containsKey(id) && !left_nodes.get(0).adjacency_list.containsKey(id)){
                for(int id2: right_nodes.get(0).adjacency_list.keySet()) {
                    if (!cycle.node_list.containsKey(id2) && !right_nodes.get(1).adjacency_list.containsKey(id2) && graph.map.get(id).adjacency_list.containsKey(id2)) {
                        count += 1;
                    }
                }
                for(int id3: right_nodes.get(1).adjacency_list.keySet()) {
                    if (!cycle.node_list.containsKey(id3) && !right_nodes.get(0).adjacency_list.containsKey(id3) && graph.map.get(id).adjacency_list.containsKey(id3)) {
                        count += 1;
                    }
                }
            }
        }

        return count;
    }

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

        for(int id: left_nodes.get(0).adjacency_list.keySet()) {
            if(!cycle.node_list.containsKey(id) && !left_nodes.get(1).adjacency_list.containsKey(id)){
                for(int id2: right_nodes.get(0).adjacency_list.keySet()) {
                    if (!cycle.node_list.containsKey(id2) && !right_nodes.get(1).adjacency_list.containsKey(id2) && !graph.map.get(id).adjacency_list.containsKey(id2)) {
                        count += 1;
                    }
                }
                for(int id3: right_nodes.get(1).adjacency_list.keySet()) {
                    if (!cycle.node_list.containsKey(id3) && !right_nodes.get(0).adjacency_list.containsKey(id3) && !graph.map.get(id).adjacency_list.containsKey(id3)) {
                        count += 1;
                    }
                }
            }
        }

        for(int id: left_nodes.get(1).adjacency_list.keySet()) {
            if(!cycle.node_list.containsKey(id) && !left_nodes.get(0).adjacency_list.containsKey(id)){
                for(int id2: right_nodes.get(0).adjacency_list.keySet()) {
                    if (!cycle.node_list.containsKey(id2) && !right_nodes.get(1).adjacency_list.containsKey(id2) && !graph.map.get(id).adjacency_list.containsKey(id2)) {
                        count += 1;
                    }
                }
                for(int id3: right_nodes.get(1).adjacency_list.keySet()) {
                    if (!cycle.node_list.containsKey(id3) && !right_nodes.get(0).adjacency_list.containsKey(id3) && !graph.map.get(id).adjacency_list.containsKey(id3)) {
                        count += 1;
                    }
                }
            }
        }

        return count;
    }

    public static long cycleEIGHTCount(Cycle cycle, BipartiteGraph graph){
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

        long neighbours = 0;

        for(int id: left_nodes.get(0).adjacency_list.keySet()) {
            if(!cycle.node_list.containsKey(id) && !left_nodes.get(1).adjacency_list.containsKey(id)){
                neighbours += 1;
            }
        }
        count += neighbours* (neighbours-1)/2;
        neighbours = 0;

        for(int id: left_nodes.get(1).adjacency_list.keySet()) {
            if(!cycle.node_list.containsKey(id) && !left_nodes.get(0).adjacency_list.containsKey(id)){
                neighbours += 1;
            }
        }
        count += neighbours* (neighbours-1)/2;
        neighbours = 0;

        for(int id: right_nodes.get(0).adjacency_list.keySet()) {
            if(!cycle.node_list.containsKey(id) && !right_nodes.get(1).adjacency_list.containsKey(id)){
                neighbours += 1;
            }
        }
        count += neighbours* (neighbours-1)/2;
        neighbours = 0;

        for(int id: right_nodes.get(1).adjacency_list.keySet()) {
            if(!cycle.node_list.containsKey(id) && !right_nodes.get(0).adjacency_list.containsKey(id)){
                neighbours += 1;
            }
        }
        count += neighbours* (neighbours-1)/2;

        return count;
    }

    public static long cycleNINECount(Cycle cycle, BipartiteGraph graph){
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
            if(!cycle.node_list.containsKey(id) && !left_nodes.get(1).adjacency_list.containsKey(id)){
                for(int id2: left_nodes.get(1).adjacency_list.keySet()) {
                    if(!cycle.node_list.containsKey(id2) && !left_nodes.get(0).adjacency_list.containsKey(id2)){
                        count += 1;
                    }
                }
            }
        }

        for(int id: right_nodes.get(0).adjacency_list.keySet()) {
            if(!cycle.node_list.containsKey(id) && !right_nodes.get(1).adjacency_list.containsKey(id)){
                for(int id2: right_nodes.get(1).adjacency_list.keySet()) {
                    if(!cycle.node_list.containsKey(id2) && !right_nodes.get(0).adjacency_list.containsKey(id2)){
                        count += 1;
                    }
                }
            }
        }

        return count;
    }


    public static long cycleTENCount(Cycle cycle, BipartiteGraph graph){
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
            if(!cycle.node_list.containsKey(id) && !left_nodes.get(1).adjacency_list.containsKey(id)){
                for(int id2: graph.map.get(id).adjacency_list.keySet()) {
                    if (!right_nodes.get(0).adjacency_list.containsKey(id2) && !right_nodes.get(1).adjacency_list.containsKey(id2)){
                        count += 1;
                    }
                }
            }
        }

        for(int id: left_nodes.get(1).adjacency_list.keySet()) {
            if(!cycle.node_list.containsKey(id) && !left_nodes.get(0).adjacency_list.containsKey(id)){
                for(int id2: graph.map.get(id).adjacency_list.keySet()) {
                    if (!right_nodes.get(0).adjacency_list.containsKey(id2) && !right_nodes.get(1).adjacency_list.containsKey(id2)){
                        count += 1;
                    }
                }
            }
        }

        for(int id: right_nodes.get(0).adjacency_list.keySet()) {
            if(!cycle.node_list.containsKey(id) && !right_nodes.get(1).adjacency_list.containsKey(id)){
                for(int id2: graph.map.get(id).adjacency_list.keySet()) {
                    if (!left_nodes.get(0).adjacency_list.containsKey(id2) && !left_nodes.get(1).adjacency_list.containsKey(id2)){
                        count += 1;
                    }
                }
            }
        }

        for(int id: right_nodes.get(1).adjacency_list.keySet()) {
            if(!cycle.node_list.containsKey(id) && !right_nodes.get(0).adjacency_list.containsKey(id)){
                for(int id2: graph.map.get(id).adjacency_list.keySet()) {
                    if (!left_nodes.get(0).adjacency_list.containsKey(id2) && !left_nodes.get(1).adjacency_list.containsKey(id2)){
                        count += 1;
                    }
                }
            }
        }
        return count;
    }




    //ABPM
    public static long cycleFOURCount(Cycle cycle, BipartiteGraph graph){
        long count = 0;

        for (String key: graph.wedge_map_left.keySet()) {

        }


        return count;
    }



}
