package LE_10_01.db.dao;

import LE_10_01.db.util.DbRunner;
import LE_10_01.db.util.DbWrite;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class AssignmentDao {

    public void printCurrentDriverPerVehicle() {
        String sql = """
                SELECT v.vehicle_id, v.license_plate,
                       e.employee_id, p.name,
                       vd.assigned_from
                FROM vehicle v
                LEFT JOIN vehicle_driver vd
                    ON vd.vehicle_id = v.vehicle_id AND vd.assigned_to IS NULL
                LEFT JOIN employee e ON e.employee_id = vd.employee_id
                LEFT JOIN person p ON p.person_id = e.person_id
                ORDER BY v.vehicle_id
                """;

        String header = String.format("| %-3s | %-10s | %-3s | %-20s | %-10s |",
                "VID", "PLATE", "EID", "DRIVER", "FROM");

        DbRunner.print("CURRENT DRIVER", header, sql, rs ->
                String.format("| %3d | %-10s | %-3s | %-20s | %-10s |",
                        rs.getInt("vehicle_id"),
                        rs.getString("license_plate"),
                        (rs.getObject("employee_id") == null ? "-" : rs.getInt("employee_id")),
                        (rs.getString("name") == null ? "-" : rs.getString("name")),
                        (rs.getDate("assigned_from") == null ? "-" : rs.getDate("assigned_from").toString())
                )
        );
    }

    /* Assigns an employee as current driver for a vehicle.
      Steps (one transaction):
     1) Close previous current assignment (assigned_to IS NULL) for this vehicle
     2) Insert new assignment with assigned_from = date, assigned_to = NULL
     return inserted rows (should be 1)
     */
    public int assignDriver(int vehicleId, int employeeId, LocalDate fromDate) {
        return DbWrite.inTransaction(conn -> {
            closeCurrentAssignment(conn, vehicleId, fromDate);
            return insertNewAssignment(conn, vehicleId, employeeId, fromDate);
        });
    }

    private void closeCurrentAssignment(Connection conn, int vehicleId, LocalDate endDate) throws SQLException {
        String sql = """
                UPDATE vehicle_driver
                SET assigned_to = ?
                WHERE vehicle_id = ?
                  AND assigned_to IS NULL
                """;

        DbWrite.executeUpdate(conn, sql, ps -> {
            ps.setDate(1, java.sql.Date.valueOf(endDate));
            ps.setInt(2, vehicleId);
        });
    }

    private int insertNewAssignment(Connection conn, int vehicleId, int employeeId, LocalDate fromDate) throws SQLException {
        String sql = """
                INSERT INTO vehicle_driver (vehicle_id, employee_id, assigned_from, assigned_to)
                VALUES (?, ?, ?, NULL)
                """;

        return DbWrite.executeUpdate(conn, sql, ps -> {
            ps.setInt(1, vehicleId);
            ps.setInt(2, employeeId);
            ps.setDate(3, java.sql.Date.valueOf(fromDate));
        });
    }
}
