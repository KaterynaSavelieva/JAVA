package utils;
import java.util.Scanner;

public class InputUtils {

    public static int checkInt(Scanner scanner, String message) {
        int value;
        while (true) {
            System.out.print(message);

            if (scanner.hasNextInt()){
                value = scanner.nextInt();
                if (isPositive(value)) {
                    return value;
                }
                System.out.println("Error: value should be >0");
            }else  {
                System.out.println("Error: enter a  integer!");
                scanner.next();
            }
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

    public static boolean isPositive (double value) {
       return value > 0;
    }
}
