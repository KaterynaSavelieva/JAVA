package LE_10_01.management;

import LE_10_01.app.InputHelper;
import LE_10_01.db.dao.AssignmentDao;
import LE_10_01.db.dao.EmployeeDao;
import LE_10_01.db.dao.VehicleDao;

import java.time.LocalDate;
import java.util.Scanner;

/* FleetManagement = "business logic" layer.
  It connects the UI (console input) with the DAO layer (database).
  UI -> FleetManagement -> DAO -> DB */
public class FleetManagement {

    // DAO objects: each DAO is responsible for one database area
    private final VehicleDao vehicleDao = new VehicleDao();
    private final EmployeeDao employeeDao = new EmployeeDao();
    private final AssignmentDao assignmentDao = new AssignmentDao();

    /* Show basic vehicle list (without subtype details) */
    public void showAllVehicles() {
        vehicleDao.printAllVehicles();
    }

    /* Show vehicles with subtype details (seats / max load / engine ccm) */
    public void showVehiclesWithDetails() {
        vehicleDao.printVehiclesWithDetails();
    }

    /* Show the current driver for each vehicle (if assigned_to is NULL) */
    public void showCurrentDriverPerVehicle() {
        assignmentDao.printCurrentDriverPerVehicle();
    }

    /* Show all employees */
    public void showAllEmployees() {
        employeeDao.printAllEmployees();
    }

    /* Read user input and insert a new employee.
      - InputHelper is used to validate input in the console.
      - EmployeeDao inserts into DB (person + employee).*/
    public void insertNewEmployee(Scanner scanner) {
        System.out.println("\n=== INSERT NEW EMPLOYEE ===");

        // Read and validate input
        String name = InputHelper.readNotBlank(scanner, "Name: ");
        String email = InputHelper.readNotBlank(scanner, "Email: ");
        String phone = InputHelper.readOptional(scanner, "Phone (optional): ");
        LocalDate birthdate = InputHelper.readOptionalDate(scanner, "Birthdate (yyyy-MM-dd, Enter to skip): ");

        // Insert into DB and get generated employee_id
        int employeeId = employeeDao.insertEmployee(name, email, phone, birthdate);

        System.out.println("Insert successful employee_id = " + employeeId);
    }

    /* Read user input and insert a new vehicle.
      - First insert into "vehicle" table (base)
      - Then insert into subtype table: car/truck/motorcycle*/
    public void insertNewVehicle(Scanner scanner) {
        System.out.println("\n=== INSERT NEW VEHICLE ===");

        // 1) Vehicle type
        String vehicleType = InputHelper.readEnum(scanner,
                "Vehicle type (CAR/TRUCK/MOTORCYCLE): ",
                new String[]{"CAR", "TRUCK", "MOTORCYCLE"});

        // 2) Common vehicle fields
        String plate = InputHelper.readNotBlank(scanner, "License plate: ");
        String brand = InputHelper.readNotBlank(scanner, "Brand: ");
        int year = InputHelper.readPositiveInt(scanner, "Production year (e.g. 2019): ");

        // 3) Energy type + auto energy unit
        String energyType = InputHelper.readEnum(scanner,
                "Energy type (PETROL/DIESEL/ELECTRIC/HYBRID): ",
                new String[]{"PETROL", "DIESEL", "ELECTRIC", "HYBRID"});

        // Simple rule: ELECTRIC -> KWH, else -> LITER
        String energyUnit = energyType.equals("ELECTRIC") ? "KWH" : "LITER";
        System.out.println("Energy unit auto-set to: " + energyUnit);

        // 4) More common fields
        double consumption = InputHelper.readNonNegativeDouble(scanner, "Energy consumption per 100km: ");
        int mileage = InputHelper.readNonNegativeInt(scanner, "Mileage (km): ");
        double level = InputHelper.readNonNegativeDouble(scanner, "Energy level (" + energyUnit + "): ");

        // 5) Subtype fields (only one is used depending on vehicleType)
        Integer seats = null;
        Double maxLoadKg = null;
        Integer engineCcm = null;

        switch (vehicleType) {
            case "CAR" -> seats = InputHelper.readPositiveInt(scanner, "Number of seats: ");
            case "TRUCK" -> maxLoadKg = InputHelper.readNonNegativeDouble(scanner, "Max load (kg): ");
            case "MOTORCYCLE" -> engineCcm = InputHelper.readPositiveInt(scanner, "Engine capacity (ccm): ");
        }

        // 6) Insert into DB (vehicle + subtype) in one transaction
        int vehicleId = vehicleDao.insertVehicleWithSubtype(
                vehicleType, plate, brand, year, energyType, energyUnit,
                consumption, mileage, level,
                seats, maxLoadKg, engineCcm
        );

        System.out.println("Insert successful vehicle_id = " + vehicleId);
    }

    /* Assign an employee as the current driver for a vehicle.
     In the database:
      - close old current assignment (set assigned_to)
      - insert new assignment with assigned_to = NULL
     Both actions are done in one transaction. */
    public void assignDriverToVehicle(Scanner scanner) {
        System.out.println("\n=== ASSIGN DRIVER TO VEHICLE ===");

        // Read IDs from user
        int vehicleId = InputHelper.readPositiveInt(scanner, "Vehicle ID: ");
        int employeeId = InputHelper.readPositiveInt(scanner, "Employee ID: ");

        // --- safety checks ---
        if (!vehicleDao.existsById(vehicleId)) {
            System.out.println("Vehicle ID: " + vehicleId + " does not exist");
            return;
        }

        if (!employeeDao.existsById(employeeId)) {
            System.out.println("Employee ID: " + employeeId + " does not exist");
            return;
        }

        // Do transaction: close old + insert new
        int insertedRows = assignmentDao.assignDriver(vehicleId, employeeId, LocalDate.now());
        System.out.println("Assignment done (inserted rows: " + insertedRows + ")");
    }

}
