package LE_10_01.db.dao;

import LE_10_01.db.util.DbRunner;
import LE_10_01.db.util.DbWrite;

import java.sql.Connection;
import java.sql.SQLException;

public class VehicleDao {

    public void printAllVehicles() {
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

    public void printVehiclesWithDetails() {
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

    public int insertVehicleWithSubtype(
            String vehicleType,
            String plate,
            String brand,
            int year,
            String energyType,
            String energyUnit,
            double consumption,
            int mileage,
            double level,
            Integer seats,
            Double maxLoadKg,
            Integer engineCcm
    ) {
        return DbWrite.inTransaction(conn -> {
            int vid = insertVehicle(conn, vehicleType, plate, brand, year,
                    energyType, energyUnit, consumption, mileage, level);

            insertSubtype(conn, vehicleType, vid, seats, maxLoadKg, engineCcm);
            return vid;
        });
    }

    private int insertVehicle(Connection conn,
                              String vehicleType,
                              String plate,
                              String brand,
                              int year,
                              String energyType,
                              String energyUnit,
                              double consumption,
                              int mileage,
                              double level) throws SQLException {

        String sql = """
                INSERT INTO vehicle
                (vehicle_type, license_plate, brand, production_year,
                 energy_type, energy_unit, energy_consumption, mileage, energy_level)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        return DbWrite.insertAndGetId(conn, sql, ps -> {
            ps.setString(1, vehicleType);
            ps.setString(2, plate);
            ps.setString(3, brand);
            ps.setInt(4, year);
            ps.setString(5, energyType);
            ps.setString(6, energyUnit);
            ps.setDouble(7, consumption);
            ps.setInt(8, mileage);
            ps.setDouble(9, level);
        });
    }

    private void insertSubtype(Connection conn,
                               String vehicleType,
                               int vehicleId,
                               Integer seats,
                               Double maxLoadKg,
                               Integer engineCcm) throws SQLException {

        switch (vehicleType) {
            case "CAR" -> DbWrite.executeUpdate(conn,
                    "INSERT INTO car (vehicle_id, number_of_seats) VALUES (?, ?)",
                    ps -> {
                        ps.setInt(1, vehicleId);
                        ps.setInt(2, seats);
                    });

            case "TRUCK" -> DbWrite.executeUpdate(conn,
                    "INSERT INTO truck (vehicle_id, max_load_kg) VALUES (?, ?)",
                    ps -> {
                        ps.setInt(1, vehicleId);
                        ps.setDouble(2, maxLoadKg);
                    });

            case "MOTORCYCLE" -> DbWrite.executeUpdate(conn,
                    "INSERT INTO motorcycle (vehicle_id, engine_capacity_ccm) VALUES (?, ?)",
                    ps -> {
                        ps.setInt(1, vehicleId);
                        ps.setInt(2, engineCcm);
                    });

            default -> throw new SQLException("Unknown vehicle type: " + vehicleType);
        }
    }
}
