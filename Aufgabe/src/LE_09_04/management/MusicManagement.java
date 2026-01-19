package LE_09_04.management;

import LE_09_04.app.MenuPrinter;
import LE_09_04.model.Album;
import LE_09_04.model.Artist;
import LE_09_04.model.Track;

import java.util.ArrayList;
import java.util.List;

public class MusicManagement {

    private final List<Album> albums;
    private final List<Artist> artists;

    private int nextAlbumId = 1;
    private int nextArtistId = 1;
    private int nextTrackId = 1;

    public MusicManagement() {
        this.albums = new ArrayList<>();
        this.artists = new ArrayList<>();
    }


    public int generateAlbumId() { return nextAlbumId++; }
    public int generateArtistId() { return nextArtistId++; }
    public int generateTrackId() { return nextTrackId++;}


    public Album findAlbumById(int albumId) {
        for (Album album : albums) {
            if (album.getAlbumID() == albumId) {
                return album;
            }
        }
        return null;
    }

    public Artist findArtistById(int artistId) {
        for (Artist artist : artists) {
            if (artist.getArtistID() == artistId) {
                return artist;
            }
        }
        return null;
    }


    public void addAlbum(Album album) {
        if (album == null) {
            throw new IllegalArgumentException("Album cannot be null");
        }
        if (findAlbumById(album.getAlbumID()) != null) {
            throw new IllegalArgumentException("Album with ID " + album.getAlbumID() + " already exists");
        }
        albums.add(album);
    }

    public void addArtist(Artist artist) {
        if (artist == null) {
            throw new IllegalArgumentException("Artist cannot be null");
        }
        if (findArtistById(artist.getArtistID()) != null) {
            throw new IllegalArgumentException("Artist with ID " + artist.getArtistID() + " already exists");
        }
        artists.add(artist);
    }

    public boolean removeAlbum(int albumID) {
        Album album = findAlbumById(albumID);
        if (album == null) {
            return false;
        }
        // clearTracks() is not necessary here (no global track storage), removing album is enough
        return albums.remove(album);
    }

    public boolean addTrackToAlbum(int albumID, Track track) {
        Album album = findAlbumById(albumID);
        if (album == null) {
            return false;
        }
        album.addTrack(track);
        return true;
    }

    public boolean removeTrackFromAlbum(int albumID, int trackId) {
        Album album = findAlbumById(albumID);
        if (album == null) {
            return false;
        }
        return album.removeTrack(trackId);
    }

    public boolean hasArtists() {
        return !artists.isEmpty();
    }


    private void printAlbumTableHeader() {
        System.out.printf("%-5s | %-30s | %-20s | %-6s | %-7s | %-10s%n",
                "ID", "Title", "Artist", "Genre", "Tracks", "Total (sec)");
        MenuPrinter.printSeparator();
    }

    public void printAllAlbums() {
        if (albums.isEmpty()) {
            System.out.println("No albums available");
            return;
        }

        MenuPrinter.printTitle("ALBUM LIST");
        printAlbumTableHeader();

        for (Album album : albums) {
            System.out.printf("%-5d | %-30s | %-20s | %-6s | %-7d | %-10d%n",
                    album.getAlbumID(),
                    album.getTitle(),
                    album.getArtist().getName(),
                    album.getGenre(),
                    album.getTracks().size(),
                    album.getTotalDurationSec());
        }
        MenuPrinter.printSeparator();
    }

    public void printAlbumDetails(int albumID) {
        Album album = findAlbumById(albumID);
        if (album == null) {
            System.out.println("Album with ID " + albumID + " does not exist");
            return;
        }

        MenuPrinter.printTitle("ALBUM DETAILS");
        printAlbumTableHeader();

        List<Track> tracks = album.getTracks(); // one copy only
        System.out.printf("%-5d | %-30s | %-20s | %-6s | %-7d | %-10d%n",
                album.getAlbumID(),
                album.getTitle(),
                album.getArtist().getName(),
                album.getGenre(),
                tracks.size(),
                album.getTotalDurationSec());

        MenuPrinter.printSeparator();


        MenuPrinter.printTitle("TRACK LIST");
        if (tracks.isEmpty()) {
            System.out.println("No tracks in this album.");
            MenuPrinter.printSeparator();
            return;
        }

        System.out.printf("%-5s | %-30s | %-10s | %-30s%n",
                "ID", "Title", "Length", "MP3 File");
        MenuPrinter.printSeparator();

        for (Track track : tracks) {
            System.out.printf("%-5d | %-30s | %-10d | %-30s%n",
                    track.getTrackId(),
                    track.getTitle(),
                    track.getLengthSec(),
                    track.getMP3FileName());
        }
        MenuPrinter.printSeparator();
    }

    public void printAllArtistsShort() {
        if (artists.isEmpty()) {
            System.out.println("No artists available");
            return;
        }

        MenuPrinter.printTitle("ARTIST LIST");
        System.out.printf("%-5s | %-25s%n", "ID", "Artist");
        MenuPrinter.printSeparator();

        for (Artist artist : artists) {
            System.out.printf("%-5d | %-25s%n",
                    artist.getArtistID(),
                    artist.getName());
        }

        MenuPrinter.printSeparator();
        System.out.println();
    }
}