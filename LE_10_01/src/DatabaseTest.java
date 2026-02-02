import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class DatabaseTest {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/fleetdb",
                    "root",
                    "1111"
            );
            System.out.println("Connected to database successfully");

            Statement stmt = connection.createStatement();

            stmt.executeUpdate(
                    "INSERT INTO employee (name, driving_license_type) " +
                           "VALUES ('John Smith', 'B')"
            );

            System.out.println("Inserted employee successfully");

            ResultSet rs = stmt.executeQuery(
                    "SELECT id, name, driving_license_type FROM employee"
            );

            System.out.println("=== Employees in database ===");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String license  = rs.getString("driving_license_type");

                System.out.println(id + " | " + name + " | " + license);
            }
            rs.close();

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



