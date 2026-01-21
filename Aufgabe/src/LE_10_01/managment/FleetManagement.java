package LE_10_01.management;

import LE_10_01.app.InputHelper;
import LE_10_01.db.dao.AssignmentDao;
import LE_10_01.db.dao.EmployeeDao;
import LE_10_01.db.dao.VehicleDao;

import java.time.LocalDate;
import java.util.Scanner;

public class FleetManagement {

    private final VehicleDao vehicleDao = new VehicleDao();
    private final EmployeeDao employeeDao = new EmployeeDao();
    private final AssignmentDao assignmentDao = new AssignmentDao();

    public void showAllVehicles() {
        vehicleDao.printAllVehicles();
    }

    public void showVehiclesWithDetails() {
        vehicleDao.printVehiclesWithDetails();
    }

    public void showCurrentDriverPerVehicle() {
        assignmentDao.printCurrentDriverPerVehicle();
    }

    public void showAllEmployees() {
        employeeDao.printAllEmployees();
    }

    public void insertNewEmployee(Scanner scanner) {
        System.out.println("\n=== INSERT NEW EMPLOYEE ===");

        String name = InputHelper.readNotBlank(scanner, "Name: ");
        String email = InputHelper.readNotBlank(scanner, "Email: ");
        String phone = InputHelper.readOptional(scanner, "Phone (optional): ");
        LocalDate birthdate = InputHelper.readOptionalDate(scanner, "Birthdate (yyyy-MM-dd, Enter to skip): ");

        int employeeId = employeeDao.insertEmployee(name, email, phone, birthdate);
        System.out.println("Insert successful employee_id = " + employeeId);
    }

    public void insertNewVehicle(Scanner scanner) {
        System.out.println("\n=== INSERT NEW VEHICLE ===");

        String vehicleType = InputHelper.readEnum(scanner,
                "Vehicle type (CAR/TRUCK/MOTORCYCLE): ",
                new String[]{"CAR", "TRUCK", "MOTORCYCLE"});

        String plate = InputHelper.readNotBlank(scanner, "License plate: ");
        String brand = InputHelper.readNotBlank(scanner, "Brand: ");
        int year = InputHelper.readPositiveInt(scanner, "Production year (e.g. 2019): ");

        String energyType = InputHelper.readEnum(scanner,
                "Energy type (PETROL/DIESEL/ELECTRIC/HYBRID): ",
                new String[]{"PETROL", "DIESEL", "ELECTRIC", "HYBRID"});

        String energyUnit = energyType.equals("ELECTRIC") ? "KWH" : "LITER";
        System.out.println("Energy unit auto-set to: " + energyUnit);

        double consumption = InputHelper.readNonNegativeDouble(scanner, "Energy consumption per 100km: ");
        int mileage = InputHelper.readNonNegativeInt(scanner, "Mileage (km): ");
        double level = InputHelper.readNonNegativeDouble(scanner, "Energy level (" + energyUnit + "): ");

        Integer seats = null;
        Double maxLoadKg = null;
        Integer engineCcm = null;

        switch (vehicleType) {
            case "CAR" -> seats = InputHelper.readPositiveInt(scanner, "Number of seats: ");
            case "TRUCK" -> maxLoadKg = InputHelper.readNonNegativeDouble(scanner, "Max load (kg): ");
            case "MOTORCYCLE" -> engineCcm = InputHelper.readPositiveInt(scanner, "Engine capacity (ccm): ");
        }

        int vehicleId = vehicleDao.insertVehicleWithSubtype(
                vehicleType, plate, brand, year, energyType, energyUnit,
                consumption, mileage, level,
                seats, maxLoadKg, engineCcm
        );

        System.out.println("Insert successful vehicle_id = " + vehicleId);
    }

    public void assignDriverToVehicle(Scanner scanner) {
        System.out.println("\n=== ASSIGN DRIVER TO VEHICLE ===");

        int vehicleId = InputHelper.readPositiveInt(scanner, "Vehicle ID: ");
        int employeeId = InputHelper.readPositiveInt(scanner, "Employee ID: ");

        int insertedRows = assignmentDao.assignDriver(vehicleId, employeeId, LocalDate.now());
        System.out.println("Assignment done (inserted rows: " + insertedRows + ")");
    }

}
