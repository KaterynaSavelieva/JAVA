package LE_06_04_RGB_CMY;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static boolean isValidColor(String color) {
        if (color == null) return false;
        if(!color.startsWith("#")) return false;

        int len = color.length();
        if (len !=4 && len!=5 && len!=7 && len!=9) return false;

        String hex = color.substring(1);
        if (!hex.matches("[0-9a-fA-F]+")) return false;

        return true;
    }

    public static String  inputColor () {
        Scanner input = new Scanner(System.in);
        String color;

        while(true) {
            System.out.print("Enter color: ");
            color = input.nextLine();
            if(isValidColor(color)) {
                return color.toUpperCase();
            }else  {
                System.out.println("Invalid color. Please try again.");
            }
        }
    }

    public static int[] hexRgb (String color) {
        String rr, gg, bb;
        //rr=gg=bb=null;
        if(color.length() == 4 || color.length() == 5 ) {
            rr =color.substring(1,2)+color.substring(1,2);
            gg = color.substring(2,3)+color.substring(2,3);
            bb = color.substring(3,4)+color.substring(3,4);
        }

        else {
            rr = color.substring(1,3);
            gg = color.substring(3,5);
            bb = color.substring(5,7);
        }

        int r = Integer.parseInt(rr, 16);
        int g = Integer.parseInt(gg,16);
        int b =Integer.parseInt(bb, 16);

        return new int [] {r,g,b};
    }

    public static double rgbToCmy (int colorRgb) {
        return (255-colorRgb)/255.0*100.0;
    }

    public static void print(String color){
        int [] rgb = hexRgb(color);

        double c = rgbToCmy(rgb[0]);
        double m = rgbToCmy(rgb[1]);
        double y = rgbToCmy(rgb[2]);

        System.out.printf("RGB: %s\n", Arrays.toString(rgb));
        System.out.printf("CMY %.2f%% %.2f%% %.2f%%\n", c, m, y);

    }

    public  static  void main(String [] args) {
        Scanner input = new Scanner(System.in);
        while (true) {
            print(inputColor());
            System.out.println("\nDo you want to check another color? (0 to exit): ");
            if(input.nextLine().equals("0")) {
                System.out.println("Bye!\n");
                break;
            };
        }
    }
}
