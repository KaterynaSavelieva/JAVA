package LE_10_01.db.util;

// Class for database connection
import LE_10_01.db.DBConnection;

// JDBC classes
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DbRunner {

    // Functional interface to map one row of ResultSet to a String
    @FunctionalInterface
    public interface RowMapper {
        // Converts one database row into a text line
        String map(ResultSet rs) throws Exception;
    }

    // Executes a SELECT query and prints the result to the console
    public static void print(String title,
                             String header,
                             String sql,
                             RowMapper mapper) {
        System.out.println("\n================ " + title + " ================");
        System.out.println(header);
        System.out.println("-".repeat(header.length()));

        // Try-with-resources closes connection, statement and result set automatically
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            // Read each row from the ResultSet
            while (rs.next()) {
                // Convert row to text and print it
                System.out.println(mapper.map(rs));
            }
            // Print table footer
            System.out.println("-".repeat(header.length()));

        } catch (Exception e) {
            // Print error message if something goes wrong
            System.out.println(title + " failed");
            // Print stack trace for debugging
            e.printStackTrace();
        }
    }
}
