package LE_10_01.db.dao;

import LE_10_01.db.DBConnection;
import LE_10_01.db.util.DbRunner;
import LE_10_01.db.util.DbWrite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Date;

/* DAO for employee-related database operations.
 * - SELECT employees (read)
 * - INSERT new employee (write) */
public class EmployeeDao {

    /* Prints all employees in the console.
     Uses a VIEW to keep the SQL simple.     */
    public void printAllEmployees() {
        String sql = """
                SELECT employee_id,
                       name,
                       email,
                       phone,
                       birthdate
                FROM all_employees_view
                ORDER BY employee_id
                """;

        // Table header for nice console output
        String header = String.format("| %-3s | %-20s | %-28s | %-17s | %-10s |",
                "ID", "NAME", "EMAIL", "PHONE", "BIRTHDATE");

        // Run SELECT and print each row using a RowMapper (lambda)
        DbRunner.print("EMPLOYEES", header, sql, rs ->
                String.format("| %3d | %-20s | %-28s | %-17s | %-10s |",
                        rs.getInt("employee_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        // Show "-" if birthdate is NULL
                        (rs.getDate("birthdate") == null ? "-" : rs.getDate("birthdate").toString())
                )
        );
    }

    /* Inserts (creates) a new employee.
      We insert into TWO tables:
      1) person
      2) employee (references person_id)
     This is done in one transaction (commit or rollback).*/
    public int insertEmployee(String name, String email, String phone, LocalDate birthdate) {

        // One transaction for both inserts
        return DbWrite.inTransaction(conn -> {
            int personId = insertPerson(conn, name, email, phone, birthdate);
            return insertEmployeeRow(conn, personId);
        });
    }

    /* Inserts a row into table person and returns the generated person_id.     */
    private int insertPerson(Connection conn,
                             String name,
                             String email,
                             String phone,
                             LocalDate birthdate) throws SQLException {

        String sql = "INSERT INTO person (name, email, phone, birthdate) VALUES (?,?,?,?)";

        // insertAndGetId -> returns AUTO_INCREMENT id
        return DbWrite.insertAndGetId(conn, sql, ps -> {
            ps.setString(1, name);
            ps.setString(2, email);

            // phone and birthdate can be NULL
            DbWrite.setNullableString(ps, 3, phone);
            ps.setDate(4, Date.valueOf(birthdate));

        });
    }

    /* Inserts a row into table employee and returns the generated employee_id.
      The employee row points to person_id (1:1 relationship).     */
    private int insertEmployeeRow(Connection conn, int personId) throws SQLException {
        String sql = "INSERT INTO employee (person_id) VALUES (?)";

        // Returns generated employee_id
        return DbWrite.insertAndGetId(conn, sql, ps -> ps.setInt(1, personId));
    }

    public boolean existsById(int employeeId) {
        String sql = "SELECT 1 FROM employee WHERE  employee_id =? ";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1,employeeId);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // true = found, false = not found
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
