package LE_11_01.app;

public class MenuPrinter {

    public static void printHeader() {
        System.out.println("\n========== CLUB DB MENU ==========");
    }

    public static void printMenu() {
        System.out.println("1) Create member");
        System.out.println("2) Show all members");
        System.out.println("3) Create coach");
        System.out.println("4) Show all coaches");
        System.out.println("5) Create team");
        System.out.println("6) Print all teams");
        System.out.println("7) Add member to team");
        System.out.println("8) Show team details");
        System.out.println("9) Remove member from team");
        System.out.println("0) Exit");
        System.out.println("-------------------------------------");
    }


    public static final int WIDTH= 100;
    public static void printSeparator() {
        System.out.println("-".repeat(WIDTH));
    }

    public static void printTitle(String title) {
        String text = " " + title + " ";
        int sideLeft = (WIDTH - text.length()) / 2;
        int sideRight = WIDTH - sideLeft - text.length();
        System.out.println("\n" + "=".repeat(sideLeft) + text + "=".repeat(sideRight));
    }
}
