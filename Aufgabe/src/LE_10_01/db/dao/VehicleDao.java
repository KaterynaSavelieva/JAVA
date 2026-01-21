package LE_10_01.db.dao;

import LE_10_01.db.DBConnection;
import LE_10_01.db.util.DbRunner;
import LE_10_01.db.util.DbWrite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/* DAO for vehicle-related database operations.
    - Read vehicles (SELECT)
    - Insert vehicle + subtype (INSERT in transaction) */
public class VehicleDao {

    /* Prints a simple list of vehicles (basic data only).     */
    public void printAllVehicles() {

        // Simple SELECT from table vehicle
        String sql = """
                SELECT vehicle_id, vehicle_type, license_plate, brand, production_year
                FROM vehicle
                ORDER BY vehicle_id
                """;

        // Header for console output
        String header = String.format("| %-3s | %-10s | %-10s | %-10s | %-4s |",
                "ID", "TYPE", "PLATE", "BRAND", "YEAR");

        // Run SELECT and print each row
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

    /* Prints vehicles with subtype details (car seats / truck load / motorcycle ccm).
      Uses a VIEW that already joins vehicle + car/truck/motorcycle.     */
    public void printVehiclesWithDetails() {

        // Select from VIEW
        String sql = """
                SELECT vehicle_id, vehicle_type, license_plate, brand, production_year,
                       number_of_seats,
                       max_load_kg,
                       engine_capacity_ccm
                FROM all_vehicles_view
                ORDER BY vehicle_id
                """;

        // Header for console output
        String header = String.format("| %-3s | %-10s | %-10s | %-10s | %-4s | %-18s |",
                "ID", "TYPE", "PLATE", "BRAND", "YEAR", "DETAILS");

        // Run SELECT and build "details" text depending on vehicle type
        DbRunner.print("VEHICLES + DETAILS", header, sql, rs -> {
            String type = rs.getString("vehicle_type");

            // Build different details depending on the subtype
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

    /* Inserts a new vehicle with its subtype (CAR/TRUCK/MOTORCYCLE).
       This needs TWO inserts:
        1) Insert into vehicle (base table)
        2) Insert into subtype table (car/truck/motorcycle)
       We do both in ONE transaction.     */
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

        // Transaction makes sure: either both inserts succeed, or none
        return DbWrite.inTransaction(conn -> {

            // 1) Insert base vehicle row -> get generated vehicle_id
            int vid = insertVehicle(conn, vehicleType, plate, brand, year,
                    energyType, energyUnit, consumption, mileage, level);

            // 2) Insert subtype row using the same vehicle_id
            insertSubtype(conn, vehicleType, vid, seats, maxLoadKg, engineCcm);

            // Return the new vehicle_id
            return vid;
        });
    }

    /* Inserts the base vehicle row (common fields for all vehicles).
      Returns generated vehicle_id.     */
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

        // INSERT into main table vehicle
        String sql = """
                INSERT INTO vehicle
                (vehicle_type, license_plate, brand, production_year,
                 energy_type, energy_unit, energy_consumption, mileage, energy_level)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        // insertAndGetId returns AUTO_INCREMENT id (vehicle_id)
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

    /*Inserts data into the subtype table.
     Only ONE of these tables is used depending on vehicleType: car, truck, motorcycle     */
    private void insertSubtype(Connection conn,
                               String vehicleType,
                               int vehicleId,
                               Integer seats,
                               Double maxLoadKg,
                               Integer engineCcm) throws SQLException {

        // --- Safety checks ---
        if ("CAR".equals(vehicleType) && seats == null) {
            throw new IllegalArgumentException("CAR must have number of seats");
        }

        if ("TRUCK".equals(vehicleType) && maxLoadKg == null) {
            throw new IllegalArgumentException("TRUCK must have max load (kg)");
        }

        if ("MOTORCYCLE".equals(vehicleType) && engineCcm == null) {
            throw new IllegalArgumentException("MOTORCYCLE must have engine capacity (ccm)");
        }


        switch (vehicleType) {

            // If CAR -> insert into car table
            case "CAR" -> DbWrite.executeUpdate(conn,
                    "INSERT INTO car (vehicle_id, number_of_seats) VALUES (?, ?)",
                    ps -> {
                        ps.setInt(1, vehicleId);
                        ps.setInt(2, seats); // must not be null for CAR
                    });

            // If TRUCK -> insert into truck table
            case "TRUCK" -> DbWrite.executeUpdate(conn,
                    "INSERT INTO truck (vehicle_id, max_load_kg) VALUES (?, ?)",
                    ps -> {
                        ps.setInt(1, vehicleId);
                        ps.setDouble(2, maxLoadKg); // must not be null for TRUCK
                    });

            // If MOTORCYCLE -> insert into motorcycle table
            case "MOTORCYCLE" -> DbWrite.executeUpdate(conn,
                    "INSERT INTO motorcycle (vehicle_id, engine_capacity_ccm) VALUES (?, ?)",
                    ps -> {
                        ps.setInt(1, vehicleId);
                        ps.setInt(2, engineCcm); // must not be null for MOTORCYCLE
                    });

            default -> throw new SQLException("Unknown vehicle type: " + vehicleType);
        }
    }

    public boolean existsById(int vehicleId) {
        String sql = "SELECT 1 FROM vehicle WHERE  vehicle_id =? ";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1,vehicleId);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // true = found, false = not found
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}
