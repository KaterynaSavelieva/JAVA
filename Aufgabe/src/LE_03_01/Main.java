package LE_03_01;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String name;
        while (true) {
            System.out.print("Enter the name of Product: ");
            name = scanner.nextLine().trim().toUpperCase();

            if (name.isEmpty()) {
                System.out.println("Error: Name cannot be empty");
            } else {
                break;
            }
        }

        int quantity;
        while (true) {
            System.out.print("Enter the quantity of Product: ");

            if (scanner.hasNextInt()) {
                quantity = scanner.nextInt();

                if (quantity <= 0) {
                    System.out.println("Error: Quantity must be greater than 0");
                }  else {
                    break;
                }
            } else {
                System.out.println("Error: Quantity must be a positive integer");
                scanner.next();
            }
        }

        scanner.nextLine();    // clean buffer

        float price;
        while (true) {
            System.out.print("Enter the price of Product: ");

            if (scanner.hasNextFloat()) {
                price = scanner.nextFloat();
                if (price <= 0) {
                    System.out.println("Error: Price cannot be negative");
                } else {
                    break;
                }
            } else {
                System.out.println("Error: Price must be a positive number");
                scanner.next();
            }
        }

        System.out.printf("Product %s, %.2f EU/pcs, %d pcs reserved. Total: %.2f EUR.%n", name,price, quantity, price*quantity);
    }
}