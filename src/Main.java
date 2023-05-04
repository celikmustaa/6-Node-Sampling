import java.sql.Connection;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        NumberFormat numFormat = new DecimalFormat("0.####E0");
        boolean isInduced = args[0].equals("true");
        System.out.println("isInduced: "+ isInduced);


//        FileFormatter.toMotivo();

//
//        Database.connect();
////        CreateGraph.insertEdges();
//       CreateGraph.insertNodes();
//       CreateGraph.setDegrees();
//        BipartiteGraph.fillWedgeMapTable();
//        BipartiteGraph.fillKeyCountFromSQL();

//        System.out.println("\n\n");
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        System.out.printf("%18s \n",    numFormat.format(Sample.cycleTENSampleSQL(10, isInduced)));



        // OLD VERSION (WITHOUT SQL)
        BipartiteGraph graph = CreateGraph.createGraph();
        graph.fillWedgeMap();
        graph.fillEdgeFourPathCounts(graph);
//
        System.out.println("wedge_map_size: " + graph.wedge_map_left.size());
        System.out.println("key_count_size: " + BipartiteGraph.key_count.size());
        System.out.println("Number of L nodes: " + graph.L.size()+"\tNumber of R nodes: "+graph.R.size());
        System.out.println("Number of nodes: " + graph.map.size()+"\tNumber of edges: "+graph.edge_list.size());



//        for(int id: graph.map.keySet()){
//            System.out.println(id + " " + graph.map.get(id).degree);
//        }

//        System.out.println("wedge_map:: ");
//        for(String key: graph.wedge_map_left.keySet()){
//            System.out.println(key + ": " + graph.wedge_map_left.get(key));
//        }
//        System.out.println("length of wedge_map: " + graph.wedge_map_left.size());

//        System.out.print("key_count: ");
//        for(BipartiteGraph.KeyCount x: graph.key_count){
//            System.out.println("[" + x.key +", " + x.cycle_count +  "]\t");
//        }
//        System.out.println();



        int[] k_sets = {50, 100, 200, 500, 1000, 5000};

        Sample.CYCLE_COUNT = BipartiteGraph.key_count.get(BipartiteGraph.key_count.size()-1).cycle_count;
        Sample.FOUR_PATH_COUNT = graph.getFourPathCount();
        System.out.println("Four path count with method: " + Sample.FOUR_PATH_COUNT);
        Sample.FOUR_PATH_COUNT = BipartiteGraph.edge_four_path_counts.get(BipartiteGraph.edge_four_path_counts.size()-1).four_path_count;
        System.out.println("Four path count with method: " + Sample.FOUR_PATH_COUNT);

        System.out.println("\n         sample         ONE-NBNM           TWO-ADPM           THREE-FBNM         FOUR-ABPM          FIVE-BBNM           SIX-IGFK         SEVEN-AGJG         EIGHT-AAPM          NINE-ACNM           TEN-BBJM        ELEVEN-MBEM        TWELVE-AADO      THIRTEEN-AAHK      FOURTEEN-ACFK       FIFTEEN-AIKG       SIXTEEN-AAPE     SEVENTEEN-AGJE");
        for(int k: k_sets){

            System.out.printf("%15d",     k);
            System.out.printf("%18s ",    numFormat.format(Sample.cycleONESample(k, graph, isInduced)));
            System.out.printf("%18s ",    numFormat.format(Sample.cycleTWOSample(k, graph, isInduced)));
            System.out.printf("%18s ",    numFormat.format(Sample.cycleTHREESample(k, graph, isInduced)));
            System.out.printf("%18s ",    numFormat.format(Sample.cycleFOURSample(k, graph, isInduced)));
            System.out.printf("%18s ",    numFormat.format(Sample.cycleFIVESample(k, graph, isInduced)));
            System.out.printf("%18s ",    numFormat.format(Sample.cycleSIXSample(k, graph, isInduced)));
            System.out.printf("%18s ",    numFormat.format(Sample.cycleSEVENSample(k, graph, isInduced)));
            System.out.printf("%18s ",    numFormat.format(Sample.cycleEIGHTSample(k, graph, isInduced)));
            System.out.printf("%18s ",    numFormat.format(Sample.cycleNINESample(k, graph, isInduced)));
            System.out.printf("%18s ",    numFormat.format(Sample.cycleTENSample(k, graph, isInduced)));
            System.out.printf("%18s ",    numFormat.format(Sample.fourPathELEVENSample(k, graph, isInduced)));
            System.out.printf("%18s ",    numFormat.format(Sample.fourPathTWELVESample(k, graph, isInduced)));
            System.out.printf("%18s ",    numFormat.format(Sample.fourPathTHIRTEENSample(k, graph, isInduced)));
            System.out.printf("%18s ",    numFormat.format(Sample.fourPathFOURTEENSample(k, graph, isInduced)));
            System.out.printf("%18s ",    numFormat.format(Sample.fourPathFIFTEENSample(k, graph, isInduced)));
            System.out.printf("%18s ",    numFormat.format(Sample.fourPathSIXTEENSample(k, graph, isInduced)));
            System.out.printf("%18s ",    numFormat.format(Sample.fourPathSEVENTEENSample(k, graph, isInduced)));


//            System.out.println();
//
//            System.out.println("wedge_map_size: " + graph.wedge_map_left.size());
//            System.out.println("key_count_size: " + BipartiteGraph.key_count.size());
//            System.out.println("Number of L nodes: " + graph.L.size()+"\tNumber of R nodes: "+graph.R.size());
//        System.out.println("Number of nodes: " + graph.map.size()+"\tNumber of edges: "+graph.edge_list.size());
            System.out.println();

        }



//        System.out.println("Sample Count: "+ 1000);
//        System.out.println("\nAlgortihm\tEst. Occurance\tExecution Time");
//        long startTime = System.nanoTime();
//        System.out.printf("FIVE-BBNM\t%s\t\t%dms\n",numFormat.format(Sample.cycleFIVESample(1000, graph, isInduced)),       (System.nanoTime()-startTime)/1000000);
//        startTime = System.nanoTime();
//        System.out.printf("SIX-IGFK\t%s\t\t%dms\n",   numFormat.format(Sample.cycleSIXSample(1000, graph, isInduced)),      (System.nanoTime()-startTime)/1000000);
//        startTime = System.nanoTime();
//        System.out.printf("SEVEN-AGJG\t%s\t\t%dms\n", numFormat.format(Sample.cycleSEVENSample(1000, graph, isInduced)),    (System.nanoTime()-startTime)/1000000);
//        startTime = System.nanoTime();
//        System.out.printf("EIGHT-AAPM\t%s\t\t%dms\n", numFormat.format(Sample.cycleEIGHTSample(1000, graph, isInduced)),(System.nanoTime()-startTime)/1000000);
//        startTime = System.nanoTime();
//        System.out.printf("NINE-ACNM\t%s\t\t%dms\n",  numFormat.format(Sample.cycleNINESample(1000, graph, isInduced)),     (System.nanoTime()-startTime)/1000000);
//        startTime = System.nanoTime();
//        System.out.printf("TEN-BBJM\t%s\t\t%dms\n",   numFormat.format(Sample.cycleTENSample(1000, graph, isInduced)),      (System.nanoTime()-startTime)/1000000);

//        System.out.println(graph.wedge_map.get("51-39").size());
//        System.out.println(graph.wedge_map.get("51-39"));
    } // main

} // Main
