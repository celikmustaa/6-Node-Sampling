import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class ExactCountNonInduced {
    public static long cycleM1Count(Cycle cycle, BipartiteGraph graph) {
        long count = 0;

        for (int id : cycle.left_nodes[0].adjacency_list.keySet()) {
            if (!cycle.map.containsKey(id)) {
                HashMap<Integer, Integer> id_adj = graph.map.get(id).adjacency_list;
                for (int id2 : cycle.right_nodes[0].adjacency_list.keySet()) {
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

    public static long cycleM2Count(Cycle cycle, BipartiteGraph graph) {
        long count = 0;

        ArrayList<Integer> left_intersection = getIntersection(cycle.left_nodes);
        ArrayList<Integer> right_intersection = getIntersection(cycle.right_nodes);

        // 6 possible cycle choice
        count += (long) (left_intersection.size() - 2) * (left_intersection.size() - 3) / 2;
        count += (long) (right_intersection.size() - 2) * (right_intersection.size() - 3) / 2;

        return count;
    }

    public static long cycleM3Count(Cycle cycle, BipartiteGraph graph) {
        long count = 0;

        ArrayList<Integer> left_intersection = getIntersection(cycle.left_nodes);
        ArrayList<Integer> right_intersection = getIntersection(cycle.right_nodes);

        for (int id : left_intersection) {
            if (!cycle.map.containsKey(id)) {
                for (int id2 : graph.map.get(id).adjacency_list.keySet()) {
                    if (!cycle.map.containsKey(id2)) {
                        if (cycle.right_nodes[0].adjacency_list.containsKey(id2)) {
                            count += 1;
                        }
                        if (cycle.right_nodes[1].adjacency_list.containsKey(id2)) {
                            count += 1;
                        }
                    }
                }
            }
        }


        for (int id : right_intersection) {
            if (!cycle.map.containsKey(id)) {
                for (int id2 : graph.map.get(id).adjacency_list.keySet()) {
                    if (!cycle.map.containsKey(id2)) {
                        if (cycle.left_nodes[0].adjacency_list.containsKey(id2)) {
                            count += 1;
                        }
                        if (cycle.left_nodes[1].adjacency_list.containsKey(id2)) {
                            count += 1;
                        }
                    }
                }
            }
        }


        count += (long) (left_intersection.size() - 2) * (right_intersection.size() - 2);


        return count;
    }

    public static long cycleM4Count(Cycle cycle, BipartiteGraph graph) {
        long count = 0;

        ArrayList<Integer> left_intersection = getIntersection(cycle.left_nodes);
        ArrayList<Integer> right_intersection = getIntersection(cycle.right_nodes);

        count += (long) (cycle.left_nodes[0].degree - 2 + cycle.left_nodes[1].degree - 2) * (left_intersection.size() - 2);
        count += (long) (cycle.right_nodes[0].degree - 2 + cycle.right_nodes[1].degree - 2) * (right_intersection.size() - 2);

        return count;
    }

    public static long cycleM5Count(Cycle cycle, BipartiteGraph graph) {
        long count = 0;

        ArrayList<Integer> left_intersection = getIntersection(cycle.left_nodes);
        ArrayList<Integer> right_intersection = getIntersection(cycle.right_nodes);


        // If left cycle is chosen
        for (int id : left_intersection) {
            if (!cycle.map.containsKey(id)) {
                count += graph.map.get(id).degree - 2;
            }
        }
        for (int id : right_intersection) {
            if (!cycle.map.containsKey(id)) {
                count += graph.map.get(id).degree - 2;
            }
        }

        // If right or outer cycle is chosen
        count += (long) (cycle.right_nodes[0].degree - 2 + cycle.right_nodes[1].degree - 2) * (left_intersection.size() - 2);
        count += (long) (cycle.left_nodes[0].degree - 2 + cycle.left_nodes[1].degree - 2) * (right_intersection.size() - 2);


        return count;
    }

    public static long cycleM6Count(Cycle cycle, BipartiteGraph graph) {
        long count = 0;

        for (int id : cycle.left_nodes[0].adjacency_list.keySet()) {
            if (!cycle.map.containsKey(id)) {
                HashMap<Integer, Integer> id_adj = graph.map.get(id).adjacency_list;
                for (int id2 : cycle.right_nodes[0].adjacency_list.keySet()) {
                    if (!cycle.map.containsKey(id2) && id_adj.containsKey(id2)) {
                        count += 1;
                    }
                }
                for (int id3 : cycle.right_nodes[1].adjacency_list.keySet()) {
                    if (!cycle.map.containsKey(id3) && id_adj.containsKey(id3)) {
                        count += 1;
                    }
                }
            }
        }

        for (int id : cycle.left_nodes[1].adjacency_list.keySet()) {
            if (!cycle.map.containsKey(id)) {
                HashMap<Integer, Integer> id_adj = graph.map.get(id).adjacency_list;
                for (int id2 : cycle.right_nodes[0].adjacency_list.keySet()) {
                    if (!cycle.map.containsKey(id2) && id_adj.containsKey(id2)) {
                        count += 1;
                    }
                }
                for (int id3 : cycle.right_nodes[1].adjacency_list.keySet()) {
                    if (!cycle.map.containsKey(id3) && id_adj.containsKey(id3)) {
                        count += 1;
                    }
                }
            }
        }

        return count;
    }


    public static long cycleM7Count(Cycle cycle, BipartiteGraph graph) {
        long count = 0;

        for (Node node : cycle.map.values()) {
            long neighbors = 0;
            for (int neighbor_id : node.adjacency_list.keySet()) {
                if (!cycle.map.containsKey(neighbor_id)) {
                    neighbors++; // TODO: use (degree -2) as neighbour count
                }
            }
            count += neighbors * (neighbors - 1) / 2;
        }

        return count;
    }

    public static long cycleM8Count(Cycle cycle, BipartiteGraph graph) {
        long count = 0;

        for (Node left_node : cycle.left_nodes) {
            for (Node right_node : cycle.right_nodes) {
                count += (long) (left_node.degree - 2) * (right_node.degree - 2);
            }
        }

        return count;
    }

    public static long cycleM9Count(Cycle cycle, BipartiteGraph graph) {
        long count = 0;

        count += (long) (cycle.left_nodes[0].degree - 2) * (cycle.left_nodes[1].degree - 2) - (getIntersection(cycle.left_nodes).size() - 2);
        count += (long) (cycle.right_nodes[0].degree - 2) * (cycle.right_nodes[1].degree - 2) - (getIntersection(cycle.right_nodes).size() - 2);

        return count;
    }


    public static long cycleM10Count(Cycle cycle, BipartiteGraph graph) {
        long count = 0;

        for (int neighbour_id : cycle.left_nodes[0].adjacency_list.keySet()) {
            if (!cycle.map.containsKey(neighbour_id)) {
                if (cycle.left_nodes[1].adjacency_list.containsKey(neighbour_id)) {
                    count += graph.map.get(neighbour_id).degree - 2;
                } else {
                    count += graph.map.get(neighbour_id).degree - 1;
                }

            }
        }
        for (int neighbour_id : cycle.left_nodes[1].adjacency_list.keySet()) {
            if (!cycle.map.containsKey(neighbour_id)) {
                if (cycle.left_nodes[0].adjacency_list.containsKey(neighbour_id)) {
                    count += graph.map.get(neighbour_id).degree - 2;
                } else {
                    count += graph.map.get(neighbour_id).degree - 1;
                }

            }
        }
        for (int neighbour_id : cycle.right_nodes[0].adjacency_list.keySet()) {
            if (!cycle.map.containsKey(neighbour_id)) {
                if (cycle.right_nodes[1].adjacency_list.containsKey(neighbour_id)) {
                    count += graph.map.get(neighbour_id).degree - 2;
                } else {
                    count += graph.map.get(neighbour_id).degree - 1;
                }

            }
        }
        for (int neighbour_id : cycle.right_nodes[1].adjacency_list.keySet()) {
            if (!cycle.map.containsKey(neighbour_id)) {
                if (cycle.right_nodes[0].adjacency_list.containsKey(neighbour_id)) {
                    count += graph.map.get(neighbour_id).degree - 2;
                } else {
                    count += graph.map.get(neighbour_id).degree - 1;
                }

            }
        }

        return count;
    }

    public static long fourPathM11Count(FourPath fourPath, BipartiteGraph graph) {
        long count = 0;

        for (int id0 : fourPath.node_list.get(0).adjacency_list.keySet()) {
            if (fourPath.ids.contains(id0)) continue;
            Node currNode = graph.map.get(id0);
            for (int id3 : fourPath.node_list.get(3).adjacency_list.keySet()) {
                if (fourPath.ids.contains(id3)) continue;
                if (currNode.adjacency_list.containsKey(id3)) {
                    count++;
                }
            }
        }


        return count;
    }

    public static long fourPathM12Count(FourPath fourPath, BipartiteGraph graph) {
        long count = 0;

        return count;
    }

    public static long fourPathM13Count(FourPath fourPath, BipartiteGraph graph) {
        long count = 0;

        int node1_degree = (fourPath.node_list.get(1).degree - 2);
        int node2_degree = (fourPath.node_list.get(2).degree - 2);

        count += ((long) node1_degree * (node1_degree - 1)) / 2;
        count += ((long) node2_degree * (node2_degree - 1)) / 2;

        return count;
    }

    public static long fourPathM14Count(FourPath fourPath, BipartiteGraph graph) {
        return (long) (fourPath.node_list.get(1).degree - 2) * (fourPath.node_list.get(2).degree - 2);
    }

    public static long fourPathM15Count(FourPath fourPath, BipartiteGraph graph) {
        long count = 0;

        for (int id : fourPath.node_list.get(1).adjacency_list.keySet()) {
            if (fourPath.ids.contains(id)) continue;

            if (fourPath.node_list.get(3).adjacency_list.containsKey(id)) {
                count += graph.map.get(id).degree - 2;
            } else {
                count += graph.map.get(id).degree - 1;
            }

        }

        for (int id : fourPath.node_list.get(2).adjacency_list.keySet()) {
            if (fourPath.ids.contains(id)) continue;

            if (fourPath.node_list.get(0).adjacency_list.containsKey(id)) {
                count += graph.map.get(id).degree - 2;
            } else {
                count += graph.map.get(id).degree - 1;
            }
        }

        if (fourPath.node_list.get(0).adjacency_list.containsKey(fourPath.node_list.get(3).id)) {
            count += (long) (fourPath.node_list.get(0).degree - 2) * (fourPath.node_list.get(1).degree - 2);
            count += (long) (fourPath.node_list.get(3).degree - 2) * (fourPath.node_list.get(2).degree - 2);
        } else {
            count += (long) (fourPath.node_list.get(0).degree - 1) * (fourPath.node_list.get(1).degree - 2);
            count += (long) (fourPath.node_list.get(3).degree - 1) * (fourPath.node_list.get(2).degree - 2);
        }

        return count;
    }

    public static long fourPathM16Count(FourPath fourPath, BipartiteGraph graph) {
        long count = 0;

        int node0_degree = (fourPath.node_list.get(0).degree - 1);
        int node3_degree = (fourPath.node_list.get(3).degree - 1);

        count += (long) (fourPath.node_list.get(1).degree - 2) * (fourPath.node_list.get(3).degree - 1);
        count += (long) (fourPath.node_list.get(2).degree - 2) * (fourPath.node_list.get(0).degree - 1);
        count += ((long) node0_degree * (node0_degree - 1)) / 2;
        count += ((long) node3_degree * (node3_degree - 1)) / 2;

        return count;
    }

    public static long fourPathM17Count(FourPath fourPath, BipartiteGraph graph) {
        long count = 0;

        if (fourPath.node_list.get(0).adjacency_list.containsKey(fourPath.node_list.get(3).id)) {
            count += (long) (fourPath.node_list.get(0).degree - 2) * (fourPath.node_list.get(3).degree - 2);
        } else {
            count += (long) (fourPath.node_list.get(0).degree - 1) * (fourPath.node_list.get(3).degree - 1);
        }


        for (int id : fourPath.node_list.get(0).adjacency_list.keySet()) {
            if (fourPath.ids.contains(id)) continue;
            count += graph.map.get(id).degree - 1;
            if (fourPath.node_list.get(2).adjacency_list.containsKey(id)) {
                count -= 1;
            }
        }

        for (int id : fourPath.node_list.get(3).adjacency_list.keySet()) {
            if (fourPath.ids.contains(id)) continue;
            count += graph.map.get(id).degree - 1;
            if (fourPath.node_list.get(1).adjacency_list.containsKey(id)) {
                count -= 1;
            }
        }

        return count;
    }

    public static ArrayList<Integer> getIntersection(Node[] nodes) {
        return nodes[0].adjacency_list.keySet().stream()
                .filter(nodes[1].adjacency_list.keySet()::contains).collect(Collectors.toCollection(ArrayList::new));

    }
}






