package _LEK3;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

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

    private static ArrayList<Double> inputPrices (Scanner scanner) {
        ArrayList<Double> prices = new ArrayList<>();
        double price= 0;
        while (true) {

            System.out.print("Enter price (0 to finish): ");
            String userInput = scanner.nextLine();
            try {
                userInput = userInput.replace(",", ".").trim();
                price = Double.parseDouble(userInput);;
                if (price == 0) {
                    break;
                }
                if (price < 0) {
                    System.out.println("Error: Price should be > 0.");
                    continue;
                }
                prices.add(price);

            }catch (NumberFormatException e) {
                System.out.println("Please enter a valid price (for example 2,50)");
            }
        }
        return prices;
    }

    private static void printCheck(ArrayList<Double> prices) {

        if (prices.size() == 0) {
            System.out.println("There are no items in the list. Bye!");
            return;
        }

        System.out.println("\n------------------RECEIPT------------------");

        System.out.println("Items:");
        for (int i = 0; i < prices.size(); i++) {
            System.out.printf(" %2d) %.2f EU\n", (i + 1), prices.get(i));
        }

        printSeparator();
        printData("Number of items:", prices.size(), "");
        printData("Subtotal of all items:", sumCalc(prices), "EU");
        printData("Least expensive item:", minCalc(prices),"EU");
        printData("Most expensive item:", maxCalc(prices),"EU");
        printData("Average item price:", avgCalc(prices),"EU");
        printData("DiscountLevel", discountPercentageCal(prices),"%");
        printData("Discount", discountCalc(prices),"EU");
        printData("Special discount (>=6 items):", discountExtraCalc(prices),"EU");
        printSeparator();
        printData("FINAL AMOUNT:", totalCalc(prices),"EU");
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


}
