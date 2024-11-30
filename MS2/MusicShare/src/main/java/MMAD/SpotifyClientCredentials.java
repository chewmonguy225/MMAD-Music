package MMAD;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SpotifyClientCredentials {
    private String filePath = "MusicShare\\src\\main\\java\\MMAD\\clientCredentials.txt";
    private static String clientID;
    private static String clientSecret;

    public SpotifyClientCredentials() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            clientID = reader.readLine(); 
            clientSecret = reader.readLine(); 
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
