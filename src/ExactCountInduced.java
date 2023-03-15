import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ExactCountInduced {

    // 6-Vertex Counts

    public static long cycleONECount(Cycle cycle, BipartiteGraph graph){
        return ExactCountNonInduced.cycleONECount(cycle, graph);
    }

    public static long cycleTWOCount(Cycle cycle, BipartiteGraph graph){
        return ExactCountNonInduced.cycleTWOCount(cycle, graph);
    }

    public static long cycleTHREECount(Cycle cycle, BipartiteGraph graph){
        long count = 0;


        ArrayList<Node> left_nodes = new ArrayList<>();
        ArrayList<Node> right_nodes = new ArrayList<>();

        for(Node node: cycle.node_list.values()) {
            if (node.id < 0){
                left_nodes.add(node);
            }else {
                right_nodes.add(node);
            }
        }

        ArrayList<Integer> left_0_adj = new ArrayList<>(left_nodes.get(0).adjacency_list.keySet());
        ArrayList<Integer> left_1_adj = new ArrayList<>(left_nodes.get(1).adjacency_list.keySet());
        ArrayList<Integer> right_0_adj = new ArrayList<>(right_nodes.get(0).adjacency_list.keySet());
        ArrayList<Integer> right_1_adj = new ArrayList<>(right_nodes.get(1).adjacency_list.keySet());

        for(int id: left_0_adj) {
            if(!cycle.node_list.containsKey(id)){
                HashMap<Integer, Integer> id_adj = graph.map.get(id).adjacency_list;
                for(int id2: right_0_adj) {
                    if (!cycle.node_list.containsKey(id2) && id_adj.containsKey(id2)) {
                        if ((left_nodes.get(1).adjacency_list.containsKey(id) && !right_nodes.get(1).adjacency_list.containsKey(id2)) ||(!left_nodes.get(1).adjacency_list.containsKey(id) && right_nodes.get(1).adjacency_list.containsKey(id2))) {
                            count += 1;
                        }

                    }
                }
            }
        }

        for(int id: left_1_adj) {
            if(!cycle.node_list.containsKey(id)){
                HashMap<Integer, Integer> id_adj = graph.map.get(id).adjacency_list;
                for(int id3: right_1_adj) {
                    if (!cycle.node_list.containsKey(id3) && id_adj.containsKey(id3)) {
                        if ((left_nodes.get(0).adjacency_list.containsKey(id) && !right_nodes.get(0).adjacency_list.containsKey(id3)) || (!left_nodes.get(0).adjacency_list.containsKey(id) && right_nodes.get(0).adjacency_list.containsKey(id3))) {
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
        ArrayList<Integer> left_1_adj = new ArrayList<>(left_nodes.get(1).adjacency_list.keySet());
        ArrayList<Integer> right_0_adj = new ArrayList<>(right_nodes.get(0).adjacency_list.keySet());
        ArrayList<Integer> right_1_adj = new ArrayList<>(right_nodes.get(1).adjacency_list.keySet());

        left_0_adj.retainAll(left_1_adj);
        right_0_adj.retainAll(right_1_adj);

        ArrayList<Integer> left_intersection = left_0_adj;
        ArrayList<Integer> right_intersection = right_0_adj;

        left_intersection.remove(Integer.valueOf(right_nodes.get(0).id));
        left_intersection.remove(Integer.valueOf(right_nodes.get(1).id));
        right_intersection.remove(Integer.valueOf(left_nodes.get(0).id));
        right_intersection.remove(Integer.valueOf(left_nodes.get(1).id));

        count += (long) left_intersection.size() * (left_nodes.get(0).adjacency_list.size() - 2 - left_intersection.size());
        count += (long) left_intersection.size() * (left_nodes.get(1).adjacency_list.size() - 2 - left_intersection.size());
        count += (long) right_intersection.size() * (right_nodes.get(0).adjacency_list.size() - 2 - right_intersection.size());
        count += (long) right_intersection.size() * (right_nodes.get(1).adjacency_list.size() - 2 - right_intersection.size());

        return count;
    }

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
        ArrayList<Integer> left_1_adj = new ArrayList<>(left_nodes.get(1).adjacency_list.keySet());
        ArrayList<Integer> right_0_adj = new ArrayList<>(right_nodes.get(0).adjacency_list.keySet());
        ArrayList<Integer> right_1_adj = new ArrayList<>(right_nodes.get(1).adjacency_list.keySet());

        // TODO check for optimal functions
        left_0_adj.retainAll(left_1_adj);
        right_0_adj.retainAll(right_1_adj);

        ArrayList<Integer> left_intersection = left_0_adj;
        ArrayList<Integer> right_intersection = right_0_adj;

        left_intersection.remove(Integer.valueOf(right_nodes.get(0).id));
        left_intersection.remove(Integer.valueOf(right_nodes.get(1).id));
        right_intersection.remove(Integer.valueOf(left_nodes.get(0).id));
        right_intersection.remove(Integer.valueOf(left_nodes.get(1).id));


        for (int id: right_intersection) {
            for (int id2 : graph.map.get(id).adjacency_list.keySet()) {
                if (!left_intersection.contains((id2))) {
                    count++;
                }
            }
        }
        for (int id: left_intersection){
            for (int id2: graph.map.get(id).adjacency_list.keySet()){
                if (!right_intersection.contains((id2))){
                    count++;
                }
            }
        }

        for (int id : left_nodes.get(0).adjacency_list.keySet()){
            if (!left_nodes.get(1).adjacency_list.containsKey(id)){
                for (int right_id : right_intersection){
                    if (graph.map.get(right_id).adjacency_list.containsKey(id)){
                        count++;
                    }
                }
            }
        }
        for (int id : left_nodes.get(1).adjacency_list.keySet()){
            if (!left_nodes.get(0).adjacency_list.containsKey(id)){
                for (int right_id : right_intersection){
                    if (graph.map.get(right_id).adjacency_list.containsKey(id)){
                        count++;
                    }
                }
            }
        }
        for (int id : right_nodes.get(0).adjacency_list.keySet()){
            if (!right_nodes.get(1).adjacency_list.containsKey(id)){
                for (int left_id : left_intersection){
                    if (graph.map.get(left_id).adjacency_list.containsKey(id)){
                        count++;
                    }
                }
            }
        }
        for (int id : left_nodes.get(1).adjacency_list.keySet()){
            if (!left_nodes.get(0).adjacency_list.containsKey(id)){
                for (int left_id : left_intersection){
                    if (graph.map.get(left_id).adjacency_list.containsKey(id)){
                        count++;
                    }
                }
            }
        }

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

    public static long fourPathELEVENCount(FourPath fourPath, BipartiteGraph graph){
        long count = 0;

        ArrayList<Integer> left_0_adj = new ArrayList<>(fourPath.node_list.get(0).adjacency_list.keySet());
        ArrayList<Integer> right_0_adj = new ArrayList<>(fourPath.node_list.get(1).adjacency_list.keySet());
        ArrayList<Integer> left_1_adj = new ArrayList<>(fourPath.node_list.get(2).adjacency_list.keySet());
        ArrayList<Integer> right_1_adj = new ArrayList<>(fourPath.node_list.get(3).adjacency_list.keySet());

        left_0_adj.retainAll(left_1_adj);
        right_0_adj.retainAll(right_1_adj);

        ArrayList<Integer> left_intersection = left_0_adj;
        ArrayList<Integer> right_intersection = right_0_adj;
        left_0_adj = new ArrayList<>(fourPath.node_list.get(0).adjacency_list.keySet());

        left_intersection.remove(Integer.valueOf(fourPath.node_list.get(2).id));
        right_intersection.remove(Integer.valueOf(fourPath.node_list.get(1).id));

        // RETURN 0 IF FOURPATH IS A CYCLE
        if (left_intersection.contains(fourPath.node_list.get(3).id) || right_intersection.contains(fourPath.node_list.get(0).id)){
            return 0;
        }

        for(int id0: left_0_adj){
            if (!left_intersection.contains(id0)){ // Induced check
                for (int id1: right_1_adj){
                    if (!right_intersection.contains(id1)){ // Induced check
                        if(graph.map.get(id0).adjacency_list.containsKey(id1)){ // Final edge check
                            count ++;
                        }
                    }
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

        ArrayList<Integer> ids = new ArrayList<>();
        for (int i=0; i < 4 ;i++){
            ids.add(fourPath.node_list.get(i).id);
        }

        ArrayList<Integer> left_0_adj = new ArrayList<>(fourPath.node_list.get(0).adjacency_list.keySet());
        ArrayList<Integer> right_0_adj = new ArrayList<>(fourPath.node_list.get(1).adjacency_list.keySet());
        ArrayList<Integer> left_1_adj = new ArrayList<>(fourPath.node_list.get(2).adjacency_list.keySet());
        ArrayList<Integer> right_1_adj = new ArrayList<>(fourPath.node_list.get(3).adjacency_list.keySet());

        left_0_adj.retainAll(left_1_adj);
        right_0_adj.retainAll(right_1_adj);

        ArrayList<Integer> left_intersection = left_0_adj;
        ArrayList<Integer> right_intersection = right_0_adj;

        left_intersection.remove(Integer.valueOf(fourPath.node_list.get(2).id));
        right_intersection.remove(Integer.valueOf(fourPath.node_list.get(1).id));

        // RETURN 0 IF FOURPATH IS A CYCLE
        if (left_intersection.contains(fourPath.node_list.get(3).id) || right_intersection.contains(fourPath.node_list.get(0).id)){
            return 0;
        }

        for(int id: fourPath.node_list.get(1).adjacency_list.keySet()){
            if ((!ids.contains(id)) && (!right_intersection.contains(id))){
                count++;
            }
        }
        for(int id: fourPath.node_list.get(2).adjacency_list.keySet()){
            if ((!ids.contains(id)) && (!left_intersection.contains(id))){
                count++;
            }
        }

        return count;
    }

    public static long fourPathFOURTEENCount(FourPath fourPath, BipartiteGraph graph){
        long count = 0;

        ArrayList<Integer> ids = new ArrayList<>();
        for (int i=0; i < 4 ;i++){
            ids.add(fourPath.node_list.get(i).id);
        }

        ArrayList<Integer> left_0_adj = new ArrayList<>(fourPath.node_list.get(0).adjacency_list.keySet());
        ArrayList<Integer> right_0_adj = new ArrayList<>(fourPath.node_list.get(1).adjacency_list.keySet());
        ArrayList<Integer> left_1_adj = new ArrayList<>(fourPath.node_list.get(2).adjacency_list.keySet());
        ArrayList<Integer> right_1_adj = new ArrayList<>(fourPath.node_list.get(3).adjacency_list.keySet());

        left_0_adj.retainAll(left_1_adj);
        right_0_adj.retainAll(right_1_adj);

        ArrayList<Integer> left_intersection = left_0_adj;
        ArrayList<Integer> right_intersection = right_0_adj;

        left_intersection.remove(Integer.valueOf(fourPath.node_list.get(2).id));
        right_intersection.remove(Integer.valueOf(fourPath.node_list.get(1).id));

        // RETURN 0 IF FOURPATH IS A CYCLE
        if (left_intersection.contains(fourPath.node_list.get(3).id) || right_intersection.contains(fourPath.node_list.get(0).id)){
            return 0;
        }

        for(int id0: fourPath.node_list.get(1).adjacency_list.keySet()){
            if ((!ids.contains(id0)) && (!right_intersection.contains(id0))){ // Induced check
                for(int id1: fourPath.node_list.get(2).adjacency_list.keySet()){
                    if ((!ids.contains(id1)) && (!left_intersection.contains(id1))){ //Induced check
                        if(graph.map.get(id0).adjacency_list.containsKey(id1)) { // Final edge check
                            count++;
                        }
                    }
                }
            }
        }

        return count;
    }

    public static long fourPathFIFTEENCount(FourPath fourPath, BipartiteGraph graph){
        long count = 0;


        ArrayList<Integer> ids = new ArrayList<>();
        for (int i=0; i < 4 ;i++){
            ids.add(fourPath.node_list.get(i).id);
        }

        ArrayList<Integer> left_0_adj = new ArrayList<>(fourPath.node_list.get(0).adjacency_list.keySet());
        ArrayList<Integer> right_0_adj = new ArrayList<>(fourPath.node_list.get(1).adjacency_list.keySet());
        ArrayList<Integer> left_1_adj = new ArrayList<>(fourPath.node_list.get(2).adjacency_list.keySet());
        ArrayList<Integer> right_1_adj = new ArrayList<>(fourPath.node_list.get(3).adjacency_list.keySet());

        left_0_adj.retainAll(left_1_adj);
        right_0_adj.retainAll(right_1_adj);

        ArrayList<Integer> left_intersection = left_0_adj;
        ArrayList<Integer> right_intersection = right_0_adj;
        left_0_adj = new ArrayList<>(fourPath.node_list.get(0).adjacency_list.keySet());
        right_0_adj = new ArrayList<>(fourPath.node_list.get(1).adjacency_list.keySet());

        left_intersection.remove(Integer.valueOf(fourPath.node_list.get(2).id));
        right_intersection.remove(Integer.valueOf(fourPath.node_list.get(1).id));

        // RETURN 0 IF FOURPATH IS A CYCLE
        if (left_intersection.contains(fourPath.node_list.get(3).id) || right_intersection.contains(fourPath.node_list.get(0).id)){
            return 0;
        }

        for(int id0: fourPath.node_list.get(1).adjacency_list.keySet()){
            if ((!ids.contains(id0)) && (!right_intersection.contains(id0))){ // Induced check
                for (int id1: graph.map.get(id0).adjacency_list.keySet()) {
                    if ((!ids.contains(id1)) && (!left_0_adj.contains(id1)) && (!left_1_adj.contains(id1))) { // Induced check
                        count++;

                    }
                }
            }
        }
        for(int id0: fourPath.node_list.get(2).adjacency_list.keySet()){
            if ((!ids.contains(id0)) && (!left_intersection.contains(id0))){ // Induced check
                for (int id1: graph.map.get(id0).adjacency_list.keySet()){
                    if ((!ids.contains(id1)) && (!right_0_adj.contains(id1)) && (!right_1_adj.contains(id1))) { // Induced check
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public static long fourPathSIXTEENCount(FourPath fourPath, BipartiteGraph graph){
        long count = 0;

        ArrayList<Integer> ids = new ArrayList<>();
        for (int i=0; i < 4 ;i++){
            ids.add(fourPath.node_list.get(i).id);
        }

        ArrayList<Integer> left_0_adj = new ArrayList<>(fourPath.node_list.get(0).adjacency_list.keySet());
        ArrayList<Integer> right_0_adj = new ArrayList<>(fourPath.node_list.get(1).adjacency_list.keySet());
        ArrayList<Integer> left_1_adj = new ArrayList<>(fourPath.node_list.get(2).adjacency_list.keySet());
        ArrayList<Integer> right_1_adj = new ArrayList<>(fourPath.node_list.get(3).adjacency_list.keySet());

        left_0_adj.retainAll(left_1_adj);
        right_0_adj.retainAll(right_1_adj);

        ArrayList<Integer> left_intersection = left_0_adj;
        ArrayList<Integer> right_intersection = right_0_adj;

        left_intersection.remove(Integer.valueOf(fourPath.node_list.get(2).id));
        right_intersection.remove(Integer.valueOf(fourPath.node_list.get(1).id));

        // RETURN 0 IF FOURPATH IS A CYCLE
        if (left_intersection.contains(fourPath.node_list.get(3).id) || right_intersection.contains(fourPath.node_list.get(0).id)){
            return 0;
        }

        for(int id0: fourPath.node_list.get(0).adjacency_list.keySet()){
            if ((!ids.contains(id0)) && (!left_intersection.contains(id0))){ // Induced check
                for(int id1: fourPath.node_list.get(0).adjacency_list.keySet()){
                    if ((id0 != id1) && (!ids.contains(id0)) && (!left_intersection.contains(id0))) { // Induced check
                        count++;
                    }
                }
            }
        }

        for(int id0: fourPath.node_list.get(3).adjacency_list.keySet()){
            if ((!ids.contains(id0)) && (!right_intersection.contains(id0))){ // Induced check
                for(int id1: fourPath.node_list.get(3).adjacency_list.keySet()){
                    if ((id0 != id1) && (!ids.contains(id0)) && (!right_intersection.contains(id0))) { // Induced check
                        count++;
                    }
                }
            }
        }

        for(int id0: fourPath.node_list.get(1).adjacency_list.keySet()){
            if ((!ids.contains(id0)) && (!right_intersection.contains(id0))){ // Induced check
                for(int id1: fourPath.node_list.get(3).adjacency_list.keySet()){
                    if ((!ids.contains(id1)) && (!right_intersection.contains(id1))){
                        count++;
                    }
                }
            }
        }

        for(int id0: fourPath.node_list.get(2).adjacency_list.keySet()){
            if ((!ids.contains(id0)) && (!left_intersection.contains(id0))){ // Induced check
                for(int id1: fourPath.node_list.get(0).adjacency_list.keySet()){
                    if ((!ids.contains(id1)) && (!left_intersection.contains(id1))){
                        count++;
                    }
                }
            }
        }

        return count;
    }

    public static long fourPathSEVENTEENCount(FourPath fourPath, BipartiteGraph graph){
        long count = 0;


        ArrayList<Integer> ids = new ArrayList<>();
        for (int i=0; i < 4 ;i++){
            ids.add(fourPath.node_list.get(i).id);
        }

        ArrayList<Integer> left_0_adj = new ArrayList<>(fourPath.node_list.get(0).adjacency_list.keySet());
        ArrayList<Integer> right_0_adj = new ArrayList<>(fourPath.node_list.get(1).adjacency_list.keySet());
        ArrayList<Integer> left_1_adj = new ArrayList<>(fourPath.node_list.get(2).adjacency_list.keySet());
        ArrayList<Integer> right_1_adj = new ArrayList<>(fourPath.node_list.get(3).adjacency_list.keySet());

        left_0_adj.retainAll(left_1_adj);
        right_0_adj.retainAll(right_1_adj);

        ArrayList<Integer> left_intersection = left_0_adj;
        ArrayList<Integer> right_intersection = right_0_adj;
        left_0_adj = new ArrayList<>(fourPath.node_list.get(0).adjacency_list.keySet());
        right_0_adj = new ArrayList<>(fourPath.node_list.get(1).adjacency_list.keySet());

        left_intersection.remove(Integer.valueOf(fourPath.node_list.get(2).id));
        right_intersection.remove(Integer.valueOf(fourPath.node_list.get(1).id));

        // RETURN 0 IF FOURPATH IS A CYCLE
        if (left_intersection.contains(fourPath.node_list.get(3).id) || right_intersection.contains(fourPath.node_list.get(0).id)){
            return 0;
        }

        for(int id0: fourPath.node_list.get(3).adjacency_list.keySet()){
            if ((!ids.contains(id0)) && (!right_intersection.contains(id0))){ // Induced check
                for(int id1: graph.map.get(id0).adjacency_list.keySet()){
                    if ((!ids.contains(id1)) && (!left_0_adj.contains(id1)) && (!left_1_adj.contains(id1))) { // Induced check
                        count++;
                    }
                }
            }
        }

        for(int id0: fourPath.node_list.get(0).adjacency_list.keySet()){
            if ((!ids.contains(id0)) && (!left_intersection.contains(id0))){ // Induced check
                for(int id1: graph.map.get(id0).adjacency_list.keySet()){
                    if ((!ids.contains(id1)) && (!right_0_adj.contains(id1)) && (!right_1_adj.contains(id1))) { // Induced check
                        count++;
                    }
                }
            }
        }

        for(int id0: fourPath.node_list.get(0).adjacency_list.keySet()){
            if ((!ids.contains(id0)) && (!left_intersection.contains(id0))){ // Induced check
                for(int id1: fourPath.node_list.get(3).adjacency_list.keySet()){
                    if ((!ids.contains(id1)) && (!right_intersection.contains(id1))) { // Induced check
                        if (!graph.map.get(id0).adjacency_list.containsKey(id1)) {
                            count++;
                        }
                    }
                }
            }
        }

        return count;
    }
}
