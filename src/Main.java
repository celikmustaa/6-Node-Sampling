import java.text.NumberFormat;
import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) {
        NumberFormat numFormat = new DecimalFormat("0.####E0");


//        FileFormatter.toMotivo();
//        FileFormatter.toPGD();

        // TODO take graph path, cycleCount and separator as parameter

        BipartiteGraph graph = CreateGraph.createGraph();
        graph.fillEdgeFourPathCounts(graph);

        System.out.println("Number of nodes: " + graph.map.size()+"\tNumber of edges: "+graph.edge_list.size());
        System.out.printf("Size of edge_four_path_counts array: %d \n", graph.edge_four_path_counts.size());



        int[] k_sets = {10, 50, 100, 200, 500, 1000, 5000};

        System.out.println("Number of cycles: "+ Sample.CYCLE_COUNT);
        Sample.FOUR_PATH_COUNT = graph.edge_four_path_counts.get(graph.edge_four_path_counts.size()-1).four_path_count;
        System.out.println("Number of four paths: "+ Sample.FOUR_PATH_COUNT);
        System.out.println("\n         sample         ONE-NBNM           TWO-ADPM           THREE-FBNM         FOUR-ABPM          FIVE-BBNM           SIX-IGFK         SEVEN-AAPM         EIGHT-AGJG          NINE-ACNM           TEN-BBJM        ELEVEN-MBEM        TWELVE-AADO      THIRTEEN-AAHK      FOURTEEN-ACFK       FIFTEEN-AIKG       SIXTEEN-AAPE     SEVENTEEN-AGJE        RUNTIME(ms)");
        for(int k: k_sets){

            long startTime = System.currentTimeMillis();

            System.out.printf("%15d",     k);
            System.out.printf("%18s ",    numFormat.format(Sample.cycleM1Sample(k, graph)));
            System.out.printf("%18s ",    numFormat.format(Sample.cycleM2Sample(k, graph)));
            System.out.printf("%18s ",    numFormat.format(Sample.cycleM3Sample(k, graph)));
            System.out.printf("%18s ",    numFormat.format(Sample.cycleM4Sample(k, graph)));
            System.out.printf("%18s ",    numFormat.format(Sample.cycleM5Sample(k, graph)));
            System.out.printf("%18s ",    numFormat.format(Sample.cycleM6Sample(k, graph)));
            System.out.printf("%18s ",    numFormat.format(Sample.cycleM7Sample(k, graph)));
            System.out.printf("%18s ",    numFormat.format(Sample.cycleM8Sample(k, graph)));
            System.out.printf("%18s ",    numFormat.format(Sample.cycleM9Sample(k, graph)));
            System.out.printf("%18s ",    numFormat.format(Sample.cycleM10Sample(k, graph)));
            System.out.printf("%18s ",    numFormat.format(Sample.fourPathM11Sample(k, graph)));
            System.out.printf("%18s ",    numFormat.format(Sample.fourPathM12Sample(k, graph)));
            System.out.printf("%18s ",    numFormat.format(Sample.fourPathM13Sample(k, graph)));
            System.out.printf("%18s ",    numFormat.format(Sample.fourPathM14Sample(k, graph)));
            System.out.printf("%18s ",    numFormat.format(Sample.fourPathM15Sample(k, graph)));
            System.out.printf("%18s ",    numFormat.format(Sample.fourPathM16Sample(k, graph)));
            System.out.printf("%18s ",    numFormat.format(Sample.fourPathM17Sample(k, graph)));

            long endTime = System.currentTimeMillis();
            long runtime = endTime - startTime;
            System.out.printf("%18s ", runtime);

            System.out.println();

        }

    } // main

} // Main
