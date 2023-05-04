import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.SQLException;

public class CreateGraph {
    //public static String path = "C:\\Users\\musta\\Desktop\\Hacettepe\\DREAM\\brunson_southern-women\\out.brunson_southern-women_southern-women";
    //public static String path = "C:\\Users\\musta\\Desktop\\Hacettepe\\DREAM\\amazon-ratings\\out.txt";
    //public static String path = "C:\\Users\\musta\\Desktop\\Hacettepe\\DREAM\\download.tsv.edit-aawiki\\edit-aawiki\\out.edit-aawiki";
//  public static String path = "C:\\Users\\musta\\Desktop\\Hacettepe\\DREAM\\download.tsv.edit-novwiki\\edit-novwiki\\out.edit-novwiki";
    public static String path = "C:\\Users\\musta\\Desktop\\Hacettepe\\DREAM\\final networks\\bag-nips\\out.bag-nips";


    public static BipartiteGraph graph = new BipartiteGraph();


    public static BipartiteGraph createGraph() {
        try {
            BufferedReader br = new BufferedReader((new FileReader(path)));

            String line;
            int counter = 0;
            while ((line = br.readLine()) != null) {
                String[] splitted = line.split("\t"); // TODO tab to space
                int left_id = Integer.parseInt(splitted[0]);
                int right_id = (-1) * Integer.parseInt(splitted[1]);
                graph.connect(left_id, right_id);
            }
            br.close();
        } catch (Exception e) {
            System.out.println("An error occurred in createGraph");
            e.printStackTrace();
        }

        return graph;
    }

    public static void insertEdges() {
        try {
            BufferedReader br = new BufferedReader((new FileReader(path)));

            String line;
            int counter = 0;
            while ((line = br.readLine()) != null) {
                String[] splitted = line.split("\t"); // TODO tab to space
                int left_id = Integer.parseInt(splitted[0]);
                int right_id = (-1) * Integer.parseInt(splitted[1]);
                graph.addEdge(left_id, right_id);

                if (counter++ % 1000 == 0) {
                   Database.insertEdge.executeBatch();
                }
            }
            Database.insertEdge.executeBatch();
            br.close();
        } catch (Exception e) {
            System.out.println("An error occurred in insertEdges");
            e.printStackTrace();
        }

    }

    public static void insertNodes() {
        try {
            BufferedReader br = new BufferedReader((new FileReader(path)));

            String line;
            int counter = 0;
            while ((line = br.readLine()) != null) {
                String[] splitted = line.split("\t"); // TODO tab to space
                int left_id = Integer.parseInt(splitted[0]);
                int right_id = (-1) * Integer.parseInt(splitted[1]);
                graph.addNodes(left_id, right_id);

                // TODO execute works when 1000 line is read. Not when 1000 new node is added
                if (counter++ % 1000 == 0) {
                    Database.insertNode.executeBatch();
                }
            }
            Database.insertNode.executeBatch();
            br.close();
        } catch (Exception e) {
            System.out.println("An error occurred in insertNodes");
            e.printStackTrace();
        }

    }

    public static void setDegrees(){
        try {
            Database.setDegrees.addBatch();
            Database.setDegrees.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
