package MMAD;

public class Controller {
    private static Controller c;
    private static ItemHandler ih = ItemHandler.access();
    private static PlaylistHandler ph = PlaylistHandler.access();
    //private static UI ui = UI.access();
    int ui;

    private Controller(){
    }

    public Controller access(){
        if(c == null){
            c = new Controller();
        } 
        return c;
    }

    public void routeMainMenu(){//maybe pass UI and display objects to get input
        switch(ui){//ui.getInt() most likely
            case 1:
                break;
            case 2:
                break;
        }
    }

    public void routeSongMenu(){
        switch(ui){
            case 0://previous page, if no previous page then page 1
                break;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                //create Song object "song" from option 1-5 and call songOptionMenu(song)
                break;
            case 6://next page, if no next page then page 1
                break;
            default://exit to main menu
        }  
    }

    public void songOptionMenu(Song song){

        switch (ui){
            case 1://add song to playlist
                break;
            case 2://remove song from playlist
                break;
            case 3://write review
                break;
            case 4://delete review
                break;
            case 5://previous page (routeSongMenu)
                break;
            default://exit to main menu
        }
    }


    public static void route1(Login login){
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
        route1(user.getLogin());
    }
}
