import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        while (true) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Calculator");
            System.out.println("Choose operation : ");
            System.out.println("1 - Addition: ");
            System.out.println("2 - Subtraction: ");
            System.out.println("3 - Division: ");
            System.out.println("4 - Multiplication: ");
            System.out.println("5 - Exit: ");

            int operation = scanner.nextInt();

            if (operation == 5) {
                System.out.println("Exiting...");
                break;
            }

            System.out.print("Enter the first number : ");
            float firstNumber = scanner.nextFloat();

            System.out.print("Enter the second number : ");
            float secondNumber = scanner.nextFloat();

            switch (operation) {
                case 1:
                    System.out.println("Result: " + firstNumber + " + " + secondNumber + " = " +(firstNumber + secondNumber));
                    continue;
                case 2:
                    System.out.println("Result: " + firstNumber + " - " + secondNumber + " = " +(firstNumber - secondNumber));
                    continue;
                case 3:
                    if (secondNumber == 0) {
                        System.out.println("Error: Division by zero is not allowed");
                    } else {
                        System.out.println("Result: " + firstNumber + " / " + secondNumber + " = " +firstNumber / secondNumber);
                    }
                    continue;
                case 4:
                    System.out.println("Result: " + firstNumber + " * " + secondNumber + " = " +firstNumber * secondNumber);
                    continue;
                default:
                    System.out.println("Invalid input");
            }
            System.out.println();
        }
    }
}
