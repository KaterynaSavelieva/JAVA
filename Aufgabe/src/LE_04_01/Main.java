package LE_04_01;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        char [] spaces = {' '};
        char [] vowels = {'A', 'E', 'I', 'O', 'U', 'Y', 'a', 'e', 'i', 'o', 'u', 'y'};
        char [] consonants = {'B','C','D','F','G','H','J','K','L','M','N','P','Q','R','S','T','V','W','X','Z',
                'b','c','d','f','g','h','j','k','l','m', 'n','p','q','r','s','t','v','w','x','z'};
        char [] punctuation_marks = {'.', ',', '?', '!', ';', ':', '-'};

        char [] text;
        while (true) {
            System.out.print("Enter a text: ");
            text = scanner.nextLine().toCharArray();

            if (text.length==0) {
                System.out.println("Error: Text cannot be empty");
            } else {
                break;
            }
        }

        int count_spaces = 0;
        int count_vowels = 0;
        int count_consonants = 0;
        int count_punctuation_marks = 0;

        for (int i = 0; i < text.length; i++) {
            for (int j=0; j < spaces.length; j++) {
                if (text[i] == spaces[j]) {
                    count_spaces++;
                }
            }
            for (int v=0; v< vowels.length; v++) {
                if (text[i]==vowels[v]) {
                    count_vowels++;
                }
            }
            for (int c=0; c<consonants.length; c++) {
                if (text[i]==consonants[c]) {
                    count_consonants++;
                }
            }
            for (int p=0; p<punctuation_marks.length; p++) {
                if (text[i]==punctuation_marks[p]) {
                    count_punctuation_marks++;
                }
            }
        }

        System.out.printf("The text is %d signs long. There are %d spaces, %d vowels, %d consonants, %d punctuation_marks in the text.",text.length, count_spaces, count_vowels,count_consonants,count_punctuation_marks);

    }
}

