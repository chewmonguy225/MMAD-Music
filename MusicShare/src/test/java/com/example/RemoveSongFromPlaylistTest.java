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

public class RemoveSongFromPlaylistTest {
    PlaylistHandler ph;
    AccountHandler ah;
    ItemHandler ih;
    Login invalidUser;
    Login validUser;
    Song songInPlaylist;
    Song songNotInPlaylist;

    @Before
    public void createSongAndUserObjects(){
        ph = PlaylistHandler.access();
        ah = AccountHandler.access();
        ih = ItemHandler.access();

        invalidUser = new Login("Not a user", "123");

        validUser = new Login("TestUser123", "TestUser123");

        ah.createAccount("TestUser123", "TestUser123");

        songInPlaylist = new Song (999992, "testSongID2", "testSongName2",
        new Artist(999992,"testArtistID2", "testArtistName2"),
        new MMAD.Album(999992,"testAlbumID2" , "testAlbumName2",
        new Artist(999992,"testArtistID2", "testArtistName2")));
        ih.addSongToDB(songInPlaylist);

        ph.addSongToPlaylist(validUser, songInPlaylist);


        songNotInPlaylist = new Song (999993, "testSongID3", "testSongNam3",
        new Artist(999993,"testArtistID3", "testArtistName3"),
        new MMAD.Album(999993,"testAlbumID3" , "testAlbumName3",
        new Artist(999993,"testArtistID3", "testArtistName3")));
        ih.addSongToDB(songNotInPlaylist);
    }

    @After
    public void resetSongAndUserInDB(){
        ph.addSongToPlaylist(validUser, songInPlaylist);
    }

    /**
     * testing if a valid user tries to remove a valid song that is in their playlist
     * should result in true
     */
    @Test
    public void testValidUserRemovesSongThatIsInPlaylist(){
        boolean result = ph.removeSongFromPlaylist(validUser, songInPlaylist);
        assertTrue(result);
    }

    /**
     * testing if a valid user tries remove a song that is not in their playlist
     * should result in false
     */
    @Test
    public void testValidUserRemovesSongThatIsNotInPlaylist(){
        boolean result = ph.removeSongFromPlaylist(validUser, songNotInPlaylist);
        assertFalse(result);
    }


    /**
     * testing if an invalid user tries remove a song
     * should result in false
     */
    @Test
    public void testInvalidUserAddValidSongToPlaylist(){
        boolean result = ph.removeSongFromPlaylist(invalidUser, songInPlaylist);
        assertFalse(result);
    }   

}
