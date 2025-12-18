package _LEK2;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static ArrayList<Double> inputPrices (Scanner scanner) {
        ArrayList<Double> prices = new ArrayList<>();
        while (true) {
            System.out.print("Enter price (0 to finish): ");

            if (!scanner.hasNextDouble()) {
                System.out.println("Please enter a valid price (for example 2.50)");
                scanner.next();
                continue;
            }

            double price = scanner.nextDouble();
            if (price == 0) {
                break;
            }

            if (price < 0) {
                System.out.println("Error: Price should be > 0.");
                continue;
            }
            prices.add(price);
        }
        return prices;
    }

    private static void printCheck(ArrayList<Double> prices) {

        if (prices.size() == 0) {
            System.out.println("There are no items in the list. Bye!");
            return;
        }

        double sum = sumCalc(prices);
        double min = minCalc(prices);
        double max = maxCalc(prices);
        double avg = avgCalc(prices);

        double discountLevel = discountPercentageCal(prices);
        double discount = discountCalc(prices);
        double extraDiscount = discountExtraCalc(prices);
        double finalTotal = totalCalc(prices);

        System.out.println("\n------------------RECEIPT------------------");

        System.out.println("Items:");
        for (int i = 0; i < prices.size(); i++) {
            System.out.printf(" %2d) %.2f EU\n", (i + 1), prices.get(i));
        }

        printSeparator();
        printData("Number of items:", prices.size(), "");
        printData("Subtotal of all items:", sum, "EU");
        printData("Least expensive item:", min,"EU");
        printData("Most expensive item:", max,"EU");
        printData("Average item price:", avg,"EU");
        printData("DiscountLevel", discountLevel,"%");
        printData("Discount", discount,"EU");
        printData("Special discount (>=6 items):", extraDiscount,"EU");
        printSeparator();
        printData("FINAL AMOUNT:", finalTotal,"EU");
        printSeparator();
    }

    private static double sumCalc(ArrayList<Double> prices) {
        double sum = 0;
        for (double price : prices) {
            sum += price;
        }
        return sum;
    }

    private static double minCalc(ArrayList<Double> prices) {
        double min = prices.get(0);
        for (double price : prices) {
            if (price < min) {
                min = price;
            }
        }
        return min;
    }

    private static double maxCalc(ArrayList<Double> prices) {
        double max = prices.get(0);
        for (double price : prices) {
            if (price > max) {
                max = price;
            }
        }
        return max;
    }

    private static double avgCalc(ArrayList<Double> prices) {
        return sumCalc(prices) / prices.size();
    }

    private static double discountPercentageCal(ArrayList<Double> prices) {
        double sum = sumCalc(prices);

        if (sum < 50) {
            return 0;
        } else if (sum < 100) {
            return 5;
        } else if (sum < 200) {
            return 10;
        } else {
            return 15;
        }
    }

    private static double discountCalc(ArrayList<Double> prices) {
        double sum = sumCalc(prices);
        double discountLevel = discountPercentageCal(prices);
        return sum * (discountLevel / 100.0);
    }

    private static double discountExtraCalc(ArrayList<Double> prices) {
        if (prices.size() >= 6) {
            return 20.0;
        }
        return 0.0;
    }

    private static double totalCalc(ArrayList<Double> prices) {
        double sum = sumCalc(prices);
        double discount = discountCalc(prices);
        double extraDiscount = discountExtraCalc(prices);

        double finalTotal = sum - discount - extraDiscount;
        if (finalTotal < 0) finalTotal = 0;

        return finalTotal;
    }

    private static void printData(String message, double value, String separator) {
        System.out.printf("| %-29s | %7.2f %2s|\n", message, value, separator);
    }

    private static void printData (String message, int value, String separator) {
        System.out.printf("| %-29s | %7d %2s|\n", message, value, separator);
    }

    private static void printSeparator   () {
        System.out.println("-".repeat(45));
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=== Welcome to Kwik-E-Mart ===");
        System.out.println("Enter prices one by one.");
        System.out.println("Enter 0 to finish.");
        printSeparator();

        ArrayList<Double> prices = inputPrices(scanner);
        printCheck(prices);

        scanner.close();
    }
}
