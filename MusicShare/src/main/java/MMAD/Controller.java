package MMAD;

public class Controller {
    public static void route(Login login){
        ItemHandler ih = ItemHandler.access();
        PlaylistHandler ph = PlaylistHandler.access();
        Artist artist = new Artist("234234", "BLUR");
        Album album = new Album("123123", "album1", artist);
        Song song = new Song("235", "12", "Song", artist, album);
        ih.addSong(song);
        ih.addSong(song);

        ph.addSong(login, song);
        ph.clearPlaylist(login);
    }

    public static void main(String[] args){
        DBHandler dbh = new DBHandler();
        Login login = new Login("John", "123123");
        User user = new User(login);
        dbh.createUser(user.getLogin().getUsername(), user.getLogin().getPassword());
        route(user.getLogin());
    }
}
