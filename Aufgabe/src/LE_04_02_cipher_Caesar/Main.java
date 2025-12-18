package LE_04_02_cipher_Caesar;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char []text_original;
        char [] text_new;
        char[][] letters = {{'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'},
                {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'}};
        char[][] letters_new;

        while(true){
            System.out.print("Enter text: ");
            text_original = scanner.nextLine().toCharArray();
            if(text_original.length == 0){
                System.out.println("Error: Text cannot be empty");
            } else {
                break;
            }
        }

        int length = letters[0].length;
        letters_new =new char[2][length];
        final int STEP = 2;

        for(int i=0;i<length;i++){
            letters_new[0][i] = letters [0][(i+STEP)%length];
            letters_new[1][i] = letters [1][(i+STEP)%length];
        }

        int text_length = text_original.length;
        text_new= new char [text_length];

        for (int k=0;k<text_length;k++){
            char original = text_original[k];
            char encrypted = original;

            for(int row=0; row<2; row++){
                for (int i=0; i<length; i++){
                    if (original==letters[row][i]){
                        encrypted = letters_new[row][i];
                        break;
                    }
                }
            }
            text_new[k] = encrypted;
        }

        System.out.println("Text original: " + new String(text_original));
        System.out.println("Encrypted text: " + new String(text_new));
    }
}