package LE_09_03.app;

import java.util.Scanner;

public class InputHelper {

    private InputHelper() { }

    public  static int readInt (Scanner scanner, String message) {
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

    public  static double readDouble(Scanner scanner, String message) {
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

    public  static String readString(Scanner scanner, String message) {
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
