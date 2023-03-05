import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Database {
    // TODO hide these confidential information
    private static final String url = "jdbc:postgresql://34.76.224.231:5432/motifs";
    private static final String user = "musty";
    private static final String password = "musty123";
    public static Connection conn = null;

    public static PreparedStatement insertNode;
    public static PreparedStatement insertEdge;
    public static PreparedStatement getMaxNodeId;
    public static PreparedStatement getAdjacencyList;
    public static PreparedStatement insertWedge;
    public static PreparedStatement queryWedge;
    public static PreparedStatement fillKeyCount;
    public static PreparedStatement getDegreeOfNode;

    public static Connection connect() {
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
            insertEdge = conn.prepareStatement("INSERT INTO edges (left_id, right_id) VALUES (?, ?) ON CONFLICT (left_id, right_id) DO NOTHING;");
            insertNode = conn.prepareStatement("INSERT INTO nodes (id, degree) VALUES (?, (SELECT COUNT(*) from edges where left_id = ? or right_id = ?)) ON CONFLICT (id) DO NOTHING;");
            getMaxNodeId = conn.prepareStatement("SELECT MAX(id) FROM nodes AS max;");
            getAdjacencyList = conn.prepareStatement("SELECT CASE WHEN ? > 0 THEN right_id ELSE left_id END AS id FROM edges WHERE left_id = ? OR right_id = ?;");
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


        } catch (Exception e) {
            System.out.println("Failed to create JDBC db connection " + e.getMessage());
        }
        return conn;
    }
}
