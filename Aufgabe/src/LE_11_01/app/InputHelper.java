package LE_11_01.app;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputHelper {

    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("yyyy-M-d");

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
            String s = scanner.nextLine();
            if (!s.isBlank()) return s.trim();
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
                if (a.equals(s)) return a;
            }
            System.out.println("Invalid value. Allowed: " + String.join("/", allowed));
        }
    }

    public static LocalDate readOptionalDate(Scanner scanner, String msg) {
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
                return LocalDate.parse(input, DATE_FMT);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date! Use yyyy-MM-dd (e.g. 2025-01-15) or press Enter to skip.");
            }
        }
    }

    public static LocalDate readDate(Scanner scanner, String msg) {
        while (true) {
            System.out.print(msg);
            String input = scanner.nextLine().trim();


            try {
                input = input.replace(" ", "-")
                        .replace("/", "-")
                        .replace(":", "-")
                        .replace(".", "-")
                        .trim();

                LocalDate date  = LocalDate.parse(input, DATE_FMT);
                if (date.isAfter(LocalDate.now())) {
                    System.out.println("Date cannot be in the future. Please try again.");
                    continue;
                }

                return date;

            } catch (DateTimeParseException e) {
                System.out.println("Invalid date! Use yyyy-MM-dd (e.g. 2025-01-15)");
            }
        }
    }

    public static int readIntRange (Scanner scanner, String message, int min, int max) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            try {
                int num = Integer.parseInt(input);
                if (num >= min && num <= max) return num;
            }catch (NumberFormatException ignored) {}
            System.out.println("Input must be between " + min + " and " + max);
        }
    }

    public static String readEmail (Scanner scanner, String msg) {
        while (true) {
            System.out.print(msg);
            String email = scanner.nextLine().trim();

            // very simple email validation
            // Simple email validation regex:
// ^            -> start of the string
// [^@\\s]+     -> one or more characters that are NOT '@' and NOT whitespace
// @            -> exactly one '@' symbol
// [^@\\s]+     -> one or more characters (domain name)
// \\.          -> a dot '.'
// [^@\\s]+     -> one or more characters (top-level domain, e.g. com, at)
// $            -> end of the string
            if (email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
                return email;
            }

            System.out.println("Please enter a valid email address (example: name@mail.com).");
        }
    }
}
