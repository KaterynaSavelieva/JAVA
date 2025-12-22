package LE_09_03;

import java.util.Scanner;
import utils.InputUtils;

public class App {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        AccountManagement manager = new AccountManagement();


        while (true){
            printMenu ();
            int choice = readInt (scanner, "Choose an option: ");

            try {
                switch (choice){
                    case 1: System.out.println("1) Create"); break;

                    case 2: System.out.println("2) Delete"); break;
                    case 3: System.out.println("3) Deposit");break;
                    case 4: System.out.println("4) Withdraw");break;
                    case 5: System.out.println("5) balance");break;
                    case 6: System.out.println("6) one account");break;
                    case 7: System.out.println("7) all accounts");break;
                    case 0: {
                        System.out.println("Bye! ");
                        return;
                    }
                    default: System.out.println("Unknown option. Try again");
                }
            } catch (IllegalArgumentException ex) {
                System.out.println("Input error: "+ex.getMessage());
            }
            System.out.println();
        }

    }

    private static void printMenu() {
        System.out.println("=== Account Management ===");
        System.out.println("1) Create account");
        System.out.println("2) Delete account");
        System.out.println("3) Deposit money");
        System.out.println("4) Withdraw money");
        System.out.println("5) Show balance");
        System.out.println("6) Show one account");
        System.out.println("7) Show all accounts");
        System.out.println("0) Exit");
    }
    
    //  Input helpers
    private static int readInt (Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private static double readDouble(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();
            try {
                return Double.parseDouble(input.trim().replaceAll(",", "."));
            }catch (NumberFormatException ex) {
                System.out.println("Please enter a valid number (example: 10.50).");
            }
        }
    }

    private static String readString(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Input must not be empty.");
        }
    }


}
