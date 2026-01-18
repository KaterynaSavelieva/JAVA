package LE_09_04.app;

import java.util.Scanner;

import LE_09_04.management.MusicManagement;
import LE_09_04.model.Track;
import LE_09_04.model.Artist;
import LE_09_04.model.Album;
import LE_09_04.model.Genre;

public class ConsoleUi {

    private final Scanner scanner;
    private final MusicManagement manager;

    public ConsoleUi(Scanner scanner, MusicManagement manager) {
        this.scanner = scanner;
        this.manager = manager;
    }

    public void start(){
        while (true) {
            MenuPrinter.printMenu();
            int choice = InputHelper.readInt (scanner, "Choose an option: ");

            switch (choice) {
                case 1: showAllAlbums(); break;
                case 2: showAlbumDetails(); break;
                case 3: addAlbum(); break;
                case 4: deleteAlbum(); break;
                case 5: addTrack(); break;
                case 6: deleteTrack(); break;
                case 7: showAllArtists(); break;
                case 0: System.out.println("Bye!"); return;
                default: System.out.println("Invalid option.");

            }
        }
    }

    private void showAllAlbums () {
        manager.printAllAlbumsShort();
    }

    private void showAllArtists () {
        manager.printAllArtistsShort();
    }

    private void showAlbumDetails () {
        int albumId = InputHelper.readInt (scanner, "Choose an album ID: ");
        manager.printAlbumDetails(albumId);
    }

    private void addAlbum () {
        int albumId = InputHelper.readInt(scanner, "New Album ID: ");
        String title = InputHelper.readString(scanner, "Title: ");
        Genre genre = InputHelper.readGenre(scanner);

        System.out.println("Choose an artist ID from the list: ");
        manager.printAllArtistsShort();
        int artistId = InputHelper.readInt(scanner, "Artist ID: ");

        Artist artist = manager.findArtistById(artistId);

        if (artist == null) {
            System.out.println("Artist with ID: " + artistId + " not found.");
            String artistName = InputHelper.readString(scanner, "Artist name: ");
            artist = new Artist(artistId, artistName);
            manager.addArtist(artist);
        }



        Album newAlbum = new Album(albumId, title, genre, artist);
        manager.addAlbum(newAlbum);

        System.out.println("Album added.");
    }

    private void deleteAlbum () {
        int albumId = InputHelper.readInt(scanner, "Album ID to delete: ");
        boolean removed = manager.removeAlbum(albumId);
        System.out.println(removed ? "Album removed (tracks deleted too)." : "Album not found.");
    }

    private void addTrack () {
        int albumId = InputHelper.readInt(scanner, "Album ID: ");
        int trackId = InputHelper.readInt(scanner, "Track ID: ");
        String trackTitle = InputHelper.readString(scanner, "Track title: ");
        String mp3 = InputHelper.readString(scanner, "MP3 file name: ");
        int length = InputHelper.readInt(scanner, "Length (sec): ");

        boolean ok = manager.addTrackToAlbum(albumId, new Track(trackId, trackTitle, mp3, length));
        System.out.println(ok ? "Track added." : "Album not found.");
    }

    private void deleteTrack () {
        int albumId = InputHelper.readInt(scanner, "Album ID: ");
        int trackId = InputHelper.readInt(scanner, "Track ID to remove: ");

        boolean ok = manager.removeTrackFromAlbum(albumId, trackId);
        System.out.println(ok ? "Track removed." : "Album or Track not found.");
    }



}
