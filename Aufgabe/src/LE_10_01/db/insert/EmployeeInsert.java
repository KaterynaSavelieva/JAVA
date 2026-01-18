package LE_10_01.db;

import LE_10_01.utils.Input;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Scanner;

public class EmployeeInsert {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Email: ");
        String email = scanner.nextLine().trim();

        System.out.print("Phone (optional): ");
        String phoneInput  = scanner.nextLine().trim();
        String phone = phoneInput.isBlank() ? null: phoneInput;

        LocalDate birthdate = Input.inputDate(scanner, "Birthdate (yyyy-MM-dd, Enter to skip): ");

        try {
            int employeeId = DbWrite.inTransaction(conn -> {
                int personId = insertPerson(conn, name, email, phone, birthdate);
                return insertEmployee(conn, personId);
            });

            System.out.println("Insert successful! Employee_id = " + employeeId);

        } catch (Exception e) {
            System.out.println("Insert failed!");
            e.printStackTrace();
        }
    }

    private static int insertPerson(Connection conn, String name, String email, String phone, LocalDate birthdate)
            throws Exception {

        String sql = """
                INSERT INTO person (name, email, phone, birthdate)
                VALUES (?, ?, ?, ?)
                """;

        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, name);
            ps.setString(2, email);
            DbWrite.setNullableString(ps, 3, phone);
            DbWrite.setNullableDate(ps, 4, birthdate);

            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) return keys.getInt(1);
            }
        }
        throw new Exception("No generated key for person");
    }

    private static int insertEmployee(Connection conn, int personId) throws Exception {

        String sql = "INSERT INTO employee (person_id) VALUES (?)";

        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, personId);
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) return keys.getInt(1);
            }
        }
        throw new Exception("No generated key for employee");
    }
}
