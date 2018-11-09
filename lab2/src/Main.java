import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Catalog theBestCatalogEver = new Catalog();
        theBestCatalogEver.addSong("Mountains", "Radical Face",
                "The Family Tree: The Roots", "4:51", "Rock", 2011);
        theBestCatalogEver.addSong("Ghost Towns", "Radical Face",
                "The Family Tree: The Roots", "3:53", "Rock", 2011);
        theBestCatalogEver.addSong("Rivers in the Dust", "Radical Face",
                "The Family Tree: The Leavers", "5:45", "Alternative Rock", 2016);
        theBestCatalogEver.addSong("Stronger", "The Score",
                "Stronger", "3:10", "Alternative Rock", 2018);
        theBestCatalogEver.addSong("Legend", "The Score",
                "Atlas", "3:09", "Indie", 2017);
        theBestCatalogEver.addSong("Higher", "The Score",
                "Atlas", "3:34", "Alternative Rock", 2017);


        ArrayList<Song> songsByName = theBestCatalogEver.getSongsByName("Mountains");
        ArrayList<Song> songsByArtist = theBestCatalogEver.getSongsByArtist("The Score");
        ArrayList<Song> songsByYear = theBestCatalogEver.getSongsByYear(2017);
        ArrayList<Song> songsByGenre = theBestCatalogEver.getSongsByGenre("Rock");

        for (Song song: songsByName) {
            System.out.println(song.getArtist() + " - " + song.getName());
        }
        System.out.println();
        for (Song song: songsByArtist) {
            System.out.println(song.getArtist() + " - " + song.getName());
        }
        System.out.println();
        for (Song song: songsByYear) {
            System.out.println(song.getArtist() + " - " + song.getName());
        }
        System.out.println();
        for (Song song: songsByGenre) {
            System.out.println(song.getArtist() + " - " + song.getName());
        }
    }
}
