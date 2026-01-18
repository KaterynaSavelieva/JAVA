package LE_09_04.app;

import LE_09_04.management.MusicManagement;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        MusicManagement management = DemoData.create();

        ConsoleUi ui = new ConsoleUi(scanner, management);
        ui.start();

        scanner.close();

    }
}
