package LE_09_04.app;
import java.util.Scanner;
import LE_09_04.model.Genre;


public class InputHelper {
    private InputHelper (){};

    public static int readInt(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    public static String readString(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            if (!input.isBlank()) {
                return input;
            }
            System.out.println("Input cannot be empty.");
        }
    }

    public static Genre readGenre(Scanner scanner) {
        while (true) {
            System.out.print("Genre (ROCK/POP/JAZZ/HIPHOP/OTHER): ");
            String input = scanner.nextLine().trim().toUpperCase();
            try {
                return Genre.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid genre. Try again.");
            }
        }
    }


}
