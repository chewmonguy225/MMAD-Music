package MMAD;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;


public class SpotifyAPIConnection extends MusicAbstractAPI {
    SpotifyClientCredentials theCredentials = new SpotifyClientCredentials();
    private String clientID = SpotifyClientCredentials.getClientID();
    private String clientSecret = SpotifyClientCredentials.getClientSecret();
    private SpotifyApi connection;

    public SpotifyAPIConnection() {
        connect();
    }

    protected void connect() {
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
            //System.out.println("Something went wrong!\n" + e.getMessage());
        }
    }

    public SpotifyApi getConnection() {
        return connection;
    }
}
