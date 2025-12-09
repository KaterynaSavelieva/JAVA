package LE_05_03;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Example: The product [Phone] was ordered by [Maria]\n");

        Scanner scanner = new Scanner(System.in);
        String text;

        while(true){
            System.out.print("Enter text (or 0 to exit): ");
            text = scanner.nextLine();

            if(text.equals("0")){
                System.out.println("Bye!");
                break;
            }

            int brOp1 = text.indexOf("[");
            int brCl1 = text.indexOf("]");
            if (brOp1 == -1 || brCl1 == -1 || brOp1 >= brCl1) {
                System.out.println("Error: Invalid input");
                System.out.println("Please enter a string in the following format:");
                System.out.println("   text[ARTICLE]text[CLIENT]");
                continue;
            }

            int brOp2 = text.indexOf("[", brCl1+1);
            int brCl2 = text.indexOf("]",brOp2+1);
            if (brOp2 == -1 || brCl2 == -1 || brOp2 >= brCl2 || brOp2 <= brCl1) {
                System.out.println("Please enter a string in the following format:");
                System.out.println("   text[ARTICLE]text[CLIENT]");
                continue;
            }

            String article = text.substring(brOp1+1, brCl1);
            String client = text.substring(brOp2+1, brCl2);
            System.out.printf("Product is %s\n", article);
            System.out.printf("Client is %s\n", client);

        }

    }
}
