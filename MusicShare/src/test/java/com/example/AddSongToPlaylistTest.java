package com.example;
import MMAD.Artist;
import MMAD.PlaylistHandler;
import MMAD.AccountHandler;
import MMAD.ItemHandler;
import MMAD.Login;
import MMAD.Song;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AddSongToPlaylistTest {
    PlaylistHandler ph;
    AccountHandler ah;
    ItemHandler ih;
    Login invalidUser;
    Login validUser;
    Song invalidSong;
    Song validSong;

    @Before
    public void createSongAndUserObjects(){
        ph = PlaylistHandler.access();
        ah = AccountHandler.access();
        ih = ItemHandler.access();

        invalidUser = new Login("Not a user", "123");

        validUser = new Login("TestUser123", "TestUser123");

        ah.createAccount("TestUser123", "TestUser123");

        invalidSong = new Song(null, null, null, null);

        validSong = new Song (99999, "testSongID", "testSongName",
        new Artist(99999,"testArtistID", "testArtistName"),
        new MMAD.Album(99999,"testAlbumID" , "testAlbumName",
        new Artist(99999,"testArtistID", "testArtistName")));
        ih.addSongToDB(validSong);

    }

    @After
    public void resetSongAndUserInDB(){
        ph.removeSongFromPlaylist(validUser, validSong);
    }

    /**
     * testing if a valid user tries to add a valid song
     * should result in true
     */
    @Test
    public void testValidUserAddsValidSongToPlaylist(){
        boolean result = ph.addSongToPlaylist(validUser, validSong);
        assertTrue(result);
    }

    /**
     * testing if a valid user tries to add an invalid song
     * should result in a null Pointer exception
     */
    @Test (expected = NullPointerException.class)
    public void testValidUserAddInvalidSongToPlaylist(){
        ph.addSongToPlaylist(validUser, invalidSong);
    }


    /**
     * testing if an invalid user tries to add a valid song to playlist
     * should result in false
     */
    @Test
    public void testInvalidUserAddValidSongToPlaylist(){
        boolean actual = ph.addSongToPlaylist(invalidUser, validSong);
        assertFalse(actual);// Expect the result to be false.
    }

    /**
     * testing if an invalid user tries to add an invalid song to playlist
     * should result in a null pointer exception
     */
    @Test (expected = NullPointerException.class)
    public void testInvalidUserAddInvalidSongToPlaylist(){
        ph.addSongToPlaylist(invalidUser, invalidSong);
    }
    

}
