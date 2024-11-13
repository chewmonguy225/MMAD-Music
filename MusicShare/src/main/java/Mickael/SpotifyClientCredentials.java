package Mickael;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SpotifyClientCredentials {
    private String filePath = "src/main/java/Mickael/clientCredentials.txt";
    private static String clientID;
    private static String clientSecret;

    public SpotifyClientCredentials() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            clientID = reader.readLine(); // Read the first line
            clientSecret = reader.readLine(); // Read the second line
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getClientID(){
        return clientID;
    }

    public static String getClientSecret(){
        return clientSecret;
    }

}
