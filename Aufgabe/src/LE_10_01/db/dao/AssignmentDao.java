package LE_10_01.db.dao;

import LE_10_01.db.util.DbRunner;
import LE_10_01.db.util.DbWrite;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

/*DAO (Data Access Object) class for driver assignments to vehicles.
 Handles reading and writing assignment data.*/
public class AssignmentDao {

    /*Prints the current driver for each vehicle.
      Uses a database VIEW for easier SELECT.  */
    public void printCurrentDriverPerVehicle() {

        // SQL query using the database view
        String sql = """
                SELECT vehicle_id, license_plate, vehicle_type,
                       employee_id, name,
                       licenses,
                       assigned_from
                FROM current_driver_view
                ORDER BY vehicle_id
                """;

        // Table header for console output
        String header = String.format("| %-3s | %-10s | %-10s | %-3s | %-20s | %-10s | %-10s |",
                "VID", "PLATE", "TYPE", "EID", "DRIVER", "LICENSES", "FROM");

        // Execute SELECT and print result
        DbRunner.print("CURRENT DRIVER", header, sql, rs ->
                String.format("| %3d | %-10s | %-10s | %-3s | %-20s | %-10s | %-10s |",
                        rs.getInt("vehicle_id"),
                        rs.getString("license_plate"),
                        rs.getString("vehicle_type"),
                        // If there is no driver, show "-"
                        (rs.getObject("employee_id") == null ? "-" : rs.getInt("employee_id")),
                        (rs.getString("name") == null ? "-" : rs.getString("name")),
                        rs.getString("licenses"),
                        (rs.getDate("assigned_from") == null
                                ? "-"
                                : rs.getDate("assigned_from").toString())
                )
        );
    }


    /*Prints the current driver for each vehicle.
      Uses a database VIEW for easier SELECT.  */
    public void printHistoryDriverPerVehicle() {

        // SQL query using the database view
        String sql = """
                SELECT vehicle_id, license_plate, vehicle_type,
                       employee_id, name,
                       licenses,
                       assigned_from, assigned_to
                FROM driver_history_view
                ORDER BY vehicle_id
                """;

        // Table header for console output
        String header = String.format("| %-3s | %-10s | %-10s | %-3s | %-20s | %-10s | %-10s | %-10s |",
                "VID", "PLATE", "TYPE", "EID", "DRIVER", "LICENSES", "FROM", "TO");

        // Execute SELECT and print result
        DbRunner.print("CURRENT DRIVER", header, sql, rs ->
                String.format("| %3d | %-10s | %-10s | %-3s | %-20s | %-10s | %-10s | %-10s |",
                        rs.getInt("vehicle_id"),
                        rs.getString("license_plate"),
                        rs.getString("vehicle_type"),
                        // If there is no driver, show "-"
                        (rs.getObject("employee_id") == null ? "-" : rs.getInt("employee_id")),
                        (rs.getString("name") == null ? "-" : rs.getString("name")),
                        rs.getString("licenses"),
                        (rs.getDate("assigned_from") == null
                                ? "-"
                                : rs.getDate("assigned_from").toString())
                        ,
                        (rs.getDate("assigned_to") == null
                                ? ""
                                : rs.getDate("assigned_to").toString())
                )
        );
    }

    // Check if this vehicle already has a current driver
    public boolean hasCurrentDriver (int vehicleId) {
        String sql = """
                SELECT 1
                FROM vehicle_driver
                WHERE vehicle_id = ?
                AND assigned_to IS NULL
                LIMIT 1;
        """;
        return DbWrite.inTransaction(conn -> {
            try (var ps = conn.prepareStatement(sql)) {
                ps.setInt(1, vehicleId);
                try (var rs = ps.executeQuery()) {
                    return rs.next();
                }
            }
        });
    }

    // Unassign current driver (close current assignment). Returns affected rows (0 or 1)
    public int unassignCurrentDriver (int vehicleId, LocalDate endDate) {
        return DbWrite.<Integer>inTransaction(conn ->
                closeCurrentAssignment(conn, vehicleId, endDate)
        );
    }

    // Normal assign: will FAIL/RETURN 0 if vehicle is not free (we check in Management)
    public int assignDriver(int vehicleId, int employeeId, LocalDate fromDate) {
        return DbWrite.inTransaction(conn ->
                insertNewAssignment(conn, vehicleId, employeeId, fromDate));
    }

    public int replaceDriver(int vehicleId, int employeeId, LocalDate fromDate) {
        return DbWrite.inTransaction(conn -> {
            closeCurrentAssignment(conn, vehicleId, fromDate);
            return insertNewAssignment(conn, vehicleId, employeeId, fromDate);
        });
    }



    /* Closes the current assignment for a vehicle.
      Sets assigned_to to the given end date.     */
    private int closeCurrentAssignment(Connection conn,
                                        int vehicleId,
                                        LocalDate endDate) throws SQLException {

        String sql = """
                UPDATE vehicle_driver
                SET assigned_to = ?
                WHERE vehicle_id = ?
                  AND assigned_to IS NULL
                """;

        // Execute UPDATE statement
        return DbWrite.executeUpdate(conn, sql, ps -> {
            ps.setDate(1, java.sql.Date.valueOf(endDate));
            ps.setInt(2, vehicleId);
        });
    }

    /* Inserts a new driver assignment.
      The new assignment is marked as current (assigned_to = NULL).*/
    private int insertNewAssignment(Connection conn,
                                    int vehicleId,
                                    int employeeId,
                                    LocalDate fromDate) throws SQLException {

        String sql = """
                INSERT INTO vehicle_driver (vehicle_id, employee_id, assigned_from, assigned_to)
                VALUES (?, ?, ?, NULL)
                """;

        // Execute INSERT statement
        return DbWrite.executeUpdate(conn, sql, ps -> {
            ps.setInt(1, vehicleId);
            ps.setInt(2, employeeId);
            ps.setDate(3, java.sql.Date.valueOf(fromDate));
        });
    }

    public boolean canDriveVehicle(int vehicleId, int employeeId) {
        String sql = """
                SELECT 1
                FROM vehicle v
                JOIN employee_license el ON el.employee_id = ?
                WHERE v.vehicle_id = ?
                AND (
                    (v.vehicle_type = 'CAR' AND el.license_code = 'B') OR
                    (v.vehicle_type = 'TRUCK' AND el.license_code IN ('C', 'CE')) OR
                    (v.vehicle_type = 'MOTORCYCLE' AND el.license_code = 'A')
                )
                LIMIT 1
        """;
        return DbWrite.inTransaction(conn ->  {
            try (var ps = conn.prepareStatement(sql)) {
                ps.setInt(1, employeeId);
                ps.setInt(2, vehicleId);
                try (var rs = ps.executeQuery()) {
                    return rs.next();
                }
            }
        });
    }
}
