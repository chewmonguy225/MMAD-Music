package Mickael;

//DELETE THESE
import se.michaelthelin.spotify.model_objects.specification.AlbumSimplified;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.model_objects.specification.Track;

//TRACK TO SONG REPLACE
public abstract class AbstractAPIQueryBuilder {
    public abstract AlbumSimplified[] searchAlbum(String albumTitle);
    public abstract Track[] searchSong(String songTitle);
    public abstract Artist[] searchArtists(String artistName);
}
