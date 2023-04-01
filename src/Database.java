import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Database {
    // TODO hide these confidential information
    private static final String url = "jdbc:postgresql://localhost/dream";
    private static final String user = "postgres";
    private static final String password = "postgres";
    public static Connection conn = null;

    public static PreparedStatement insertEdge;
    public static PreparedStatement insertNode;
    public static PreparedStatement setDegrees;
    public static PreparedStatement getMaxNodeId;
    public static PreparedStatement getAdjacencyList;
    public static PreparedStatement insertWedge;
    public static PreparedStatement queryWedge;
    public static PreparedStatement fillKeyCount;
    public static PreparedStatement getDegreeOfNode;
    public static PreparedStatement getWedgeCount;
    public static PreparedStatement setCumulativeCycleCount;






    public static Connection connect() {
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");

            insertNode = conn.prepareStatement("INSERT INTO nodes (id, adjacency_list)\n" +
                    "VALUES (?, array(SELECT CASE\n" +
                    "                            WHEN ? > 0 THEN right_id\n" +
                    "                            ELSE left_id\n" +
                    "                            END AS id\n" +
                    "                 FROM edges\n" +
                    "                 WHERE left_id = ? OR right_id = ?))" +
                    "ON CONFLICT (id)\n" +
                    "    DO NOTHING;");
            insertEdge = conn.prepareStatement("INSERT INTO edges (left_id, right_id) VALUES (?, ?) ON CONFLICT (left_id, right_id) DO NOTHING;");
            setDegrees = conn.prepareStatement("UPDATE nodes\n" +
                    "SET degree = array_length(adjacency_list, 1)\n" +
                    "WHERE true;");
            getMaxNodeId = conn.prepareStatement("SELECT MAX(id) FROM nodes AS max;");
            getAdjacencyList = conn.prepareStatement("SELECT adjacency_list\n" +
                    "FROM nodes\n" +
                    "WHERE id = ?;");
            insertWedge = conn.prepareStatement("INSERT INTO wedge_map (key, middles) " +
                    "VALUES (?, ARRAY [?]) " +
                    "ON CONFLICT (key) DO " +
                    "UPDATE SET middles = array_append(wedge_map.middles, ?) " +
                    "WHERE wedge_map.key = ?; ");
            queryWedge = conn.prepareStatement("SELECT middles FROM wedge_map WHERE key = ? ;");
            fillKeyCount = conn.prepareStatement("SELECT key, array_length(middles, 1) * (array_length(middles, 1)-1) / 2 AS count " +
                    "FROM wedge_map " +
                    "WHERE array_length(middles, 1) > 1; ");
            getDegreeOfNode = conn.prepareStatement("SELECT degree FROM nodes WHERE id = ?;");
            getWedgeCount = conn.prepareStatement("SELECT COUNT(key) FROM wedge_map AS count;");
            setCumulativeCycleCount = conn.prepareStatement("UPDATE wedge_map\n" +
                    "SET cumulative_cycle_count = array_length(middles, 1) * (array_length(middles, 1)-1) / 2 + COALESCE((SELECT cumulative_cycle_count FROM wedge_map wm\n" +
                    "                                                                                            WHERE wm.key_index = ? - 1), 0)\n" +
                    "WHERE key_index = (?);");



        } catch (Exception e) {
            System.out.println("Failed to create JDBC db connection " + e.getMessage());
        }
        return conn;
    }
}
