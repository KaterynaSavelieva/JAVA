package utils;
import java.util.Scanner;

public class InputUtils {

    public static boolean isPositive (double value) {
        return value > 0;
    }

    public static boolean isInterval (double value, double min, double max ) {
        return value >= min && value <= max;
    }

    public static int checkInt(Scanner scanner, String message) {
        int value;
        while (true) {
            System.out.print(message);
            if (scanner.hasNextInt()){
                value = scanner.nextInt();
                return value;
            }else  {
                System.out.println("Error: enter an integer!");
                scanner.next();
            }
        }
    }

    public static int isPositiveInt(Scanner scanner, String message) {
        while (true) {
            int value = checkInt(scanner, message);
            if (isPositive(value)) {
                return value;
            }
            System.out.println("Error: value should be >0");
        }
    }

    public static int inputIntInterval(Scanner scanner, String message, int min, int max) {
        while (true) {
            int value = checkInt(scanner, message);
            if (isInterval(value, min, max)) {
                return value;
            }
            System.out.println("Error: value should be in the interval between " + min + " and " + max + "!");
        }
    }

    public static float checkFloat(Scanner scanner, String message) {
        float value;
        while (true) {
            System.out.print(message);

            if (scanner.hasNextFloat()){
                value = scanner.nextFloat();
                return value;
            }else  {
                System.out.println("Error: Please enter a decimal number!");
                scanner.next();
            }
        }
    }

    public static float isPositiveFloat(Scanner scanner, String message) {
        while (true) {
            float value = checkFloat(scanner, message);
            if (isPositive(value)) {
                return value;
            }
            System.out.println("Error: value should be >0");
        }
    }

}


