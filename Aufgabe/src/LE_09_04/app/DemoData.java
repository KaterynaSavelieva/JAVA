package LE_09_04.app;

import LE_09_04.management.MusicManagement;
import LE_09_04.model.Album;
import LE_09_04.model.Artist;
import LE_09_04.model.Genre;
import LE_09_04.model.Track;

public class DemoData {

    private DemoData() {}

    public static MusicManagement create() {

        MusicManagement management = new MusicManagement();

        // ===== ARTISTS =====
        Artist queen = new Artist(management.generateArtistId(), "Queen");
        Artist metallica = new Artist(management.generateArtistId(), "Metallica");
        Artist adele = new Artist(management.generateArtistId(), "Adele");

        management.addArtist(queen);
        management.addArtist(metallica);
        management.addArtist(adele);

        // ===== ALBUM 1 =====
        Album greatestHits = new Album(management.generateAlbumId(), "Greatest Hits", Genre.ROCK, queen);
        greatestHits.addTrack(new Track(management.generateTrackId(), "Bohemian Rhapsody", 354));
        greatestHits.addTrack(new Track(management.generateTrackId(), "Another One Bites The Dust",  215));
        greatestHits.addTrack(new Track(management.generateTrackId(), "We Will Rock You",  122));
        management.addAlbum(greatestHits);

        // ===== ALBUM 2 =====
        Album nightAtOpera = new Album(management.generateAlbumId(), "A Night at the Opera", Genre.ROCK, queen);
        nightAtOpera.addTrack(new Track(management.generateTrackId(), "Death on Two Legs", 223));
        nightAtOpera.addTrack(new Track(management.generateTrackId(), "You're My Best Friend",  170));
        nightAtOpera.addTrack(new Track(management.generateTrackId(), "Love of My Life",  217));
        management.addAlbum(nightAtOpera);

        // ===== ALBUM 3 =====
        Album blackAlbum = new Album(management.generateAlbumId(), "Black Album", Genre.ROCK, metallica);
        blackAlbum.addTrack(new Track(management.generateTrackId(), "Enter Sandman", 331));
        blackAlbum.addTrack(new Track(management.generateTrackId(), "Sad But True", 324));
        blackAlbum.addTrack(new Track(management.generateTrackId(), "Nothing Else Matters", 388));
        management.addAlbum(blackAlbum);

        // ===== ALBUM 4 =====
        Album album21 = new Album(management.generateAlbumId(), "21", Genre.POP, adele);
        album21.addTrack(new Track(management.generateTrackId(), "Rolling in the Deep", 228));
        album21.addTrack(new Track(management.generateTrackId(), "Someone Like You",  285));
        album21.addTrack(new Track(management.generateTrackId(), "Set Fire to the Rain",  242));
        management.addAlbum(album21);

        return management;
    }
}
