package LE_10_01.app;

public class MenuPrinter {

    public static void printHeader() {
        System.out.println("\n========== FLEET DB MENU ==========");
    }

    public static void printMenu() {
        System.out.println("1) Show all vehicles");
        System.out.println("2) Show vehicles + details");
        System.out.println("3) Show current driver per vehicle");
        System.out.println("4) Show all employees");
        System.out.println("5) New employee (insert)");
        System.out.println("6) New vehicle (insert)");
        System.out.println("7) Assign driver to vehicle");
        System.out.println("8) Unassign driver from vehicle");
        System.out.println("9) Show history driver per vehicle");
        System.out.println("0) Exit");
    }
}
