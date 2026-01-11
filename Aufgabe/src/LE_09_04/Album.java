package LE_09_04;

import java.util.ArrayList;
import java.util.List;

public class Album {
   private final int albumID;
   private final String title;
   private final Genre genre;
   private final Artist artist;
   private final List<Track> tracks;

   public Album(int albumID, String title, Genre genre, Artist artist) {
       if (albumID <= 0 ) {
           throw new IllegalArgumentException("Album ID must be greater than 0.");
       }
       if (title == null||title.isBlank()) {
           throw new IllegalArgumentException("Title cannot be null or empty.");
       }
       if (genre == null){
           throw new IllegalArgumentException("Genre cannot be null.");
       }
       if (artist == null){
           throw new IllegalArgumentException("Artist cannot be null.");
       }

       this.albumID = albumID;
       this.title = title;
       this.genre = genre;
       this.artist = artist;
       this.tracks = new ArrayList<>();
   }

   public int getAlbumID() {
       return albumID;
   }

   public String getTitle() {
       return title;
   }
   public Genre getGenre() {
       return genre;
   }
   public Artist getArtist() {
       return artist;
   }
   public List<Track> getTracks() {
       return new ArrayList<>(tracks); // encapsulation
   }

    // ===== business methods =====
   public void addTrack(Track track) {
       if (track == null) {
           throw new IllegalArgumentException("Track cannot be null.");
       }
       tracks.add(track);
   }

    public boolean removeTrack(int trackId) {

        for (int i = 0; i < tracks.size(); i++) {

            Track currentTrack = tracks.get(i);

            if (currentTrack.getTrackId() == trackId) {
                tracks.remove(i);
                return true;   // track found and removed
            }
        }

        return false; // no track with this ID found
    }

    public int getTotalDurationSec() {
        int total = 0;
        for (Track track : tracks) {
            total += track.getLengthSec();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Album{id=" + albumID +
                ", title='" + title + '\'' +
                ", artist=" + artist.getName() +
                ", genre=" + genre +
                ", tracks=" + tracks.size() +
                ", totalDurationSec=" + getTotalDurationSec() +
                '}';
    }


    public void printDetails() {

        System.out.println("\n------------------ALBUM DETAILS------------------");

        System.out.printf("Album:  %s\n", title);
        System.out.printf("Artist: %s\n", artist.getName());
        System.out.printf("Genre:  %s\n", genre);

        printSeparator();

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
                        t.getMP3FileNama());
            }
        }

        printSeparator();
        printData("Number of tracks:", tracks.size(), "");
        printData("Total duration:", getTotalDurationSec(), "sec");
        printSeparator();
    }

    private void printData(String message, int value, String unit) {
        System.out.printf("| %-29s | %7d %3s|\n", message, value, unit);
    }

    private void printSeparator() {
        System.out.println("-".repeat(55));
    }

}
