package MMAD;

public class ItemHandler {
    private static ItemHandler ih;
    DBHandler dbh = new DBHandler();
    private ItemHandler (){

    }

    public static ItemHandler access(){
        if (ih == null){
            ih = new ItemHandler();
        }
        return ih;
    }
    public void addSong(Song song){
        dbh.addSongToDB(song);
    }

    public void addAlbum(Album album){
        dbh.addAlbumToDB(album);
    }

    public void addArtist(Artist artist){
        dbh.addArtistToDB(artist);
    }

    
}
