import java.util.*;

public class Catalog {
    Map<String, Album> albums = new HashMap<>();
    Map<String, Genre> genres = new HashMap<>();
    Map<String, Collection> collections = new HashMap<>();

    private int parseLength(String lengthString) {  // Turn "xx:yy" to integer length in seconds
        String[] parts = lengthString.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }

    public void addSong(String songName, String artistName, String albumName, String length, String genre, int year) {
        if (albums.containsKey(albumName)) {    // There is an album with name @albumName
            Album album = albums.get(albumName);
            if (!album.contains(songName)) {    // If it's new song add it to album, otherwise do nothing
                album.add(new Song(songName, artistName, albumName, parseLength(length)));
            }
        } else {
            if (!genres.containsKey(genre)) {   // Create new genre
                Genre newGenre = new Genre(genre);
                for (Genre parentGenre: genres.values()) {
                    if (newGenre.getName().contains(parentGenre.getName())) {
                        parentGenre.addSubgenre(newGenre);
                        newGenre.addParent(parentGenre);
                    }
                }
                genres.put(genre, newGenre);
            }

            Album newAlbum = new Album(albumName, artistName, genres.get(genre), year);     // create new album
            newAlbum.add(new Song(songName, artistName, albumName, parseLength(length)));
            albums.put(albumName, newAlbum);
        }
    }

    public ArrayList<Song> getSongsByName(String songName) {
        ArrayList<Song> list = new ArrayList<>();
        for (Album album: albums.values()) {
            for (Song song: album) {
                if (song.getName().equals(songName)) {
                    list.add(song);
                }
            }
        }
        return list;
    }

    public ArrayList<Song> getSongsByArtist(String artistName) {
        ArrayList<Song> list = new ArrayList<>();
        for (Album album: albums.values()) {
            for (Song song: album) {
                if (song.getArtist().equals(artistName)) {
                    list.add(song);
                }
            }
        }
        return list;
    }

    public ArrayList<Song> getSongsByYear(int year) {
        ArrayList<Song> list = new ArrayList<>();
        for (Album album: albums.values()) {
            if (album.getYear() == year) {
                for (Song song: album) {
                    list.add(song);
                }
            }
        }
        return list;
    }

    public ArrayList<Song> getSongs(String artistName, int year) {
        ArrayList<Song> list = new ArrayList<>();
        for (Album album: albums.values()) {
            if (album.getArtist().equals(artistName) && album.getYear() == year) {
                for (Song song: album) {
                    list.add(song);
                }
            }
        }
        return list;
    }

    public ArrayList<Song> getSongsByLength(int minLength, int maxLength) {
        ArrayList<Song> list = new ArrayList<>();
        for (Album album: albums.values()) {
            for (Song song: album) {
                if (minLength <= song.getLength() && song.getLength() <= maxLength) {
                    list.add(song);
                }
            }
        }
        return list;
    }

    public ArrayList<Song> getSongsByGenre(String genre) {
        ArrayList<Song> list = new ArrayList<>();

        if (genres.containsKey(genre)) {
            Set<Genre> genresNeed = new HashSet<>();
            Queue<Genre> bfsQueue = new LinkedList<>();
            Genre queryGenre = genres.get(genre);
            genresNeed.add(queryGenre);

            bfsQueue.add(queryGenre);
            while (!bfsQueue.isEmpty()) {
                Genre currGenre = bfsQueue.remove();
                for (Genre subgenre: currGenre.subgenres().values()) {
                    genresNeed.add(subgenre);
                    bfsQueue.add(subgenre);
                }
            }

            for (Album album: albums.values()) {
                for (Genre subgenre: genresNeed) {
                    if (album.getGenre().getName().equals(subgenre.getName())) {
                        for (Song song: album) {
                            list.add(song);
                        }
                        break;
                    }
                }

            }
        }

        return list;
    }
}

