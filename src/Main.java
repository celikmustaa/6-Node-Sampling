import java.text.NumberFormat;
import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) {
//        FileFormatter.toMotivo();


        BipartiteGraph graph = CreateGraph.createGraph();
        graph.fillWedgeMap();

        System.out.println("Number of L nodes: " + graph.L.size()+"\tNumber of R nodes: "+graph.R.size());
        System.out.println("Number of nodes: " + graph.map.size()+"\tNumber of edges: "+graph.edge_list.size());

//        System.out.println("wedge_map: " + graph.wedge_map);
//        System.out.print("key_count: ");
//        for(BipartiteGraph.KeyCount x: graph.key_count){
//            System.out.println("[" + x.key +", " + x.cycle_count +  "]\t");
//        }
//        System.out.println();



        int[] k_sets = {50, 100, 200, 500};

        boolean isInduced = args[0].equals("true");
        System.out.println("isInduced: "+ isInduced);

        NumberFormat numFormat = new DecimalFormat("0.####E0");

        System.out.println("\n         sample          SIX-IGFK         SEVEN-AGJG         EIGHT-AAPM          NINE-ACNM           TEN-BBJM");
        for(int k: k_sets){

            System.out.printf("%15d",       k);
            System.out.printf("%18s ",    numFormat.format(Sample.cycleSIXSample(k, graph, isInduced)));
            System.out.printf("%18s ",    numFormat.format(Sample.cycleSEVENSample(k, graph, isInduced)));
            System.out.printf("%18s ",    numFormat.format(Sample.cycleEIGHTSample(k, graph, isInduced)));
            System.out.printf("%18s ",    numFormat.format(Sample.cycleNINESample(k, graph, isInduced)));
            System.out.printf("%18s ",    numFormat.format(Sample.cycleTENSample(k, graph, isInduced)));

            System.out.println();

        }

//        System.out.println(graph.wedge_map.get("51-39").size());
//        System.out.println(graph.wedge_map.get("51-39"));
    } // main

} // Main


30 x 30

C 30 x 1
N 30 x 1