
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Connection connect() {
        String url = "jdbc:sqlite:D:\\Mines\\Mines\\src\\savedgames.db";
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Connection was successful!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

    public static void insertSavedGame(String name, File binaryFile) {
        String sql = "INSERT INTO savedgames (name, data) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.connect(); PreparedStatement pstmt = conn.prepareStatement(sql); FileInputStream fis = new FileInputStream(binaryFile)) {

            pstmt.setString(1, name);
            pstmt.setBinaryStream(2, fis, (int) binaryFile.length());

            pstmt.executeUpdate();
        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<String> getSavedGamesList() {
        List<String> savedGames = new ArrayList<>();

        String sql = "SELECT name FROM savedgames";

        try (Connection conn = DatabaseConnection.connect(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                savedGames.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return savedGames;
    }

    public static Object loadGameFromDatabase(String gameName) {
        String sql = "SELECT data FROM savedgames WHERE name = ?";
        Object loadedGame = null;

        try (Connection conn = DatabaseConnection.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, gameName);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    byte[] gameData = rs.getBytes("data");
                    ByteArrayInputStream bais = new ByteArrayInputStream(gameData);
                    ObjectInputStream ois = new ObjectInputStream(bais);
                    loadedGame = ois.readObject();
                    ois.close();
                }
            }
        } catch (SQLException | IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return loadedGame;
    }

}
