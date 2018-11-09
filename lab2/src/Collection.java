import java.util.HashSet;
import java.util.Iterator;

public class Collection implements Iterable<Song> {
    private HashSet<Song> songs = new HashSet<>();

    public void add(Album album) {
        for (Song song: album) {
            add(song);
        }
    }

    public void add(Song song) {
        songs.add(song);
    }

    @Override
    public Iterator<Song> iterator() {
        return songs.iterator();
    }
}
