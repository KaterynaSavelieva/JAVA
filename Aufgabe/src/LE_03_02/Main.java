package LE_03_02;
import java.util.Scanner;

public class Main {

    static int checkInt(Scanner scanner, String message) {
        int value;
        while (true) {
            System.out.print(message);

            if (scanner.hasNextInt()){
                value = scanner.nextInt();
                if (value >0) {
                    return value;
                } else {
                    System.out.println("Error: value should be >0");
                }
            }else  {
                System.out.println("Error: Please enter a positive integer!");
                scanner.next();
            }
        }
    }

    static boolean isLeapYear(int year) {
        return (year%400==0 || (year%4==0 && year%100!=0));
    }

    static void printLeapYear(int year) {
        if(isLeapYear(year)) {
            System.out.printf("Year %s is a leap year!\n", year);
        } else {
            System.out.printf("Year %s is not a leap year!\n", year);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int year = checkInt(scanner, "Please enter the year: ");
        printLeapYear(year);

        System.out.println("===== Leap years in this period: =====\n");

        int startYear = checkInt(scanner, "Please enter the start year: ");
        int endYear = checkInt(scanner, "Please enter the end year: ");
        if (startYear > endYear) {
            int change =endYear;
            endYear=startYear;
            startYear=change;}

        System.out.printf("Start year is %d, end year is %d.\n", startYear, endYear);

        for (int i=startYear;i<=endYear;i++) {
            if(isLeapYear(i)) {
                printLeapYear(i);
            }else {
                continue;
            }
        }


    }
}
