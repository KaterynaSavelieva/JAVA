package LE_09_03.app;

public class MenuPrinter {
    private MenuPrinter() { }

    public static void printMenu() {
        System.out.println("=== Account Management ===");
        System.out.println("1) Create account");
        System.out.println("2) Delete account");
        System.out.println("3) Deposit money");
        System.out.println("4) Withdraw money");
        System.out.println("5) Show balance");
        System.out.println("6) Show one account");
        System.out.println("7) Show all accounts");
        System.out.println("8) Show branches");
        System.out.println("0) Exit");
    }
}
