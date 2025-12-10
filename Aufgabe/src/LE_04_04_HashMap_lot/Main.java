package LE_04_04_HashMap_lot;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        int n= (int)(Math.random()*100)+1;
        System.out.printf("Number of lottery draws %d\n",n);

        final int MAX= 45;
        final int MIN= 1;
        System.out.printf("Numbers that can fall out are from %d to %d \n", MIN, MAX);

        int k =0;
        HashMap<Integer, Integer> results = new HashMap<>();

        for (int i = 1; i<=MAX; i++) {
            results.put(i, k);
        }

        for (int n1=0;n1<n;n1++) {
            int j = (int)(Math.random()*MAX)+1;
            for (int i = 1; i<=MAX; i++) {
                if (i==j) {
                    k= results.get(i);
                    k++;
                    results.put(i,k);
                }
            }
        }

        float percentage =0;
        System.out.printf("%-8s %10s %12s\n", "Number", "Quantity", "Percentage" );
        for (int variant: results.keySet()) {
            percentage += (float)results.get(variant)/n*100;
            System.out.printf("%-8d %10d %11.2f%%\n", variant, results.get(variant), (float)results.get(variant)/n*100);
        }

        System.out.println("-".repeat(33));
        System.out.printf("%-8s %10d %11.2f%%\n", "Total", n, percentage );
        System.out.println(results);
    }
}