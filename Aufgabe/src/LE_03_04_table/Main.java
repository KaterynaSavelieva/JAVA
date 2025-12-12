package LE_03_04_table;
import utils.InputUtils;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        float priceMin = InputUtils.isPositiveFloat(scanner, "Enter minimum price: ");
        float priceMax = InputUtils.isPositiveFloat(scanner, "Enter maximum price: ");

        while (priceMin > priceMax) {
            System.out.printf("Maximum price must be greater than minimum price %.2f: \n", priceMin);
            priceMax = InputUtils.isPositiveFloat(scanner, "Enter maximum price: ");
        }

        double priceStep = Math.round(((priceMax-priceMin)/10)*100.0)/100.0;
        System.out.printf("Price step is $%f\n", priceStep);

        final int WIDTH = 140;
        System.out.println("-".repeat(WIDTH));
        System.out.println(" ".repeat((WIDTH-10)/2)+"Unit price"+" ".repeat((WIDTH-10)/2));
        System.out.println("-".repeat(WIDTH));

        System.out.printf("%8s |", "Qty");
        for (float price=priceMin; price<=priceMax; price+=priceStep) {
            System.out.printf("%10.2f |", price);
        }
        System.out.println();
        System.out.println("-".repeat(WIDTH));

        for (int quantity = 10; quantity <= 100; quantity+=10) {
            System.out.printf("%8d |", quantity);
            for (float price=priceMin; price<=priceMax; price+=priceStep) {
                float total = price*quantity;
                System.out.printf("%10.2f |", total);
            }

            System.out.println();
            System.out.println("-".repeat(WIDTH));
        }
    }
}

