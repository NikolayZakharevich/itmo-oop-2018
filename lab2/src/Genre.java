import java.util.HashMap;
import java.util.Map;

public class Genre {
    private String name;
    private Map<String, Genre> subgenres = new HashMap<>();
    private Map<String, Genre> parentGenres = new HashMap<>();

    public Genre(String name) {
        this.name = name;
    }

    public void addParent(Genre parent) {
        parentGenres.put(parent.name, parent);
    }

    public void addSubgenre(Genre child) {
        subgenres.put(child.name, child);
    }

    public String getName() {
        return name;
    }

    public Map<String, Genre> subgenres() {
        return subgenres;
    }

    public Map<String, Genre> parentGenres() {
        return parentGenres;
    }
}
