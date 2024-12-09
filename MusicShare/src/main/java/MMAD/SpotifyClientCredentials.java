package MMAD;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SpotifyClientCredentials {
    private static String clientID;
    private static String clientSecret;

    public SpotifyClientCredentials() {
        // Use the classloader to load the file from the resources folder
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(getClass().getClassLoader().getResourceAsStream("clientCredentials.txt")))) {
            if (reader != null) {
                clientID = reader.readLine();
                clientSecret = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getClientID() {
        return clientID;
    }

    public static String getClientSecret() {
        return clientSecret;
    }
}
