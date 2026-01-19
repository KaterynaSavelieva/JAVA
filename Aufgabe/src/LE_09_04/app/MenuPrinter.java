// ===== FILE: MenuPrinter.java =====
package LE_09_04.app;

public class MenuPrinter {

    private MenuPrinter() {}

    public static final int WIDTH = 95;

    public static void printMenu() {
        System.out.println("""
                ========== MUSIC MANAGEMENT ==========
                1) Show all albums
                2) Show album details
                3) Add new album
                4) Delete album (deletes all tracks)
                5) Add track to album
                6) Delete track from album
                7) Show all artists
                8) Add artist
                0) Exit
                -------------------------------------
                """);
    }

    public static void printSeparator() {
        System.out.println("-".repeat(WIDTH));
    }

    public static void printTitle(String title) {
        String text = " " + title + " ";
        int sideLeft = (WIDTH - text.length()) / 2;
        int sideRight = WIDTH - sideLeft - text.length();
        System.out.println("\n" + "-".repeat(sideLeft) + text + "-".repeat(sideRight));
    }
}
