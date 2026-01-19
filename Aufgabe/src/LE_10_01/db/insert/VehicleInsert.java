package LE_10_01.db.insert;

import LE_10_01.db.DbWrite;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class VehicleInsert {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== INSERT NEW VEHICLE ===");

        final String vehicleType = readEnum(scanner,
                "Vehicle type (CAR/TRUCK/MOTORCYCLE): ",
                new String[]{"CAR", "TRUCK", "MOTORCYCLE"});

        System.out.print("License plate: ");
        final String plate = scanner.nextLine().trim();

        System.out.print("Brand: ");
        final String brand = scanner.nextLine().trim();

        final int year = readPositiveInt(scanner, "Production year (e.g. 2019): ");

        final String energyType = readEnum(scanner,
                "Energy type (PETROL/DIESEL/ELECTRIC/HYBRID): ",
                new String[]{"PETROL", "DIESEL", "ELECTRIC", "HYBRID"});

        final String energyUnit = energyType.equals("ELECTRIC") ? "KWH" : "LITER";
        System.out.println("Energy unit auto-set to: " + energyUnit);

        final double consumption = readNonNegativeDouble(scanner, "Energy consumption per 100km: ");
        final int mileage = readNonNegativeInt(scanner, "Mileage (km): ");
        final double level = readNonNegativeDouble(scanner, "Energy level (" + energyUnit + "): ");

        // subtype detail
        final Integer seats;
        final Double maxLoadKg;
        final Integer engineCcm;

        switch (vehicleType) {
            case "CAR" -> {
                seats = readPositiveInt(scanner, "Number of seats: ");
                maxLoadKg = null;
                engineCcm = null;
            }
            case "TRUCK" -> {
                seats = null;
                maxLoadKg = readNonNegativeDouble(scanner, "Max load (kg): ");
                engineCcm = null;
            }
            case "MOTORCYCLE" -> {
                seats = null;
                maxLoadKg = null;
                engineCcm = readPositiveInt(scanner, "Engine capacity (ccm): ");
            }
            default -> throw new IllegalStateException("Unexpected value: " + vehicleType);
        }

        try {
            int vehicleId = DbWrite.inTransaction(conn -> {
                int vid = insertVehicle(conn, vehicleType, plate, brand, year,
                        energyType, energyUnit, consumption, mileage, level);

                insertSubtype(conn, vehicleType, vid, seats, maxLoadKg, engineCcm);
                return vid;
            });

            System.out.println("Insert successful ✅ vehicle_id = " + vehicleId);

        } catch (Exception e) {
            System.out.println("Insert failed ❌");
            e.printStackTrace();
        }
    }

    // ---------- INSERTS using DbWrite ----------

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

    private static void insertSubtype(Connection conn,
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
