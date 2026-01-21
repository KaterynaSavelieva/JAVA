package LE_10_01.db.util;

import LE_10_01.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DbRunner {

    @FunctionalInterface
    public interface RowMapper {
        String map(ResultSet rs) throws Exception;
    }

    public static void print(String title, String header, String sql, RowMapper mapper) {
        System.out.println("\n================ " + title + " ================");
        System.out.println(header);
        System.out.println("-".repeat(header.length()));

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                System.out.println(mapper.map(rs));
            }
            System.out.println("-".repeat(header.length()));

        } catch (Exception e) {
            System.out.println(title + " failed ❌");
            e.printStackTrace();
        }
    }
}
