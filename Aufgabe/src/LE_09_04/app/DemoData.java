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
        Artist queen = new Artist(1, "Queen");
        Artist metallica = new Artist(2, "Metallica");
        Artist adele = new Artist(3, "Adele");

        // ===== ALBUM 1 =====
        Album greatestHits = new Album(100, "Greatest Hits", Genre.ROCK, queen);
        greatestHits.addTrack(new Track(1, "Bohemian Rhapsody", "bohemian_rhapsody.mp3", 354));
        greatestHits.addTrack(new Track(2, "Another One Bites The Dust", "another_one.mp3", 215));
        greatestHits.addTrack(new Track(3, "We Will Rock You", "we_will_rock_you.mp3", 122));

        // ===== ALBUM 2 =====
        Album nightAtOpera = new Album(101, "A Night at the Opera", Genre.ROCK, queen);
        nightAtOpera.addTrack(new Track(1, "Death on Two Legs", "death_on_two_legs.mp3", 223));
        nightAtOpera.addTrack(new Track(2, "You're My Best Friend", "youre_my_best_friend.mp3", 170));
        nightAtOpera.addTrack(new Track(3, "Love of My Life", "love_of_my_life.mp3", 217));

        // ===== ALBUM 3 =====
        Album blackAlbum = new Album(200, "Black Album", Genre.ROCK, metallica);
        blackAlbum.addTrack(new Track(1, "Enter Sandman", "enter_sandman.mp3", 331));
        blackAlbum.addTrack(new Track(2, "Sad But True", "sad_but_true.mp3", 324));
        blackAlbum.addTrack(new Track(3, "Nothing Else Matters", "nothing_else_matters.mp3", 388));

        // ===== ALBUM 4 =====
        Album album21 = new Album(300, "21", Genre.POP, adele);
        album21.addTrack(new Track(1, "Rolling in the Deep", "rolling_in_the_deep.mp3", 228));
        album21.addTrack(new Track(2, "Someone Like You", "someone_like_you.mp3", 285));
        album21.addTrack(new Track(3, "Set Fire to the Rain", "set_fire_to_the_rain.mp3", 242));

        // ===== ADD TO MANAGEMENT =====
        management.addAlbum(greatestHits);
        management.addAlbum(nightAtOpera);
        management.addAlbum(blackAlbum);
        management.addAlbum(album21);

        management.addArtist(queen);
        management.addArtist(metallica);
        management.addArtist(adele);

        return management;
    }
}
