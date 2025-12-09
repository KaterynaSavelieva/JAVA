package LE_06_02_varargs;

public class Main {
    static void total (String report, float... sale){
        System.out.printf("\nPeriod: %s\n", report);
        System.out.println("Number of sales: "+sale.length);
        System.out.print("Sales for this periods: ");
        float totalSale=0;
            for (float i : sale) {
                totalSale =totalSale+i;

                System.out.print(i+ ", ");

            }
            System.out.printf("\nTotal sales: %.2f", totalSale);
            System.out.println();
        }

    public static void main(String[] args) {
        total("3 months", 111,200,500);
        total("7 months", 12,19,500,800,4,58,1);
    }
}
