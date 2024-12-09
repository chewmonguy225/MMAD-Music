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
    private Review testReview;
    private String testReviewDescription;
    private int testRating;

    @Before
    public void setUp() {

        // Access handlers
        rh = ReviewHandler.access();
        dbh = DBHandler.access();
        ah = AccountHandler.access();

        // Set up test user and artist
        testUser = new User(new Login("testUser", "password"));
        ah.createAccount("testUser", "password");

        testArtist = new Artist(00000, "testArtistName");
        testReviewDescription = "Test artist review";
        testRating = 6;

        // Remove existing reviews for this artist and user in the database
        ArrayList<String> userReviews = dbh.getUserReviews(testUser.getLogin());
        for (String reviewID : userReviews) {
            dbh.deleteReview(reviewID);
        }
    }

    @Test
    public void testingWriteReview() {
        // Testing creating a review
        testReview = rh.createReview(testUser, testArtist, testReviewDescription, testRating);

        // Verify the review is stored in the database
        ArrayList<String> dbReview = dbh.getReview(testUser.getLogin(), testArtist);

        assertNotNull("Review should be saved in the database.", dbReview);
        assertEquals("Description should match.", testReviewDescription, dbReview.get(1));
        assertEquals("Rating should match.", String.valueOf(testRating), dbReview.get(2));
    }

    @Test
    public void testingDeleteReview() {
        // Testing deleting a review
        testReview = rh.createReview(testUser, testArtist, testReviewDescription, testRating);
        rh.deleteReview(testUser, testArtist);

        // Verify the review is no longer in the database
        ArrayList<String> dbReview = dbh.getReview(testUser.getLogin(), testArtist);

        assertNull("Review should be removed from the database.", dbReview);
    }

    @After
    public void cleanUp() {
        // Clean up test data to avoid side effects
        ArrayList<String> userReviews = dbh.getUserReviews(testUser.getLogin());
        for (String reviewID : userReviews) {
            dbh.deleteReview(reviewID);
        }
        // Delete test account
        ah.deleteAccount();
    }
}
