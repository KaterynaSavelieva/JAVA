package LE_04_03;
import utils.InputUtils;
import  java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = InputUtils.isPositiveInt(scanner, "Enter the number of people participating in the survey: ");
        int [] ratings;
        ratings = new int[n];

        int evaluation [] = {1,2,3}; // масив з оцінками
        int count=0; // сума всіх оцінок

        Random rand = new Random();// генеруємо
        for (int i = 0; i < n; i++) {
            int randomNumber = rand.nextInt(evaluation.length);
            int randomValue = evaluation[randomNumber];

            ratings[i] = randomValue;
            System.out.printf("Customer %d rated %d ", i+1, randomValue);
            System.out.println();
            count =count+randomValue;
        }

        int count1, count2, count3;
        count1=count2=count3=0;

        for (int i = 0; i < ratings.length; i++) {
            if (ratings[i] == 1) count1++;
            else if (ratings[i] == 2) count2++;
            else if (ratings[i] == 3) count3++;
        }

        float rating_avr =  (float)count/n;
        float rating_avr_prc = rating_avr/evaluation.length*100;

        System.out.printf("\nAverage rating %.2f, %.2f %%\n", rating_avr, rating_avr_prc);
        System.out.println("-".repeat(39));
        System.out.printf("%-15s %10s %12s\n", "Evaluation", "Quantity","Percentage" );
        System.out.println("-".repeat(39));
        System.out.printf("%-15s %10d %11.2f%%\n", "Not recommended", count1, (float)  count1/ratings.length*100);
        System.out.printf("%-15s %10d %11.2f%%\n", "Acceptable", count2, (float)  count2/ratings.length*100);
        System.out.printf("%-15s %10d %11.2f%%\n", "Excellent", count3, (float)  count3/ratings.length*100);
        System.out.println("-".repeat(39));
        System.out.printf("%-15s %10d %11.2f%%\n", "Total", count3+count1+count2, (float)  (count3+count1+count2)/ratings.length*100);
    }
}
