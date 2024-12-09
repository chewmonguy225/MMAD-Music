// Aayaan Shaikh
package com.example;

import java.util.ArrayList;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;

import MMAD.AccountHandler;
import MMAD.Artist;
import MMAD.DBHandler;
import MMAD.Login;
import MMAD.Review;
import MMAD.ReviewHandler;
import MMAD.User;

public class WriteReviewTest {

    private ReviewHandler rh;
    private DBHandler dbh;
    private AccountHandler ah;
    private User testUser;
    private Artist testArtist;
    private String testReviewDescription;
    private int testRating;
    private Review testReview;

    @Before
    public void setUp() {
        // Access handlers
        rh = ReviewHandler.access();
        dbh = DBHandler.access();
        ah = AccountHandler.access();

        // Set up test user and artist
        testUser = new User(new Login("testUser", "password"));
        ah.createAccount("testUser", "password");

        testArtist = new Artist(00000, "00000", "testArtistName");
        testReviewDescription = "Test artist review";
        testRating = 6;
        testReview = new Review(testUser, testArtist, testReviewDescription, testRating);

        // Remove existing reviews for this artist and user in the database
        while (dbh.deleteReview(testReview)) 
        {  
        }
    }

    @Test
    public void testValidWriteReview() {
        // Test creating a valid review
        Review tempReview = rh.createReview(testUser, testArtist, testReviewDescription, testRating);

        // Verify the review is stored in the database
        ArrayList<String> dbReview = dbh.getReview(testUser.getLogin(), testArtist);

        assertNotNull("Review should be saved in the database.", dbReview);
        assertEquals("Description should match.", testReviewDescription, dbReview.get(1));
        assertEquals("Rating should match.", String.valueOf(testRating), dbReview.get(2));
    }

    @Test
    public void testInvalidWriteReview() {
        // Test creating a review with invalid data (e.g., null description)
        Review tempReview = rh.createReview(testUser, testArtist, null, testRating);

        // Verify the review is not created or stored in the database
        assertNull("Review should not be created with invalid data.", tempReview);
    }

    @After
    public void cleanUp() {
        // Clean up test data to avoid side effects
        while (dbh.deleteReview(testReview)) 
        {  
        }
        // Delete test account
        ah.deleteAccount();
    }
}
