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


        Database.connect();
//        CreateGraph.insertEdges();
//       CreateGraph.insertNodes();
        BipartiteGraph.fillWedgeMapTable();
//        BipartiteGraph.fillKeyCountFromSQL();
//
//        System.out.println("\n\n");
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        System.out.printf("%18s \n",    numFormat.format(Sample.cycleTENSampleSQL(10, isInduced)));



        // OLD VERSION (WITHOUT SQL)
//        BipartiteGraph graph = CreateGraph.createGraph();
//        graph.fillWedgeMap();
////
////
////
//        System.out.println("Number of L nodes: " + graph.L.size()+"\tNumber of R nodes: "+graph.R.size());
//        System.out.println("Number of nodes: " + graph.map.size()+"\tNumber of edges: "+graph.edge_list.size());
////
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



        int[] k_sets = {50, 100, 200, 500};
//
//        System.out.println("\n         sample          FIVE-BBNM          SIX-IGFK         SEVEN-AGJG         EIGHT-AAPM          NINE-ACNM           TEN-BBJM");
//        for(int k: k_sets){
//
//            System.out.printf("%15d",     k);
//            System.out.printf("%18s ",    numFormat.format(Sample.cycleFIVESample(k, graph, isInduced)));
//            System.out.printf("%18s ",    numFormat.format(Sample.cycleSIXSample(k, graph, isInduced)));
//            System.out.printf("%18s ",    numFormat.format(Sample.cycleSEVENSample(k, graph, isInduced)));
//            System.out.printf("%18s ",    numFormat.format(Sample.cycleEIGHTSample(k, graph, isInduced)));
//            System.out.printf("%18s ",    numFormat.format(Sample.cycleNINESample(k, graph, isInduced)));
//            System.out.printf("%18s ",    numFormat.format(Sample.cycleTENSample(k, graph, isInduced)));
//
//            System.out.println();
//
//        }



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
