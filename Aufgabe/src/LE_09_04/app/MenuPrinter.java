package LE_09_04.app;


import LE_09_04.model.Track;
import LE_09_04.model.Album;

import java.util.List;


public class MenuPrinter {
    private MenuPrinter() {};

    public static void printMenu() {
        System.out.println("""        
                ========== MUSIC MANAGEMENT ==========
                1) Show all albums (short)
                2) Show album details
                3) Add new album
                4) Delete album (deletes all tracks)
                5) Add track to album
                6) Delete track from album (only this track)
                7) Show all artists (short)
                0) Exit
                -------------------------------------
                """);
    }

    public static final int WIDTH = 95;

    public static void printSeparator (){
        System.out.println("-".repeat(WIDTH));
    }

    public static void printTitle(String title){
        int sideLeft = (WIDTH - title.length()) / 2;
        int sideRight = WIDTH - sideLeft-title.length();
        System.out.println("\n"+"-".repeat(sideLeft)+title+"-".repeat(sideRight));
    }


    public static void  printDetails(Album album) {

        System.out.println("\n------------------ALBUM DETAILS------------------");

        System.out.printf("Album:  %s\n", album.getTitle());
        System.out.printf("Artist: %s\n", album.getArtist().getName());
        System.out.printf("Genre:  %s\n", album.getGenre());

        printSeparator();

        List<Track> tracks = album.getTracks();

        if (tracks.isEmpty()) {
            System.out.println("Tracks: (no tracks in this album)");
        } else {
            System.out.println("Tracks:");
            for (int i = 0; i < tracks.size(); i++) {
                Track t = tracks.get(i);
                // similar to your style: numbered list + aligned columns
                System.out.printf(" %2d) %-25s | %4d sec | %-20s\n",
                        (i + 1),
                        t.getTitle(),
                        t.getLengthSec(),
                        t.getMP3FileName());
            }
        }

        printSeparator2();
        printData("Number of tracks:", tracks.size(), "");
        printData("Total duration:", album.getTotalDurationSec(), "sec");
        printSeparator2();
    }

    private static void printData(String message, int value, String unit) {
        System.out.printf("| %-29s | %7d %3s|\n", message, value, unit);
    }

    private static void printSeparator2() {
        System.out.println("-".repeat(55));
    }
}
