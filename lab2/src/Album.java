import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Album implements Iterable<Song> {
    private String name;
    private String artistName;
    private Genre genre;
    private int year;
    private Map<String, Song> songs = new HashMap<>();

    public Album(String name, String artistName, Genre genre, int year) {
        this.name = name;
        this.artistName = artistName;
        this.genre = genre;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artistName;
    }

    public Genre getGenre() {
        return genre;
    }

    public int getYear() {
        return year;
    }

    public void add(Song song) {
        songs.put(song.getName(), song);
    }

    public boolean contains(String songName) {
        return songs.containsKey(songName);
    }

    @Override
    public Iterator iterator() {
        return songs.values().iterator();
    }
}
