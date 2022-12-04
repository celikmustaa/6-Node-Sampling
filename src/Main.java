public class Main {
    public static void main(String[] args) {
//        FileFormatter.toMotivo();

        BipartiteGraph graph = CreateGraph.createGraph();
        graph.fillWedgeMap();

        System.out.println("Number of L nodes: " + graph.L.size()+"\tNumber of R nodes: "+graph.R.size());
        System.out.println("Number of nodes: " + graph.map.size()+"\tNumber of edges: "+graph.edge_list.size());

        // System.out.println("wedge_map: " + graph.wedge_map);
//        System.out.print("key_count: ");
//        for(BipartiteGraph.KeyCount x: graph.key_count){
//            System.out.println("[" + x.key +", " + x.cycle_count +  "]\t");
//        }
//        System.out.println();



        int[] k_sets = {1, 2, 5, 10, 20, 50, 100, 200, 500, 1000, 3000, 100000};
        //int[] k_sets = {1, 2, 5};

        System.out.println("\n         sample        EIGHT-AAPM          NINE-ACNM           TEN-BBJM");

        for(int k: k_sets){
            System.out.printf("%15d%18d ", k, (long) Sample.cycleEIGHTSample(k, graph));
            System.out.printf("%18d ", (long) Sample.cycleNINESample(k, graph));
            System.out.printf("%18d ", (long) Sample.cycleTENSample(k, graph));
            System.out.println();

        }


//        System.out.println(graph.wedge_map.get("51-39").size());
//        System.out.println(graph.wedge_map.get("51-39"));
    } // main
} // Main
