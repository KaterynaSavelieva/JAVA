package LE_09_04;

public class Artist {
    private final int artistID;
    private final String name; //The Artist class is immutable.
    // Its identity fields are final to prevent accidental modification after creation.
    // значення присвоюється ОДИН раз (у конструкторі), після створення об’єкта не може змінюватися

    public Artist(int artistID, String name) {
        if (artistID < 0) {
            throw new IllegalArgumentException("Artist ID must be a positive integer");
        }
        if (name == null|| name.isBlank()) {
            throw new IllegalArgumentException("Artist name must not be null or blank");
        }
        this.artistID = artistID;
        this.name = name;
    }

    public int getArtistID() {
        return artistID;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Artist{" + "artistID=" + artistID + ", name=" + name + '}';
    }
}
