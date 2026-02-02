package LE_11_01.app;

import LE_11_01.management.Management;

import java.sql.SQLException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Management management = DemoData.create ();

        ConsoleUi ui = new ConsoleUi(scanner, management);
        ui.run();

        scanner.close();
    }
}
