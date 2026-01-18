package LE_10_01.db;

import LE_10_01.app.DbRunner;

public class VehicleSelect {

    public static void main(String[] args) {

        String sql = """
                SELECT vehicle_id, vehicle_type, license_plate, brand, production_year
                FROM vehicle
                ORDER BY vehicle_id
                """;

        String header = String.format("| %-3s | %-10s | %-10s | %-10s | %-4s |",
                "ID", "TYPE", "PLATE", "BRAND", "YEAR");

        DbRunner.print("VEHICLES", header, sql, rs ->
                String.format("| %3d | %-10s | %-10s | %-10s | %4d |",
                        rs.getInt("vehicle_id"),
                        rs.getString("vehicle_type"),
                        rs.getString("license_plate"),
                        rs.getString("brand"),
                        rs.getInt("production_year")
                )
        );
    }
}
