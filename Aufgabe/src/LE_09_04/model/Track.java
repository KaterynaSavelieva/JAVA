package LE_09_04.model;

public class Track {
    private final int trackId;
    private final String title;
    private final String mp3FileName;
    private final int lengthSec;

    public Track(int trackId, String title, String mp3FileName, int lengthSec) {
        if (trackId <= 0 ) {
            throw new IllegalArgumentException("Track id cannot be negative");
        }
        if (title == null|| title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if (mp3FileName == null|| mp3FileName.isBlank()) {
            throw new IllegalArgumentException("MP3 file name cannot be empty");
        }
        if (lengthSec <= 0) {
            throw new IllegalArgumentException("Length sec cannot be negative");
        }
        this.trackId = trackId;
        this.title = title;
        this.mp3FileName = mp3FileName;
        this.lengthSec = lengthSec;

    }

    public int getTrackId() {return trackId;}
    public String getTitle() {return title;}
    public String getMP3FileName() {return mp3FileName;}
    public int getLengthSec() {return lengthSec;}

    @Override
    public String toString() {
        return "Track{id=" + trackId +
                ", title='" + title + '\'' +
                ", mp3='" + mp3FileName + '\'' +
                ", lengthSec=" + lengthSec +
                '}';
    }

}
