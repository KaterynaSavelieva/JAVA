package LE_10_01.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class VehicleSelect {

    public static void main(String[] args) {

        String sql = """
                SELECT
                    vehicle_id,
                    vehicle_type,
                    license_plate,
                    brand,
                    production_year,
                    mileage,
                    energy_level
                FROM vehicle
                ORDER BY vehicle_id
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            printHeader();

            while (rs.next()) {
                printRow(
                        rs.getInt("vehicle_id"),
                        rs.getString("vehicle_type"),
                        rs.getString("license_plate"),
                        rs.getString("brand"),
                        rs.getInt("production_year"),
                        rs.getInt("mileage"),
                        rs.getDouble("energy_level")
                );
            }

            printFooter();

        } catch (Exception e) {
            System.out.println("Vehicle query failed ");
            e.printStackTrace();
        }
    }

    // ===== PRINT HELPERS =====

    private static void printHeader() {
        printLine();
        System.out.printf("| %-3s | %-10s | %-10s | %-10s | %-4s | %-7s | %-7s |\n",
                "ID", "TYPE", "PLATE", "BRAND", "YEAR", "KM", "LEVEL");
        printLine();
    }

    private static void printRow(int id, String type, String plate,
                                 String brand, int year, int km, double level) {

        System.out.printf("| %3d | %-10s | %-10s | %-10s | %4d | %7d | %7.2f |\n",
                id, type, plate, brand, year, km, level);
    }

    private static void printFooter() {
        printLine();
    }

    private static void printLine() {
        System.out.println("-".repeat(74));
    }
}
