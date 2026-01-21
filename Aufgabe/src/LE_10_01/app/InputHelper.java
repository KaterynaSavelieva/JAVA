package LE_10_01.app;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputHelper {

    public static void pressEnter(Scanner scanner) {
        System.out.println("\nPress ENTER to continue...");
        scanner.nextLine();
    }

    public static int readInt(Scanner scanner, String msg, int min, int max) {
        while (true) {
            System.out.print(msg);
            String s = scanner.nextLine().trim();
            try {
                int v = Integer.parseInt(s);
                if (v >= min && v <= max) return v;
            } catch (NumberFormatException ignored) {}
            System.out.println("Please enter a number between " + min + " and " + max + ".");
        }
    }

    public static int readPositiveInt(Scanner scanner, String msg) {
        while (true) {
            System.out.print(msg);
            String s = scanner.nextLine().trim();
            try {
                int v = Integer.parseInt(s);
                if (v > 0) return v;
            } catch (NumberFormatException ignored) {}
            System.out.println("Please enter a positive integer (> 0).");
        }
    }

    public static int readNonNegativeInt(Scanner scanner, String msg) {
        while (true) {
            System.out.print(msg);
            String s = scanner.nextLine().trim();
            try {
                int v = Integer.parseInt(s);
                if (v >= 0) return v;
            } catch (NumberFormatException ignored) {}
            System.out.println("Please enter a non-negative integer (>= 0).");
        }
    }

    public static double readNonNegativeDouble(Scanner scanner, String msg) {
        while (true) {
            System.out.print(msg);
            String s = scanner.nextLine().trim().replace(",", ".");
            try {
                double v = Double.parseDouble(s);
                if (v >= 0) return v;
            } catch (NumberFormatException ignored) {}
            System.out.println("Please enter a non-negative number (>= 0). Example: 12.5");
        }
    }

    public static String readNotBlank(Scanner scanner, String msg) {
        while (true) {
            System.out.print(msg);
            String s = scanner.nextLine().trim();
            if (!s.isBlank()) return s;
            System.out.println("Value must not be empty.");
        }
    }

    public static String readOptional(Scanner scanner, String msg) {
        System.out.print(msg);
        String s = scanner.nextLine().trim();
        return s.isBlank() ? null : s;
    }

    public static String readEnum(Scanner scanner, String msg, String[] allowed) {
        while (true) {
            System.out.print(msg);
            String s = scanner.nextLine().trim().toUpperCase();
            for (String a : allowed) {
                if (a.equals(s)) return s;
            }
            System.out.println("Invalid value. Allowed: " + String.join("/", allowed));
        }
    }

    public static LocalDate readOptionalDate(Scanner scanner, String msg) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");

        while (true) {
            System.out.print(msg);
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) return null;

            try {
                input = input.replace(" ", "-")
                        .replace("/", "-")
                        .replace(":", "-")
                        .replace(".", "-")
                        .trim();
                return LocalDate.parse(input, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date! Use yyyy-MM-dd (e.g. 2025-01-15) or press Enter to skip.");
            }
        }
    }
}
