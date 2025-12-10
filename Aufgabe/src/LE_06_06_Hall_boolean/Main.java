package LE_06_06_Hall_boolean;
import java.util.Random;

public class Main {
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        int rows = 15;
        int cols = 10;

        // true = free seat, false = occupied
        boolean[][] hall = generateHall(rows, cols);
        int[] freeSeatsRow = calcFreeSeatsRow(hall);
        int freeSeatsTotal = calcFreeSeatsTotal(freeSeatsRow);
        int totalSeats = rows * cols;

        int[] maxFreeConsecutive = calcMaxFreeSeatsConsectiveRow(hall);

        printHall(hall, freeSeatsRow);
        System.out.println();
        printHall2(hall, freeSeatsRow, maxFreeConsecutive);

        System.out.println();
        System.out.println("Total seats: " + totalSeats);
        System.out.println("Total free seats: " + freeSeatsTotal);
        float occupancy = occupancy(totalSeats, freeSeatsTotal);
        System.out.printf("Total occupancy: %.2f%%\n", occupancy * 100);
    }

    private static boolean[][] generateHall(int rows, int cols) {
        boolean[][] hall = new boolean[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                hall[row][col] = RANDOM.nextBoolean();
            }
        }
        return hall;
    }

    private static int[] calcFreeSeatsRow(boolean[][] hall) {
        int[] freeSeats = new int[hall.length];
        for (int row = 0; row < hall.length; row++) {
            for (int col = 0; col < hall[row].length; col++) {
                if (hall[row][col]) {
                    freeSeats[row] = freeSeats[row] + 1;
                }
            }
        }
        return freeSeats;
    }

    private static int calcFreeSeatsTotal(int[] freeSeatsRow) {
        int total = 0;
        for (int seats : freeSeatsRow) {
            total += seats;
        }
        return total;
    }

    private static void printHall(boolean[][] hall, int[] freeSeatsRow) {
        System.out.printf("%-8s", "Seat");
        for (int col = 0; col < hall[0].length; col++) {
            System.out.printf("%s %d  ", "Seat", col + 1);
        }
        System.out.println("Free seats:");

        for (int row = 0; row < hall.length; row++) {
            System.out.printf("%-3s %2d  ", "Row", row + 1);
            for (int col = 0; col < hall[row].length; col++) {
                System.out.printf("%-8s", hall[row][col]);
            }
            System.out.printf(" Free %d in row %d", freeSeatsRow[row], row + 1);
            System.out.println();
        }
    }

    private static void printHall2(boolean[][] hall, int[] freeSeatsRow, int[] maxFreeConsecutive) {
        System.out.printf("%-8s", "Seat");
        for (int col = 0; col < hall[0].length; col++) {
            System.out.printf("%-4s", "S." + (col + 1));
        }
        System.out.println(" Free seats / Max consecutive free:");

        for (int row = 0; row < hall.length; row++) {
            System.out.printf("%-3s %2d  ", "Row", row + 1);
            for (int col = 0; col < hall[row].length; col++) {
                if (hall[row][col]) {
                    System.out.printf("%-4s", "0");   // free
                } else {
                    System.out.printf("%-4s", "X");   // occupied
                }
            }
            System.out.printf(" Free %2d    | Max block: %d",
                    freeSeatsRow[row], maxFreeConsecutive[row]);
            System.out.println();
        }
    }

    private static int[] calcMaxFreeSeatsConsectiveRow(boolean[][] hall) {
        int[] maxFreeSeats = new int[hall.length];

        for (int row = 0; row < hall.length; row++) {
            int currentFreeSeats = 0;
            int maxCount = 0;

            for (int col = 0; col < hall[row].length; col++) {
                if (hall[row][col]) {          // free
                    currentFreeSeats++;
                    if (currentFreeSeats > maxCount) {
                        maxCount = currentFreeSeats;
                    }
                } else {                       // occupied → обнуляємо лічильник
                    currentFreeSeats = 0;
                }
            }
            maxFreeSeats[row] = maxCount;      // зберігаємо для поточного ряду
        }

        return maxFreeSeats;
    }

    private static float occupancy(int totalSeats, int freeSeats) {
        return (float) (totalSeats - freeSeats) / totalSeats;
    }
}
