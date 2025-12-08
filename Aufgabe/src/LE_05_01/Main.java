package LE_05_01;
import java.util.Scanner;

public  class Main {

    public static boolean isValidHex(String hex) {
        if (!hex.startsWith("#")) return false;
        if (hex.length() != 7) return false;
        String allowed = "0123456789ABCDEFabcdef";

        for (int i = 1; i < 7; i++) {
            char c = hex.charAt(i);
            if (!allowed.contains(String.valueOf(c))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String color_hex;

        while (true) {
            System.out.print("Please enter HEX color (example: #BBFF6A): ");
            color_hex = scanner.nextLine();
            if (isValidHex(color_hex)) {
                break;
            } else {
                System.out.println("Error: Invalid HEX format. Use format #BBFF6A");
            }
        }

        System.out.println("HEX: " +color_hex.toUpperCase());

        String red = color_hex.substring(1,3);
        String green = color_hex.substring(3,5);
        String blue = color_hex.substring(5,7);

        int r = Integer.parseInt(red, 16);
        int g = Integer.parseInt(green, 16);
        int b = Integer.parseInt(blue, 16);

        String color_rgb = r+", "+g+", "+b;
        System.out.println("RGB: "+color_rgb);

    }
}