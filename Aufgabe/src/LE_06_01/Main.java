package LE_06_01;

public class Main {
    public static float min(float[] arr) {
        float min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        return min;
    }

    public static float max(float[] arr) {
        float max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    public static float minTotal(float[][] arr) {
        float minTotal = arr[0][0];
        for (int i = 0; i < arr.length; i++) {
            for (int j=0; j < arr[i].length; j++) {
                if (arr[i][j] < minTotal) {
                    minTotal = arr[i][j];
                }
            }
        }
        return minTotal;
    }

    public static float maxTotal(float[][] arr) {
        float maxTotal = arr[0][0];
         for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] > maxTotal) {
                    maxTotal = arr[i][j];
                }
            }
        }
        return maxTotal;
    }

    public static void main(String[] args) {
        float DIAPASON = 500f;
        float [][] mySales = new float[10][10];

        System.out.printf("%7s"," ");
        for (int i = 0; i < 10; i++) {
            System.out.printf("Sale-%2d%2s", i+1, " ");
        }
        System.out.printf(" Min%2s  Max%2s", " ", " ");
        System.out.println();

        for (int row=0;row<mySales.length;row++){
            System.out.printf("Day %2d", row+1);
            for (int col=0;col<mySales[row].length;col++){
                mySales[row][col] = (float)(Math.random()*DIAPASON);
                System.out.printf("%8.2f ", mySales[row][col]);
            }
            System.out.printf("%8.2f", min(mySales[row]));
            System.out.printf("%8.2f", max(mySales[row]));
            System.out.println();

        }
        System.out.printf("\nMin sale is %.2f\n", minTotal(mySales));
        System.out.printf("Max sale is %.2f\n\n", maxTotal(mySales));

    }
}
