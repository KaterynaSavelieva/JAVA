package LE_09_04.model;

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

   public int getAlbumID() {return albumID;}
   public String getTitle() {return title;}
   public Genre getGenre() {return genre;}
   public Artist getArtist() {return artist;}
   public List<Track> getTracks() {return new ArrayList<>(tracks); }// encapsulation

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

    public void clearTracks() {tracks.clear();} // drop all tracks in album

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

}