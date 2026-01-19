package LE_10_01.db.insert;

import LE_10_01.db.DbWrite;
import LE_10_01.utils.Input;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class EmployeeInsert {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== INSERT NEW EMPLOYEE ===");

        System.out.print("Name: ");
        final String name = scanner.nextLine().trim();

        System.out.print("Email: ");
        final String email = scanner.nextLine().trim();

        System.out.print("Phone (optional): ");
        String phoneInput = scanner.nextLine().trim();
        final String phone = phoneInput.isBlank() ? null : phoneInput;

        final LocalDate birthdate = Input.inputDate(scanner, "Birthdate (yyyy-MM-dd, Enter to skip): ");

        try {
            int employeeId = DbWrite.inTransaction(conn -> {
                int personId = insertPerson(conn, name, email, phone, birthdate);
                return insertEmployee(conn, personId);
            });

            System.out.println("Insert successful employee_id = " + employeeId);

        } catch (Exception e) {
            System.out.println("Insert failed ");
            e.printStackTrace();
        }
    }

    private static int insertPerson(Connection conn,
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

    private static int insertEmployee(Connection conn, int personId) throws SQLException {

        String sql = "INSERT INTO employee (person_id) VALUES (?)";

        return DbWrite.insertAndGetId(conn, sql, ps -> ps.setInt(1, personId));
    }
}
