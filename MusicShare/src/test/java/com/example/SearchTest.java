//Mickael Agustin
package com.example;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

import MMAD.Song;
import MMAD.Album;
import MMAD.Artist;
import MMAD.SpotifyAPIQueryBuilder;

import java.util.ArrayList;

public class SearchTest {

    private SpotifyAPIQueryBuilder api;

    @Before
    public void setUp() {
        api = SpotifyAPIQueryBuilder.access();
    }

    @Test // Test search with an empty string
    public void testingEmptySongSearch() {
        String songTitle = "";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            api.searchSong(songTitle);
        });

        assertEquals("Song title cannot be null or empty", exception.getMessage());
    }

    @Test // search with empty String
    public void testingSongSearch() {
        String songTitle = "let it be";
        ArrayList<Song> theSongs = api.searchSong(songTitle);

        // Search does not return empty
        assertNotNull(theSongs);

        // searchSong returns song objects
        for (Song song : theSongs) {
            assertTrue(song instanceof Song); // Ensure each element is of type Song
        }
    }

    @Test
    public void testingAlbumSearch() {
        String albumTitle = "let it be";
        ArrayList<Album> theAlbums = api.searchAlbum(albumTitle);

        // Search does not return empty
        assertNotNull(theAlbums);

        // searchAlbum should return album objects
        for (Album album : theAlbums) {
            assertTrue(album instanceof Album); // Ensure each element is of type Song
        }
    }

    @Test // Test search with an empty string
    public void testingEmptyAlbumSearch() {
        String albumTitle = "";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            api.searchAlbum(albumTitle);
        });

        assertEquals("Album title cannot be null or empty", exception.getMessage());
    }

    @Test
    public void testingArtistSearch() {
        String artistName = "the beatles";
        ArrayList<Artist> theArtists = api.searchArtist(artistName);

        // Search does not return empty
        assertNotNull(theArtists);

        // searchAlbum should return album objects
        for (Artist artist : theArtists) {
            assertTrue(artist instanceof Artist); // Ensure each element is of type Song
        }
    }

    @Test
    public void testingEmptyArtistSearch() {
        String artistName = "";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            api.searchArtist(artistName);
        });

        assertEquals("artistName cannot be null or empty", exception.getMessage());
    }
}
