import java.io.BufferedReader;
import java.io.FileReader;

public class CreateGraph {
    //public static String path = "C:\\Users\\musta\\Desktop\\Hacettepe\\DREAM\\brunson_southern-women\\out.brunson_southern-women_southern-women";
    //public static String path = "C:\\Users\\musta\\Desktop\\Hacettepe\\DREAM\\amazon-ratings\\out.txt";
    //public static String path = "C:\\Users\\musta\\Desktop\\Hacettepe\\DREAM\\download.tsv.edit-aawiki\\edit-aawiki\\out.edit-aawiki";
    public static String path = "C:\\Users\\musta\\Desktop\\Hacettepe\\DREAM\\download.tsv.edit-novwiki\\edit-novwiki\\out.edit-novwiki";

    public static BipartiteGraph createGraph() {

        BipartiteGraph graph = new BipartiteGraph();

        try {
            BufferedReader br = new BufferedReader((new FileReader(path)));

            String line;
            int counter = 0;
            while ((line = br.readLine()) != null) {
                String[] splitted = line.split("\t"); // TODO tab to space
                int left_id = Integer.parseInt(splitted[0]);
                int right_id = (-1) * Integer.parseInt(splitted[1]);
                graph.connect(left_id, right_id);

//                if (counter++ % 1000 == 0) {
//                    System.out.println(counter);
//                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("An error occurred in createGraph");
            e.printStackTrace();
        }

        return graph;
    }

}
