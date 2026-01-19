package LE_09_03.app;

import LE_09_03.management.AccountManagement;
import java.util.Scanner;

public class ConsoleUI {

    private final Scanner scanner;
    private final AccountManagement manager;

    public ConsoleUI(Scanner scanner, AccountManagement manager) {
        this.scanner = scanner;
        this.manager = manager;
    }

    public void start(){
        while (true){
            MenuPrinter.printMenu ();
            int choice = InputHelper.readInt (scanner, "Choose an option: ");

            try {
                switch (choice){
                    case 1: handleCreateAccount(); break;
                    case 2: handleDeleteAccount(); break;
                    case 3: handleDeposit(); break;
                    case 4: handleWithdraw(); break;
                    case 5: handleGetBalance(); break;
                    case 6: handleShowAccount (); break;
                    case 7: handleShowAllAccounts (); break;
                    case 8: handleShowBranches(); break;
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

    private String askForExistingAccountNumber() {
        System.out.println(manager.showOnlyAccounts());

        while (true) {
            String number = InputHelper.readString(scanner, "Account number: ");
            if ("0".equals(number)) {
                return null;
            }
            if (manager.getBalance(number) != null) {
                return number;
            }
            System.out.println("Account not found. Please choose an existing account.");
        }
    }


    private  void handleCreateAccount(){
        System.out.println(manager.showAllBranches());
        String branchId;
        while (true) {
            branchId = InputHelper.readString(scanner, "Branch ID (e.g. B01): ");
            if (manager.findBranchById(branchId) != null) break;
            System.out.println("Branch not found. Please choose one of the listed Branch IDs.");
        }
        String number = InputHelper.readString(scanner, "Account number: ");
        double startBalance = InputHelper.readDouble(scanner, "Start balance: ");

        boolean created = manager.createAccount(branchId, number, startBalance);
        if (created){
            System.out.println("Account created successfully!");
        } else {
            System.out.println("Account creation failed!");
        }
    }




    private  void handleDeleteAccount(){
        String number= askForExistingAccountNumber();
        boolean deleted = manager.deleteAccount(number);
        System.out.println(deleted ? "Account deleted successfully!" : "Account deletion failed!");
    }

    private  void handleDeposit  (){
        String number= askForExistingAccountNumber();
        double amount = InputHelper.readDouble(scanner, "Deposit amount ");
        boolean deposited = manager.deposit(number, amount);
        System.out.println(deposited ? "Account deposited successfully!" : "Account deposition failed!");
    }


    private void handleWithdraw(){
        String number = askForExistingAccountNumber();
        double amount = InputHelper.readDouble(scanner, "Withdraw amount ");
        boolean withdrawn = manager.withdraw(number, amount);
        System.out.println(withdrawn ? "Withdrawal successful." : "Not enough balance.");
    }

    private void handleGetBalance(){
        String number = askForExistingAccountNumber();
        Double balance = manager.getBalance(number);
        System.out.println("Account balance: " + String.format("%.2f", balance));
    }


    private void handleShowAccount(){
        String number = askForExistingAccountNumber();
        System.out.println(manager.showAccount(number));
    }

    private  void handleShowAllAccounts(){
        String accounts = manager.showAllAccounts();
        System.out.println(accounts);
    }

    private  void handleShowBranches() {
        System.out.println(manager.showAllBranches());
    }




}
