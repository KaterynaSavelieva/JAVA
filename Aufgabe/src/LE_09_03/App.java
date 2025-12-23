package LE_09_03;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AccountManagement manager = new AccountManagement();

        while (true){
            printMenu ();
            int choice = readInt (scanner, "Choose an option: ");

            try {
                switch (choice){
                    case 1: handleCreateAccount(scanner, manager); break;

                    case 2: handleDeleteAccount (scanner, manager); break;
                    case 3: handleDeposit(scanner, manager);break;
                    case 4: handleWithdraw(scanner, manager);break;
                    case 5: handleGetBalance(scanner, manager);break;
                    case 6: handleShowAccount (scanner,manager);break;
                    case 7: handleShowAllAccounts (scanner,manager);break;
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

    private static void handleCreateAccount(Scanner scanner, AccountManagement manager){
        String number = readString(scanner, "Account number: ");
        String owner = readString(scanner, "Owner name: ");
        double startBalance = readDouble(scanner, "Start balance: ");

        manager.createAccount(number, owner, startBalance);
        System.out.println("Account created successfully.");
    }

    private static void handleDeleteAccount(Scanner scanner, AccountManagement manager){
        String number = readString(scanner, "Account number: ");
        boolean deleted = manager.deleteAccount(number);
        if (deleted){
            System.out.println("Account deleted successfully.");
        }else {
            System.out.println("Account not found.");
        }
    }

    private static void handleDeposit  (Scanner scanner, AccountManagement manager){
        String number = readString(scanner, "Account number: ");
        double amount = readDouble(scanner, "Deposit amount ");
        boolean deposited = manager.deposit(number, amount);
        if (deposited){
            System.out.println("Deposited successfully.");
        }
        else {
            System.out.println("Account not found.");
        }
    }

    private static void handleWithdraw(Scanner scanner, AccountManagement manager){
        String number = readString(scanner, "Account number: ");
        double amount = readDouble(scanner, "Withdraw amount ");
        boolean withdrawn = manager.withdraw(number, amount);
        if (withdrawn){
            System.out.println("Withdraw successfully.");
        }else  {
            Double balance = manager.getBalance(number);
            if(balance==null){
                System.out.println("Account not found.");
            }else {
                System.out.println("Not enough balance.");
            }
        }
    }

    private static void handleGetBalance(Scanner scanner, AccountManagement manager){
        String number = readString(scanner, "Account number: ");
        Double balance = manager.getBalance(number);
        if (balance==null){
            System.out.println("Account not found.");
        }else  {
            System.out.println("Account balance: "+balance);
        }
    }

    private static void handleShowAccount(Scanner scanner, AccountManagement manager){
        String number = readString(scanner, "Account number: ");
        String result = manager.showAccount(number);
        System.out.println(result);
    }

    private static void handleShowAllAccounts(Scanner scanner, AccountManagement manager){
        String accounts = manager.showAllAccounts();
        System.out.println(accounts);
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
