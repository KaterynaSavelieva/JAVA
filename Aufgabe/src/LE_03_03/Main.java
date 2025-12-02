package LE_03_03;
import utils.InputUtils;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int start = InputUtils.checkInt(scanner, "Enter start number: ");
        int end = InputUtils.checkInt(scanner, "Enter end number: ");

        while (start > end) {
            System.out.printf("End number should be greater than start number %d\n", start);
            end = InputUtils.checkInt(scanner, "Enter end number: ");
        }

        int step = InputUtils.checkInt(scanner, "Enter step number: ");
        while (step > (end-start)) {
            System.out.printf("Step number should be less than %d (difference between end- and start- number)\n", end-start);
            step = InputUtils.checkInt(scanner, "Enter step number: ");
        }

        System.out.println("\n===== Generated list: ===== ");
        for (int i=start; i<=end; i+=step) {
            System.out.println(i);
        }
    }
}
