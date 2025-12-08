package LE_05_02;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String ipAddress;

        while (true) {
            System.out.print("Enter IP address (or 0 to exit): ");
            ipAddress = scanner.nextLine();

            if (ipAddress.equals("0")) {
                System.out.println("Bye!");
                break;
            }

            int pointCount = 0;
            for (int point = 0; point < ipAddress.length(); point++) {
                if (ipAddress.charAt(point) == '.') {
                    pointCount++;
                }
            }
            if (pointCount != 3) {
                System.out.println("Error: IP address  must contain exactly 3 points! ");
                continue;
            }

            int i = ipAddress.indexOf(".");
            int j = ipAddress.indexOf(".", i + 1);
            int k = ipAddress.indexOf(".", j + 1);

            String part1 = ipAddress.substring(0, i);
            String part2 = ipAddress.substring(i + 1, j);
            String part3 = ipAddress.substring(j + 1, k);
            String part4 = ipAddress.substring(k + 1);

            int first = parseIntRange(part1, 0, 255, "first part");
            int second = parseIntRange(part2, 0, 255, "second part");
            int third = parseIntRange(part3, 0, 255, "third part");
            int fourth = parseIntRange(part4, 0, 255, "fourth part");

            if (first == -1 || second == -1 || third == -1 || fourth == -1) {
                System.out.println("Please try again.\n");
                continue;
            }

            String decimal = String.format("%d.%d.%d.%d", first, second, third, fourth);
            String hex = String.format("%02X%02X%02X%02X", first, second, third, fourth);
            String octal = String.format("%03o%03o%03o%03o", first, second, third, fourth);
            String binary = Integer.toBinaryString(first) + Integer.toBinaryString(second) + Integer.toBinaryString(third) + Integer.toBinaryString(fourth);
            String binary1 = to8BitBinary(first) + to8BitBinary(second) + to8BitBinary(third) + to8BitBinary(fourth);

            System.out.printf("Decimal: %s\n", decimal);
            System.out.printf("Hex: %s\n", hex);
            System.out.printf("Octal: %s\n", octal);
            System.out.printf("Binary: %s\n", binary);
            System.out.printf("Binary: %s\n", binary1);
        }
    }




    // helper: convert number to 8-bit binary string
    private static String to8BitBinary(int value) {
        return String.format("%8s", Integer.toBinaryString(value))
                .replace(' ', '0');  // replace spaces with zeros
    }

    private static int parseIntRange (String part, int min, int max, String name) {
        int value;
        try {
            value = Integer.parseInt(part);
        } catch (NumberFormatException e) {
            System.out.printf("Error: %s '%s' must be an integer between %d and %d!\n", name, part, min, max);
            return -1;
        }

        if (value < min || value > max) {
            System.out.printf("Error: %s '%s' must be an integer between %d and %d!\n", name, part, min, max);
            return -1;
        }

        return value;
    }


}
