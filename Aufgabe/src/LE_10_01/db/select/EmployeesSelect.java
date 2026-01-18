package LE_10_01.db;

import LE_10_01.app.DbRunner;

public class EmployeesSelect {

    public static void main(String[] args) {

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
                        rs.getDate("birthdate") // можна так, або getString
                )
        );
    }
}
