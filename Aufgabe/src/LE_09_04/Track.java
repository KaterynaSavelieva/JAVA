package LE_09_04;

public class Track {
    private final int trackId;
    private final String title;
    private final String mp3FileNama;
    private final int lengthSec;

    public Track(int trackId, String title, String mp3FileNama, int lengthSec) {
        if (trackId < 0 ) {
            throw new IllegalArgumentException("Track id cannot be negative");
        }
        if (title == null|| title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if (mp3FileNama == null|| mp3FileNama.isBlank()) {
            throw new IllegalArgumentException("MP3 file name cannot be empty");
        }
        if (lengthSec <= 0) {
            throw new IllegalArgumentException("Length sec cannot be negative");
        }
        this.trackId = trackId;
        this.title = title;
        this.mp3FileNama = mp3FileNama;
        this.lengthSec = lengthSec;

    }

    public int getTrackId() {
        return trackId;
    }
    public String getTitle() {
        return title;
    }
    public String getMP3FileNama() {
        return mp3FileNama;
    }
    public int getLengthSec() {
        return lengthSec;
    }

    @Override
    public String toString() {
        return "Track{id=" + trackId +
                ", title='" + title + '\'' +
                ", mp3='" + mp3FileNama + '\'' +
                ", lengthSec=" + lengthSec +
                '}';
    }

}
