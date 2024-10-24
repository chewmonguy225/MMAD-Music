package Mickael;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;

public class SpotifyAPIConnection extends MusicAbstractAPI {
    private String clientID = "39e68d74ea964fe8b12e4d03f28c817c";
    private String clientSecret = "2e4bd1398d6e4e0aa43ed25e0116b4bf";
    private SpotifyApi connection;
    
    public SpotifyAPIConnection(){
        connect();
    }

    protected void connect(){
        connection = new SpotifyApi.Builder()
                .setClientId(clientID)
                .setClientSecret(clientSecret)
                .build();
        ClientCredentialsRequest clientCredentialsRequest = connection.clientCredentials()
                .build();

        try {
            // Execute the request synchronous
            final ClientCredentials clientCredentials = clientCredentialsRequest.execute();
            connection.setAccessToken(clientCredentials.getAccessToken());
        } catch (Exception e) {
            System.out.println("Something went wrong!\n" + e.getMessage());
        }
    }

    public SpotifyApi getConnection(){
        return connection;
    }
}
