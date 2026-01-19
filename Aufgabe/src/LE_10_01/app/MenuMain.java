package LE_10_01.app;

import java.util.Scanner;

import LE_10_01.db.insert.EmployeeInsert;
import LE_10_01.db.select.CurrentDriverSelect;
import LE_10_01.db.select.EmployeesSelect;
import LE_10_01.db.select.VehicleSelect;
import LE_10_01.db.select.VehicleWithDetailsSelect;

public class MenuMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printHeader();
            printMenu();

            System.out.print("Choose option (0-3): ");
            String input = scanner.nextLine().trim();

            int choice;
            try {
                choice = Integer.parseInt(input);
            }catch (NumberFormatException e){
                System.out.println("Please enter a number between 1-3");
                continue;
            }

            switch (choice) {
                case 1: VehicleSelect.main(new String[]{}); break;
                case 2: VehicleWithDetailsSelect.main(new String[]{}); break;
                case 3: CurrentDriverSelect.main(new String[]{}); break;
                case 4: EmployeesSelect.main(new String[]{}); break;
                case 5: EmployeeInsert.main(new String[]{}); break;
                case 0: {
                    System.out.println("Bye!");;
                    scanner.close();
                    return;
                }
                default: System.out.println("Invalid choice: please enter a number between 0 and 3");
            }

            System.out.println("\nPress ENTER to continue...");
            scanner.nextLine();

        }
    }

    private static void printMenu() {
        System.out.println("1) Show all vehicles");
        System.out.println("2) Show vehicles + details");
        System.out.println("3) Show current driver per vehicle");
        System.out.println("4) Show all employees");
        System.out.println("5) New Employee");
        System.out.println("0) Exit");
    }

    private static void printHeader() {
        System.out.println("\n========== FLEET DB MENU ==========");
    }
}
