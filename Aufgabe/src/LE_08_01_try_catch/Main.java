package LE_08_01_try_catch;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static int [] readLotoNumber (Scanner input) {
        int [] lotonumber = new int[6];
        for (int i =0; i<lotonumber.length; i++) {
            while (true) {
                System.out.printf("Enter number %d: ", i + 1);
                try {
                    int number = input.nextInt();

                    if (number < 1 || number > 45) {
                        System.out.println("Please enter a number between 1 and 45");
                        continue;
                    }

                    boolean found = false;
                    for (int j=0; j<i; j++) {
                        if (number==lotonumber[j]) {
                            found = true;
                            break;
                        }
                    }
                    if (found) {
                        System.out.println("The number " + number + " has already been entered.");
                        continue;
                    }

                    lotonumber[i] = number;
                    break;

                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please try again.");
                    input.nextLine();
                }
            }
        }
        Arrays.sort(lotonumber);
        return lotonumber;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int [] loto = readLotoNumber(input);

        System.out.println("Your loto numbers: " +  Arrays.toString(loto));

        input.close();
    }

}
