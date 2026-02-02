package _LEK_OOP.app;

public class MenuPrinter {

    public static void printMenu() {
        System.out.println("1) Show all rooms");
        System.out.println("2) Book room");
        System.out.println("3) Checkout room");
        System.out.println("4) Show statistics");
        System.out.println("0) Exit");

    }

    public static final int WIDTH= 40;
    public static void printSeparator() {
        System.out.println("-".repeat(WIDTH));
    }

    public static void printSeparator2() {
        System.out.println("-".repeat(65));
    }

    public static void printTitle(String title) {
        String text = " " + title + " ";
        int sideLeft = (WIDTH - text.length()) / 2;
        int sideRight = WIDTH - sideLeft - text.length();
        System.out.println("\n" + "=".repeat(sideLeft) + text + "=".repeat(sideRight));
    }
}
