// Aayaan Shaikh
package com.example;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;

import MMAD.AccountHandler;
import MMAD.ReviewHandler;
import MMAD.Login;
import MMAD.User;
import MMAD.Artist;
import MMAD.DBHandler;
import MMAD.Review;

public class ReviewTest {

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
    public void testingDeleteReview() {
        // Test deleting a review
        Review tempReview = rh.createReview(testUser, testArtist, testReviewDescription, testRating);

        rh.deleteReview(tempReview);

        // Verify the review is not stored in the database
        ArrayList<String> dbReview = dbh.getReview(testUser.getLogin(), testArtist);

        assertNull("Review should be removed from the database.", dbReview);
    }

    @Test
    public void testingInvalidDeleteReview() {
        // Testing deleting a review when review is not in database
        rh.deleteReview(testReview);

        // Verify deletion is not successful
        assertNull("Review should not be deleted properly.", testReview);
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
