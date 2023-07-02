import java.io.BufferedReader;
import java.io.FileReader;


public class CreateGraph {
    //public static String path = "C:\\Users\\musta\\Desktop\\Hacettepe\\DREAM\\brunson_southern-women\\out.brunson_southern-women_southern-women";
    //public static String path = "C:\\Users\\musta\\Desktop\\Hacettepe\\DREAM\\amazon-ratings\\out.txt";
    //public static String path = "C:\\Users\\musta\\Desktop\\Hacettepe\\DREAM\\download.tsv.edit-aawiki\\edit-aawiki\\out.edit-aawiki";
//  public static String path = "C:\\Users\\musta\\Desktop\\Hacettepe\\DREAM\\download.tsv.edit-novwiki\\edit-novwiki\\out.edit-novwiki";
    public static String path = "C:\\Users\\musta\\Desktop\\Hacettepe\\DREAM\\final networks\\bag-nips\\out.bag-nips";
//    public static String path = "C:\\Users\\musta\\Desktop\\Hacettepe\\DREAM\\final networks\\komarix-citeseer\\out.komarix-citeseer";
//    public static String path = "C:\\Users\\musta\\Desktop\\Hacettepe\\DREAM\\final networks\\amazon-ratings\\out.amazon-ratings";
//    public static String path = "C:\\Users\\musta\\Desktop\\Hacettepe\\DREAM\\final networks\\edit-jawiki\\out.edit-jawiki"; // tab
//    public static String path = "C:\\Users\\musta\\Desktop\\Hacettepe\\DREAM\\final networks\\livejournal-groupmemberships\\out.livejournal-groupmemberships"; // space

    public static String separator = "\t";
    public static BipartiteGraph graph = new BipartiteGraph();


    public static BipartiteGraph createGraph() {
        try {
            BufferedReader br = new BufferedReader((new FileReader(path)));

            String line;
            long counter = 0;
            while ((line = br.readLine()) != null) {
                String[] splitted = line.split(separator);
                int left_id = Integer.parseInt(splitted[0]);
                int right_id = (-1) * Integer.parseInt(splitted[1]);
                graph.connect(left_id, right_id);
                counter += 1;
                if (counter % 10000 == 0){
                    System.out.println("Reading line "+ counter + " of the input file.");
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("An error occurred in createGraph");
            e.printStackTrace();
        }

        return graph;
    }

}
