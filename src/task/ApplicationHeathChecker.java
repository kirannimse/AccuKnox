package task;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApplicationHeathChecker {
	public static void main(String[] args) {
        String applicationUrl = "https://www.accuknox.com/"; // Replace with the actual application URL
        checkApplicationStatus(applicationUrl);
    }

    public static void checkApplicationStatus(String appUrl) {
        try {
            URL url = new URL(appUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                System.out.println("The application is up and running.");
            } else {
                System.out.println("The application is down or unavailable. HTTP Status Code: " + responseCode);
            }
        } catch (IOException e) {
            System.out.println("Error: Unable to connect to the application.");
        }
    }
}
