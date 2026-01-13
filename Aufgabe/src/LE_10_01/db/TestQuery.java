package LE_10_01.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TestQuery {
    public static void main(String[] args) {
        String sql = "SELECT 1";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                System.out.println("DB answered: " + rs.getInt(1) + " ✅");
            }

        } catch (Exception e) {
            System.out.println("Query failed ❌");
            e.printStackTrace();
        }
    }
}
