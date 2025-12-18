package LE_07_01_time_date;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static LocalTime inputTime(Scanner scanner, String message) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime userTime =  null;

        while (true) {
            System.out.println(message);
            String userInput = scanner.nextLine();
            try {
                userInput = userInput.replace(" ", ":")
                        .replace("-", ":")
                        .replace("/", ":")
                        .trim();
                //userInput = userInput.replaceAll("[ \\-\\/]", ":");
                userTime = LocalTime.parse(userInput.trim(),formatter);
                System.out.println("Time accepted: " +userTime);
                return userTime;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid time! Please use the format HH:mm (e.g. 09:30).");
            }
        }
    }

    public static LocalDate inputDate(Scanner scanner, String message) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
        LocalDate userDate = null;

        while (true) {
            System.out.println(message);
            String userInput = scanner.nextLine();
            try {
                userInput = userInput.replace(" ", "-")
                        .replace("/", "-")
                        .replace(":", "-")
                        .replace(".", "-")
                        .trim();
                userDate = LocalDate.parse(userInput.trim(),formatter);
                System.out.println("Date accepted: " +userDate);
                return userDate;
            }catch (DateTimeParseException e){
                System.out.println("Invalid date! Please use the format yyyy-MM-dd (e.g. 2025-01-15).");
            }
        }
    }

    public static Duration durationTime(LocalTime start, LocalTime finish) {
        int startMinutes = start.getHour()*60 +start.getMinute();
        int finishMinutes = finish.getHour()*60 +finish.getMinute();

        int diffMinutes;
        if (startMinutes > finishMinutes) {
            diffMinutes = 24*60-startMinutes+finishMinutes;
        }else {
            diffMinutes = finishMinutes-startMinutes;
        }
        return Duration.ofMinutes(diffMinutes);
    }

    public static long durationDays(LocalDate start, LocalDate finish, Scanner scanner) {
        while (finish.isBefore(start)) {
            System.out.println("The delivery date cannot be earlier than the order date.");
            System.out.print("Please enter a valid delivery date (yyyy-MM-dd): ");
            finish = inputDate(scanner, "");   // ask again
        }
        long dayStart = start.toEpochDay();
        long dayEnd = finish.toEpochDay();
        return dayEnd - dayStart;
    }

    public static Period periodBetween (LocalDate start, LocalDate finish) {
        return Period.between(start, finish);
    }

    public static String durationMessage (String message, LocalTime start, LocalTime finish) {
        Duration duration = durationTime(start, finish);
        long hours = duration.toHours();
        long minutes = duration.toMinutes()%60;
        return String.format("%s %02d hours  %02d minutes", message, hours, minutes);
    }

    public static void timeCalc (Scanner scanner) {
        System.out.println("=== Work Time Calculation ===");
        LocalTime startTime = inputTime(scanner, "Please enter the start time (HH:mm): ");
        LocalTime finishTime = inputTime(scanner, "Please enter the end time (HH:mm): ");
        String workTime= durationMessage("Total work time", startTime, finishTime);
        System.out.println(workTime);
    }

    public static void dateCalc (Scanner scanner) {
        System.out.println("\n=== Order and Delivery Dates ===");
        LocalDate startDay = inputDate(scanner, "Please enter the order date (yyyy-MM-dd): ");
        LocalDate finishDay = inputDate(scanner, "Please enter the delivery date (yyyy-MM-dd): ");
        long waiting= durationDays(startDay,finishDay,scanner);
        System.out.println("Processing time: " + waiting+"days");

        Period p = Period.between(startDay,finishDay);
        String formatted =formatPeriod(p);
        System.out.println("Which is: " + formatted);

    }

    public static String formatPeriod (Period period) {
        StringBuilder result = new StringBuilder();

        if (period.getYears() > 0) {
            result.append(period.getYears() + " years ");
        }
        if (period.getMonths() > 0) {
            result.append(period.getMonths() + " months ");
        }
        if (period.getDays() > 0) {
            result.append(period.getDays() + " days ");
        }
        if (result.length() == 0) {
            return "0 days";
        }
        return result.toString().trim();
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Time Calculations ===");
            System.out.println("1: Work time calculation");
            System.out.println("2: Order and delivery duration");
            System.out.println("3: Exit");
            System.out.print("Enter your choice (1-3): ");

            String inputChoice = scanner.nextLine();

            int choice;
            try { choice = Integer.parseInt(inputChoice);

            } catch (NumberFormatException e){
                System.out.println("Wrong choice! Please enter 1, 2 or 3.");
                continue;
            }



            if (choice == 3) {
                System.out.println("Bye!");
                break;
            }

            if (choice <1||choice>3) {
                System.out.println("Wrong choice! Please enter 1, 2 or 3.");
                continue;
            }
            switch (choice) {
                case 1: timeCalc(scanner);
                    break;
                case 2: dateCalc(scanner);
                    break;
            }
        }
    }
}