package LE_09_04.app;

import java.util.Scanner;

import LE_09_04.management.MusicManagement;
import LE_09_04.model.Album;
import LE_09_04.model.Artist;
import LE_09_04.model.Genre;
import LE_09_04.model.Track;

public class ConsoleUi {

    private final Scanner scanner;
    private final MusicManagement manager;

    public ConsoleUi(Scanner scanner, MusicManagement manager) {
        this.scanner = scanner;
        this.manager = manager;
    }

    public void start() {
        while (true) {
            MenuPrinter.printMenu();
            int choice = InputHelper.readInt(scanner, "Choose an option: ");

            switch (choice) {
                case 1: manager.printAllAlbums(); break;
                case 2: showAlbumDetails(); break;
                case 3: addAlbum(); break;
                case 4: deleteAlbum(); break;
                case 5: addTrack(); break;
                case 6: deleteTrack(); break;
                case 7: manager.printAllArtistsShort(); break;
                case 8: addNewArtist(); break;
                case 0: System.out.println("Bye!"); return;
                default: System.out.println("Invalid option.");
            }
        }
    }

    private void showAlbumDetails() {
        int albumId = InputHelper.readInt(scanner, "Album ID: ");
        manager.printAlbumDetails(albumId);
    }

    private void addAlbum() {
        int albumId = manager.generateAlbumId();
        System.out.println("New Album ID: " + albumId);

        String title = InputHelper.readString(scanner, "Title: ");
        Genre genre = InputHelper.readGenre(scanner);

        Artist artist = chooseArtist();

        Album album = new Album(albumId, title, genre, artist);
        manager.addAlbum(album);

        System.out.println("Album added.");
    }


    private Artist chooseArtist() {

        if (!manager.hasArtists()) {
            System.out.println("No artists available. Let's create a new artist.");
            return createNewArtist();
        }

        System.out.println("Artists:");
        manager.printAllArtistsShort();

        int choice = InputHelper.readInt(scanner, "Enter Artist ID or 0 to create new: ");

        if (choice == 0) {
            return createNewArtist();
        }

        Artist artist = manager.findArtistById(choice);
        if (artist == null) {
            System.out.println("Artist not found. Creating a new artist.");
            return createNewArtist();
        }

        System.out.println("Selected: " + artist.getName());
        return artist;
    }

    private Artist createNewArtist() {
        int artistId = manager.generateArtistId();
        System.out.println("New Artist ID: " + artistId);

        String name = InputHelper.readString(scanner, "Artist name: ");
        Artist artist = new Artist(artistId, name);
        manager.addArtist(artist);

        System.out.println("Artist added: " + artist.getName());
        return artist;
    }

    private void addNewArtist() {
        createNewArtist();
    }

    private void deleteAlbum() {
        int albumId = InputHelper.readInt(scanner, "Album ID to delete: ");
        boolean removed = manager.removeAlbum(albumId);
        System.out.println(removed ? "Album removed (tracks deleted too)." : "Album not found.");
    }

    private void addTrack() {
        int albumId = InputHelper.readInt(scanner, "Album ID: ");

        Album album = manager.findAlbumById(albumId);
        if (album == null) {
            System.out.println("Album not found");
            return;
        }

        int trackId = manager.generateTrackId();
        System.out.println("New Track ID: " + trackId);

        String trackTitle = InputHelper.readString(scanner, "Track title: ");
        int length = InputHelper.readInt(scanner, "Length (sec): ");

        Track track = new Track(trackId, trackTitle, length);

        album.addTrack(track);
        System.out.println("Track added: " +track.getTitle() +" ["+ track.getMP3FileName()+"]");
    }

    private void deleteTrack() {
        int albumId = InputHelper.readInt(scanner, "Album ID: ");

        Album album = manager.findAlbumById(albumId);
        if (album == null) {
            System.out.println("Album not found");
            return;
        }

        int trackId = InputHelper.readInt(scanner, "Track ID to remove: ");

        boolean ok = manager.removeTrackFromAlbum(albumId, trackId);
        System.out.println(ok ? "Track removed." : "Track not found.");
    }
}
