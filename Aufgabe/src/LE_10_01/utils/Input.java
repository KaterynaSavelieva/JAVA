package LE_10_01.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Input {
    public static LocalDate inputDate(Scanner scanner, String message) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");


        while (true) {
            System.out.print(message);
            String userInput = scanner.nextLine().trim();

            if (userInput.isEmpty()) {
                return null;
            }

            try {
                userInput = userInput.replace(" ", "-")
                        .replace("/", "-")
                        .replace(":", "-")
                        .replace(".", "-")
                        .trim();
                LocalDate userDate = LocalDate.parse(userInput,formatter);
                System.out.println("Date accepted: " +userDate);
                return userDate;

            }catch (DateTimeParseException e){
                System.out.println("Invalid date! Please use the format yyyy-MM-dd (e.g. 2025-01-15)." +
                        "Or press Enter to skip.");
            }
        }
    }
}
