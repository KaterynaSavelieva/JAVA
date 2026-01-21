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
                SELECT vehicle_id, license_plate,
                       employee_id, name,
                       assigned_from
                FROM current_driver_view
                ORDER BY vehicle_id
                """;

        // Table header for console output
        String header = String.format("| %-3s | %-10s | %-3s | %-20s | %-10s |",
                "VID", "PLATE", "EID", "DRIVER", "FROM");

        // Execute SELECT and print result
        DbRunner.print("CURRENT DRIVER", header, sql, rs ->
                String.format("| %3d | %-10s | %-3s | %-20s | %-10s |",
                        rs.getInt("vehicle_id"),
                        rs.getString("license_plate"),
                        // If there is no driver, show "-"
                        (rs.getObject("employee_id") == null ? "-" : rs.getInt("employee_id")),
                        (rs.getString("name") == null ? "-" : rs.getString("name")),
                        (rs.getDate("assigned_from") == null
                                ? "-"
                                : rs.getDate("assigned_from").toString())
                )
        );
    }

    /*Assigns an employee as the current driver of a vehicle.
     The operation is done in one transaction.     */
    public int assignDriver(int vehicleId, int employeeId, LocalDate fromDate) {

        // Run both steps in one transaction
        return DbWrite.inTransaction(conn -> {

            // Close previous assignment (if exists)
            closeCurrentAssignment(conn, vehicleId, fromDate);

            // Insert new assignment
            return insertNewAssignment(conn, vehicleId, employeeId, fromDate);
        });
    }

    /* Closes the current assignment for a vehicle.
      Sets assigned_to to the given end date.     */
    private void closeCurrentAssignment(Connection conn,
                                        int vehicleId,
                                        LocalDate endDate) throws SQLException {

        String sql = """
                UPDATE vehicle_driver
                SET assigned_to = ?
                WHERE vehicle_id = ?
                  AND assigned_to IS NULL
                """;

        // Execute UPDATE statement
        DbWrite.executeUpdate(conn, sql, ps -> {
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
}
