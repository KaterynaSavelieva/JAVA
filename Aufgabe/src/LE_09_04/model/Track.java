package LE_09_04.model;

public class Track {

    private final int trackId;
    private final String title;
    private final String mp3FileName;
    private final int lengthSec;

    public Track(int trackId, String title, int lengthSec) {
        if (trackId <= 0) {
            throw new IllegalArgumentException("Track id must be positive");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if (lengthSec <= 0) {
            throw new IllegalArgumentException("Length sec must be positive");
        }

        this.trackId = trackId;
        this.title = title;
        this.mp3FileName = generateMp3FileName(title);
        this.lengthSec = lengthSec;
    }

    private static String generateMp3FileName(String title) {
        return title
                .toLowerCase()
                .trim()
                .replaceAll("\\s+", "_")
                + ".mp3";
    }

    public int getTrackId() {
        return trackId;
    }

    public String getTitle() {
        return title;
    }

    public String getMP3FileName() {
        return mp3FileName;
    }

    public int getLengthSec() {
        return lengthSec;
    }

    @Override
    public String toString() {
        return "Track{id=" + trackId +
                ", title='" + title + '\'' +
                ", mp3='" + mp3FileName + '\'' +
                ", lengthSec=" + lengthSec +
                '}';
    }
}
