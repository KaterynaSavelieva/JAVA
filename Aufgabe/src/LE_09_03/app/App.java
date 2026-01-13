package LE_09_03.app;
import LE_09_03.management.AccountManagement;
import LE_09_03.model.Branch;
import LE_09_03.model.Company;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Company company = DemoData.createCompanyWithBranches();
        AccountManagement manager = new AccountManagement(company);
        DemoData.initDemoData(manager);

        ConsoleUI ui = new ConsoleUI(scanner, manager);
        ui.start();
    }
}