package LE_09_04;

public class Main {
    public static void main(String[] args) {
        Artist artist=new Artist(1, "Queen");

        Album album = new Album(
                100, "Greatest Hits", Genre.ROCK, artist
        );

        album.addTrack(new Track(1, "Bohemian Rhapsody", "bohemian_rhapsody.mp3", 354));
        album.addTrack(new Track(2, "Another One Bites The Dust", "another_one.mp3", 215));
        album.addTrack(new Track(3, "We Will Rock You", "we_will_rock_you.mp3", 122));

        album.printDetails();
        System.out.println("\nRemoving track with ID 2...");

        boolean removed = album.removeTrack(2);
        if (removed) {
            System.out.println("Track was removed successfully.");
        } else {
            System.out.println("Track not found.");
        }

        album.printDetails();
    }
}
