package LE_10_01.app;

import LE_10_01.management.FleetManagement;

import java.util.Scanner;

public class ConsoleUi {

    private final Scanner scanner = new Scanner(System.in);
    private final FleetManagement management = new FleetManagement();

    public void run() {
        while (true) {
            MenuPrinter.printHeader();
            MenuPrinter.printMenu();

            int choice = InputHelper.readInt(scanner, "Choose option (0-9): ", 0, 9);

            switch (choice) {
                case 1 -> management.showAllVehicles();
                case 2 -> management.showVehiclesWithDetails();
                case 3 -> management.showCurrentDriverPerVehicle();
                case 4 -> management.showAllEmployees();
                case 5 -> management.insertNewEmployee(scanner);
                case 6 -> management.insertNewVehicle(scanner);
                case 7 -> management.assignDriverToVehicle(scanner);
                case 8 -> management.unassignDriverFromVehicle(scanner);
                case 9 -> management.showHistoryDriverPerVehicle();

                case 0 -> {
                    System.out.println("Bye!");
                    scanner.close();
                    return;
                }
            }

            InputHelper.pressEnter(scanner);
        }
    }
}
