package LE_10_01.db;

import java.sql.*;
import java.util.Scanner;

public class VehicleInsert {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1) TYPE
        String vehicleType = readEnum(scanner,
                "Vehicle type (CAR/TRUCK/MOTORCYCLE): ",
                new String[]{"CAR", "TRUCK", "MOTORCYCLE"});

        // 2) Common fields for table vehicle
        System.out.print("License plate: ");
        String plate = scanner.nextLine().trim();

        System.out.print("Brand: ");
        String brand = scanner.nextLine().trim();

        int year = readPositiveInt(scanner, "Production year (e.g. 2019): ");

        String energyType = readEnum(scanner,
                "Energy type (PETROL/DIESEL/ELECTRIC/HYBRID): ",
                new String[]{"PETROL", "DIESEL", "ELECTRIC", "HYBRID"});

        // Energy unit (simple rule: ELECTRIC => KWH, else => LITER)
        String energyUnit = energyType.equals("ELECTRIC") ? "KWH" : "LITER";
        System.out.println("Energy unit auto-set to: " + energyUnit);

        double consumption = readNonNegativeDouble(scanner, "Energy consumption per 100km: ");
        int mileage = readNonNegativeInt(scanner, "Mileage (km): ");
        double level = readNonNegativeDouble(scanner, "Energy level (" + energyUnit + "): ");

        // 3) Subtype detail
        Integer seats = null;
        Double maxLoadKg = null;
        Integer engineCcm = null;

        switch (vehicleType) {
            case "CAR" -> seats = readPositiveInt(scanner, "Number of seats: ");
            case "TRUCK" -> maxLoadKg = readNonNegativeDouble(scanner, "Max load (kg): ");
            case "MOTORCYCLE" -> engineCcm = readPositiveInt(scanner, "Engine capacity (ccm): ");
        }

        // === INSERT WITH TRANSACTION ===
        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);

            try {
                int vehicleId = insertVehicle(conn, vehicleType, plate, brand, year,
                        energyType, energyUnit, consumption, mileage, level);

                insertSubtype(conn, vehicleType, vehicleId, seats, maxLoadKg, engineCcm);

                conn.commit();
                System.out.println("Insert successful  vehicle_id = " + vehicleId);

            } catch (Exception e) {
                conn.rollback();
                System.out.println("Insert failed (rolled back)");
                e.printStackTrace();
            }

        } catch (Exception e) {
            System.out.println("DB connection failed ❌");
            e.printStackTrace();
        }
    }

    // ---------- INSERTS ----------

    private static int insertVehicle(Connection conn,
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

        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, vehicleType);
            ps.setString(2, plate);
            ps.setString(3, brand);
            ps.setInt(4, year);

            ps.setString(5, energyType);
            ps.setString(6, energyUnit);

            ps.setDouble(7, consumption);
            ps.setInt(8, mileage);
            ps.setDouble(9, level);

            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) return keys.getInt(1);
            }
        }

        throw new SQLException("No generated key for vehicle");
    }

    private static void insertSubtype(Connection conn,
                                      String vehicleType,
                                      int vehicleId,
                                      Integer seats,
                                      Double maxLoadKg,
                                      Integer engineCcm) throws SQLException {

        switch (vehicleType) {
            case "CAR" -> {
                String sql = "INSERT INTO car (vehicle_id, number_of_seats) VALUES (?, ?)";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, vehicleId);
                    ps.setInt(2, seats);
                    ps.executeUpdate();
                }
            }
            case "TRUCK" -> {
                String sql = "INSERT INTO truck (vehicle_id, max_load_kg) VALUES (?, ?)";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, vehicleId);
                    ps.setDouble(2, maxLoadKg);
                    ps.executeUpdate();
                }
            }
            case "MOTORCYCLE" -> {
                String sql = "INSERT INTO motorcycle (vehicle_id, engine_capacity_ccm) VALUES (?, ?)";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, vehicleId);
                    ps.setInt(2, engineCcm);
                    ps.executeUpdate();
                }
            }
            default -> throw new SQLException("Unknown vehicle type: " + vehicleType);
        }
    }

    // ---------- INPUT HELPERS ----------

    private static String readEnum(Scanner scanner, String msg, String[] allowed) {
        while (true) {
            System.out.print(msg);
            String s = scanner.nextLine().trim().toUpperCase();

            for (String a : allowed) {
                if (a.equals(s)) return s;
            }
            System.out.println("Invalid value. Allowed: " + String.join("/", allowed));
        }
    }

    private static int readPositiveInt(Scanner scanner, String msg) {
        while (true) {
            System.out.print(msg);
            String s = scanner.nextLine().trim();
            try {
                int v = Integer.parseInt(s);
                if (v > 0) return v;
            } catch (Exception ignored) {}
            System.out.println("Please enter a positive integer (>0).");
        }
    }

    private static int readNonNegativeInt(Scanner scanner, String msg) {
        while (true) {
            System.out.print(msg);
            String s = scanner.nextLine().trim();
            try {
                int v = Integer.parseInt(s);
                if (v >= 0) return v;
            } catch (Exception ignored) {}
            System.out.println("Please enter a non-negative integer (>=0).");
        }
    }

    private static double readNonNegativeDouble(Scanner scanner, String msg) {
        while (true) {
            System.out.print(msg);
            String s = scanner.nextLine().trim().replace(",", ".");
            try {
                double v = Double.parseDouble(s);
                if (v >= 0) return v;
            } catch (Exception ignored) {}
            System.out.println("Please enter a non-negative number (>=0). Example: 12.5");
        }
    }
}
