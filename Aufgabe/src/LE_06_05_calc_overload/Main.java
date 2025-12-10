package LE_06_05_calc_overload;
import java.util.Scanner;

public class Main {

    public static void main(String [] args) {
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println("\n======= Calculator: ======= ");
            System.out.println("---- Choose operation -----");
            System.out.println("1 - Addition: ");
            System.out.println("2 - Subtraction: ");
            System.out.println("3 - Division: ");
            System.out.println("4 - Multiplication: ");
            System.out.println("5 - Exit: ");
            System.out.print("Your choice: ");

            int operation = scanner.nextInt();
            scanner.nextLine();

            if(operation == 5) {
                System.out.println("Bye!");
                break;
            }
            if (operation <1||operation>5) {
                System.out.println("Wrong operation!");
                continue;
            }
            Number[] numbers = inputNumbers(scanner);
            typeCalculate(operation, numbers);
        }
    }

    public static Number checkNumber(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            try {
                String input = scanner.nextLine().trim();
                if (input.contains(".")||input.contains(",")) {
                    input = input.replace(",", ".");
                    return Float.parseFloat(input);
                }
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number!");
            }
        }
    }

    public static Number [] inputNumbers(Scanner scanner) {
        Number n1 =checkNumber(scanner, "Please enter the first number: ");
        Number n2 =checkNumber(scanner, "Please enter the second number: ");
        return new Number[]{n1,n2};
    }

    public static void typeCalculate(int operation, Number[] numbers) {
        boolean bothInt =numbers[0] instanceof Integer && numbers[1] instanceof Integer;
        if (bothInt) {
            int a = numbers[0].intValue();
            int b = numbers[1].intValue();

            switch (operation) {
                case 1:
                    addition(a, b);
                    break;
                case  2:
                    subtraction(a, b);
                    break;
                case 3:
                    division(a, b);
                    break;
                case 4:
                    multiplication(a, b);
                    break;
                default:
                    System.out.println("Unknown operation!");
            }
        }else {
            float a = numbers[0].floatValue();
            float b = numbers[1].floatValue();
            switch (operation) {
                case 1:
                    addition(a, b);
                    break;
                case 2:
                    subtraction(a, b);
                    break;
                case 3:
                    division(a, b);
                    break;
                case 4:
                    multiplication(a, b);
                    break;
                default:
                    System.out.println("Unknown operation");
            }
        }
    }


    public static int addition (int a, int b){
        int result = a+b;
        System.out.println("Result: " + a + " + " + b + " = " +result);
        return result;
    }
    public static int subtraction(int a, int b){
        int result = a-b;
        System.out.println("Result: " + a + " - " + b + " = " +result);
        return result;
    }
    public static int multiplication(int a, int b){
        int result = a*b;
        System.out.println("Result: " + a + " * " + b + " = " +result);
        return result;
    }
    public static int division(int a, int b){
        if(b==0){
            System.out.println("Error: Division by zero is not allowed");
            return 0;
        }
        int result = a/b;
        System.out.println("Result: " + a + " / " + b + " = " +result);
        return result;
    }

    public static float addition(float a, float b){
        float result = a+b;
        System.out.println("Result: " + a + " + " + b + " = " +result);
        return result;
    }
    public static float subtraction(float a, float b){
        float result = a-b;
        System.out.println("Result: " + a + " - " + b + " = " +result);
        return result;
    }
    public static float multiplication(float a, float b){
        float result = a*b;
        System.out.println("Result: " + a + " * " + b + " = " +result);
        return result;
    }
    public static float division(float a, float b){
        if(b==0){
            System.out.println("Error: Division by zero is not allowed");
            return 0;
        }
        float result = a/b;
        System.out.println("Result: " + a + " / " + b + " = " +result);
        return result;
    }

}
