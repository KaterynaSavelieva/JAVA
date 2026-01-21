package LE_10_01.db.dao;

import LE_10_01.db.util.DbRunner;
import LE_10_01.db.util.DbWrite;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class EmployeeDao {

    public void printAllEmployees() {
        String sql = """
                SELECT e.employee_id, p.name, p.email, p.phone, p.birthdate
                FROM employee e
                JOIN person p ON p.person_id = e.person_id
                ORDER BY e.employee_id
                """;

        String header = String.format("| %-3s | %-20s | %-28s | %-17s | %-10s |",
                "ID", "NAME", "EMAIL", "PHONE", "BIRTHDATE");

        DbRunner.print("EMPLOYEES", header, sql, rs ->
                String.format("| %3d | %-20s | %-28s | %-17s | %-10s |",
                        rs.getInt("employee_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        (rs.getDate("birthdate") == null ? "-" : rs.getDate("birthdate").toString())
                )
        );
    }

    public int insertEmployee(String name, String email, String phone, LocalDate birthdate) {
        return DbWrite.inTransaction(conn -> {
            int personId = insertPerson(conn, name, email, phone, birthdate);
            return insertEmployeeRow(conn, personId);
        });
    }

    private int insertPerson(Connection conn,
                             String name,
                             String email,
                             String phone,
                             LocalDate birthdate) throws SQLException {

        String sql = "INSERT INTO person (name, email, phone, birthdate) VALUES (?,?,?,?)";

        return DbWrite.insertAndGetId(conn, sql, ps -> {
            ps.setString(1, name);
            ps.setString(2, email);
            DbWrite.setNullableString(ps, 3, phone);
            DbWrite.setNullableDate(ps, 4, birthdate);
        });
    }

    private int insertEmployeeRow(Connection conn, int personId) throws SQLException {
        String sql = "INSERT INTO employee (person_id) VALUES (?)";
        return DbWrite.insertAndGetId(conn, sql, ps -> ps.setInt(1, personId));
    }
}
