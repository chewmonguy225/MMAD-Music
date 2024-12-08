package MMAD;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SQLCredentials {
    private static String driverClassName;
    private static String url;
    private static String username;
    private static String password;

    public SQLCredentials() {
        // Use the classloader to load the file from the resources folder
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(getClass().getClassLoader().getResourceAsStream("SQLCredentials.txt")))) {
            if (reader != null) {
                driverClassName = reader.readLine();
                url = reader.readLine();
                username = reader.readLine();
                password = reader.readLine();
            } else {
                System.out.println("File not found!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getDriverClassName() {
        return driverClassName;
    }

    public static String getURL() {
        return url;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }
}
