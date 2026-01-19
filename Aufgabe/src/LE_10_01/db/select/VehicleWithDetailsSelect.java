package LE_10_01.db.select;

import LE_10_01.db.DbRunner;

public class VehicleWithDetailsSelect {

    public static void main(String[] args) {

        String sql = """
                SELECT v.vehicle_id, v.vehicle_type, v.license_plate, v.brand, v.production_year,
                       c.number_of_seats,
                       t.max_load_kg,
                       m.engine_capacity_ccm
                FROM vehicle v
                LEFT JOIN car c ON c.vehicle_id = v.vehicle_id
                LEFT JOIN truck t ON t.vehicle_id = v.vehicle_id
                LEFT JOIN motorcycle m ON m.vehicle_id = v.vehicle_id
                ORDER BY v.vehicle_id
                """;

        String header = String.format("| %-3s | %-10s | %-10s | %-10s | %-4s | %-18s |",
                "ID", "TYPE", "PLATE", "BRAND", "YEAR", "DETAILS");

        DbRunner.print("VEHICLES + DETAILS", header, sql, rs -> {
            String type = rs.getString("vehicle_type");

            String details = switch (type) {
                case "CAR" -> "seats=" + rs.getInt("number_of_seats");
                case "TRUCK" -> "maxLoadKg=" + rs.getDouble("max_load_kg");
                case "MOTORCYCLE" -> "engineCcm=" + rs.getInt("engine_capacity_ccm");
                default -> "-";
            };

            return String.format("| %3d | %-10s | %-10s | %-10s | %4d | %-18s |",
                    rs.getInt("vehicle_id"),
                    type,
                    rs.getString("license_plate"),
                    rs.getString("brand"),
                    rs.getInt("production_year"),
                    details
            );
        });
    }
}
