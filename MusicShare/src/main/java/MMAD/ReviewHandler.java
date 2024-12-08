package MMAD;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReviewHandler {
    private static ReviewHandler rh = null;
    private static DBHandler dbh = DBHandler.access();
    private static AccountHandler ah = AccountHandler.access();
    private static ItemHandler ih = ItemHandler.access();
    private final int itemsPerPage = 5;

    private ReviewHandler() {

    }

    public static ReviewHandler access() {
        if (rh == null) {
            rh = new ReviewHandler();
        }
        return rh;
    }

    public Review createReview(User user, Item item, String description, int rating) {
        item.setID(dbh.getSongID((Song) item));
        Review r = new Review(user, item, description, rating); // Create review object
        dbh.createReview(user.getLogin(), r); // Add the review to the database
        ah.getCurrentUserObject().addReview(r);
        return r;
    }

    public void setUserReviews(User user) {
        ArrayList<Review> userReviews = new ArrayList<>();

        Login login = user.getLogin(); // Create a Login object
        ArrayList<String> reviewIDs = dbh.getUserReviews(login); // Fetch review IDs for the user
        if (!reviewIDs.isEmpty()) {
            for (String reviewID : reviewIDs) {
                // Extract letters and numbers from reviewID
                String[] parts = separateLettersAndNumbers(reviewID);
                String reviewType = parts[0]; // Letters (s, a, ar, etc.)
                int itemID = Integer.parseInt(parts[1]); // Numeric part of the ID

                Item item = null;
                switch (reviewType) {
                    case "s":
                        item = ih.createSongFromID(itemID);
                        break;
                    case "al":
                        break;
                    case "ar":
                        break;
                }
                userReviews.add(getReview(user, item));

            }
        }

        user.setReviews(userReviews);
    }

    public Review getReview(User user, Item item) {
        ArrayList<String> reviewInfo = dbh.getReview(user.getLogin(), item);
        Review review = new Review(user, item, reviewInfo.get(1), Integer.parseInt(reviewInfo.get(2)));
        return review;
    }

    public ArrayList<Review> getItemReviews(Item item) {
        item.setID(dbh.getSongID((Song) item));
        ArrayList<Review> theReviews = new ArrayList<Review>();
        ArrayList<String> reviewInfo = dbh.getItemReviews(item);
        for (String username : reviewInfo) {
            User user = new User(new Login(username, null));
            theReviews.add(getReview(user, item));
        }
        return theReviews;
    }

    public ArrayList<Review> getRecentReviews() {
        ArrayList<Review> theReviews = new ArrayList<Review>();

        ArrayList<ArrayList<String>> reviewInfoList = dbh.getRecentReviews();
        System.out.println(reviewInfoList);
        for (ArrayList<String> reviewInfo : reviewInfoList) {
            User user = new User(new Login(reviewInfo.get(1), null));

            String[] parts = separateLettersAndNumbers(reviewInfo.get(0));
            String reviewType = parts[0]; // Letters (s, a, ar, etc.)
            int itemID = Integer.parseInt(parts[1]); // Numeric part of the ID
            Item item = null;
            switch (reviewType) {
                case "s":
                    item = ih.createSongFromID(itemID);
                    break;
                case "al":
                    break;
                case "ar":
                    break;
            }
            theReviews.add(getReview(user, item));
        }

        return theReviews;
    }

    public void displayReviews(ArrayList<Review> theReviews, UI ui, Display display) {
        int totalPages = (int) Math.ceil((double) theReviews.size() / itemsPerPage);
        int currentPage = 1;

        display.displayReviewsResult(theReviews, currentPage, totalPages);
        int option = ui.getInt();
        while (!((option == 6 && currentPage == 1) || (option == 7 && currentPage == totalPages))) {
            if (option == (itemsPerPage + 1) && currentPage != 1) {
                currentPage--;
                display.displayReviewsResult(theReviews, currentPage, totalPages);
                option = ui.getInt();
            } else if (option == (itemsPerPage + 2) && currentPage != totalPages) {
                currentPage++;
                display.displayReviewsResult(theReviews, currentPage, totalPages);
                option = ui.getInt();
            } else {
                display.invalidOption();
            }
        }
    }

    public void deleteReview(Review review){
        dbh.deleteReview(review);
    }

    // Helper method to separate letters and numbers in the reviewID
    private String[] separateLettersAndNumbers(String reviewID) {
        Pattern pattern = Pattern.compile("^([a-z]+)(\\d+)$");
        Matcher matcher = pattern.matcher(reviewID);

        if (matcher.find()) {
            String letters = matcher.group(1); // Group 1: Letters
            String numbers = matcher.group(2); // Group 2: Numbers
            return new String[] { letters, numbers };
        }

        return new String[] { "", "" }; // Default values if no match is found
    }

}
