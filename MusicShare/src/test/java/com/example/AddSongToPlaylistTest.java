package com.example;
import MMAD.Artist;
import MMAD.PlaylistHandler;
import MMAD.Login;
import MMAD.Song;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AddSongToPlaylistTest {
    PlaylistHandler ph;
    Login invalidUser;
    Song invalidSong;
    Song validSong;

    @Before
    public void createSongAndUserObjects(){
        ph = PlaylistHandler.access();

        invalidUser = new Login("Not a user", "123");

        

        invalidSong = new Song(null, null, null, null);

        validSong = new Song (99999, "testSongID", "testSongName",
        new Artist(99999,"testArtistID", "testArtistName"),
        new MMAD.Album(99999,"testAlbumID" , "testAlbumName",
        new Artist(99999,"testArtistID", "testArtistName")));

    }

    @After
    public void resetSongAndUserInDB(){

    }

    @Test
    public void testValidUserAddInvalidSongToPlaylist(){

    }


    /**
     * testing if an invalid user tries to add a valid song to playlist
     */
    @Test
    public void testInvalidUserAddValidSongToPlaylist(){
        boolean actual = ph.addSongToPlaylist(invalidUser, validSong);
        assertFalse(actual);// Expect the result to be false.
    }

    /**
     * testing if an invalid user tries to add an invalid song to playlist
     */
    @Test (expected = NullPointerException.class)
    public void testInvalidUserAddInvalidSongToPlaylist(){
        ph.addSongToPlaylist(invalidUser, invalidSong);
    }
    

}
