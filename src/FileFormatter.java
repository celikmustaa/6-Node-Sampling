import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class FileFormatter {
//    public static String input_path = "C:\\Users\\musta\\Desktop\\Hacettepe\\DREAM\\download.tsv.edit-aawiki\\edit-aawiki\\out.edit-aawiki";
    public static String input_path = "C:\\Users\\musta\\Desktop\\Hacettepe\\DREAM\\download.tsv.edit-novwiki\\edit-novwiki\\out.edit-novwiki";
    public static String output_path = "out.txt";

    public static void toMotivo(){
        BipartiteGraph graph = new BipartiteGraph();

        try {
            BufferedReader br = new BufferedReader((new FileReader(input_path)));

            String line;
            int counter = 0;
            while ((line = br.readLine()) != null) {
                String[] splitted = line.split("\t");
                int left_id = Integer.parseInt(splitted[0]);
                int right_id = (-1) * Integer.parseInt(splitted[1]);
                graph.connect(left_id, right_id);

                if (counter++ % 1000 == 0) {
                    System.out.println(counter);
                }
            }

            counter = 0;
            HashMap<Integer, Integer> map_to_motivo = new HashMap<>();             // map_id: motivo_id
            for(int map_id: graph.map.keySet()){
                if (!map_to_motivo.containsKey(map_id)){
                    map_to_motivo.put(map_id, counter++);
                }
            }

            FileWriter myWriter = new FileWriter(output_path);
            myWriter.write(graph.map.size() + " " + graph.edge_list.size() + "\n");
            for(int i = 0; i < graph.edge_list.size() - 1; i++){
                ArrayList<Integer> edge = graph.edge_list.get(i);
                myWriter.write(map_to_motivo.get(edge.get(0)) + " " + map_to_motivo.get(edge.get(1)) + "\n");
                myWriter.write(map_to_motivo.get(edge.get(1)) + " " + map_to_motivo.get(edge.get(0)) + "\n");
            }
            ArrayList<Integer> edge = graph.edge_list.get(graph.edge_list.size() - 1);
            myWriter.write(map_to_motivo.get(edge.get(0)) + " " + map_to_motivo.get(edge.get(1)) + "\n");
            myWriter.write(map_to_motivo.get(edge.get(1)) + " " + map_to_motivo.get(edge.get(0)) );

            br.close();
            myWriter.close();
        } catch (Exception e) {
            System.out.println("An error occurred in createGraph");
            e.printStackTrace();
        }

    }
}
