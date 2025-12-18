package LE_05_02_IP;
import java.util.Scanner;

public class IPConverter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter IPv4 address (e.g. 192.168.0.1): ");
        String ip = scanner.nextLine();

        String[] parts = ip.split("\\.");

        if (parts.length != 4) {
            System.out.println("Invalid IP format!");
            return;
        }

        System.out.println("\nResult:");
        System.out.println("Decimal    : " + ip);
        System.out.print("Binary     : ");
        printBinary(parts);
        System.out.print("Hex        : ");
        printHex(parts);

        scanner.close();
    }

    private static void printBinary(String[] parts) {
        for (int i = 0; i < parts.length; i++) {
            int value = Integer.parseInt(parts[i]);
            String binary = String.format("%8s",
                    Integer.toBinaryString(value)).replace(' ', '0');
            System.out.print(binary);
            if (i < 3) System.out.print(".");
        }
        System.out.println();
    }

    private static void printHex(String[] parts) {
        for (int i = 0; i < parts.length; i++) {
            int value = Integer.parseInt(parts[i]);
            String hex = String.format("%02X", value);
            System.out.print(hex);
            if (i < 3) System.out.print(".");
        }
        System.out.println();
    }
}



//String[] parts = ipAddress.split("\\.");
//
//if (parts.length != 4) {
//        System.out.println("Error: IP must contain 4 numbers separated by dots!");
//    continue;
//            }
//
//// перевіряємо кожну частину
//int[] nums = new int[4];
//for (int p = 0; p < 4; p++) {
//        try {
//nums[p] = Integer.parseInt(parts[p]);
//        if (nums[p] < 0 || nums[p] > 255) {
//        throw new NumberFormatException();
//        }
//                } catch (NumberFormatException e) {
//        System.out.println("Error: each part must be a number 0–255");
//        continue ipLoop;
//    }
//            }

