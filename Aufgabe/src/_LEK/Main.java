package _LEK;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static void printCheck () {


    }
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Double> prices = new ArrayList<>();

        System.out.println("=== Wellcome to Kwik-E-Mart ===");
        System.out.println("Enter prices one by one.");
        System.out.println("Enter 0 to finish.\n");


        while (true) {
            System.out.print("Enter price: ");

            if (!scanner.hasNextDouble()) {
                System.out.println("Please enter a valid price (for example 2,50)");
                scanner.next();
                continue;
            }

            double price = scanner.nextDouble();

            if (price == 0) {
                break;
            }

            if (price < 0) {
                System.out.println("Error: Price should be >0.");
                continue;
            }
            prices.add(price);
        }

        if (prices.size() == 0) {
            System.out.println("There are no items in the list. Bye!");
            scanner.close();
            return;
        }

        System.out.println("\n---------------RECEIPT---------------");


        for (int i = 0; i < prices.size(); i++) {
            System.out.printf("%d. %.2f\n", i+1, prices.get(i));
        }
        int amount = prices.size();

        System.out.println("-------------------------------------");
        System.out.printf("%-35s %d\n", "Number of items ", amount);

        double sum = 0;
        double min = prices.get(0);
        double max = prices.get(0);
        for (int i = 1; i < prices.size(); i++) {
            sum += prices.get(i);
            if (prices.get(i) < min) {
                min = prices.get(i);
            }
            if (prices.get(i) > max) {
                max = prices.get(i);
            }
        }
        double avg = sum / prices.size();



        System.out.printf("%-28s %7.2f %2s\n", "Subtotal of all items", sum, "EU");
        System.out.printf("%-28s %7.2f %2s\n", "Least expensive item", min, "EU");
        System.out.printf("%-28s %7.2f %2s\n", "Most expensive item", max, "EU");
        System.out.printf("%-28s %7.2f %2s\n", "Average item price", avg, "EU");


        double discountLevel;
        if (sum<50) {
            discountLevel = 0;
        }
        else if (sum<100) {
            discountLevel = 5;
        }
        else if (sum<200) {
            discountLevel = 10;
        }
        else {
            discountLevel = 15;
        }

        double discount = (double) sum* (discountLevel/100);
        System.out.printf("%-10s%.0f%s %20.2f %2s\n", "Discount (", discountLevel, "%) ", discount, "EU");


        double extraDiscoint = 0;
        if (prices.size() >= 6) {
            extraDiscoint = 20;
            System.out.printf("%-28s %7.2f %2s\n", "Special discount (>=6items)", extraDiscoint, "EU");
        } else {
            System.out.printf("%-33s %2s\n", "Special discount:",  "none");
        }


        double finalTotal= sum-extraDiscoint-discount;
        System.out.println("----------------------------------------");
        System.out.printf("%-28s %7.2f %2s\n", "Final amount", finalTotal, "EU");
        System.out.println("----------------------------------------");

        scanner.close();

    }
}
