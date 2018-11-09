public class Song {
    private String name;
    private String artistName;
    private String albumName;
    private int length;

    public Song(String name, String artistName, String albumName, int length) {
        this.name = name;
        this.artistName = artistName;
        this.albumName = albumName;
        this.length = length;
    }

    public String getArtist() {
        return artistName;
    }

    public String getAlbum() {
        return albumName;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }
}


