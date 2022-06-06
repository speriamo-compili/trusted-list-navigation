package trustedlist.models.utility;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Utility class to make HTTP requests.
 */
public class RequestsHandler {

    /**
     * Used to make a GET request to the url.
     * @param urlToRead Url to make a get request
     * @return Response as string
     * @throws Exception If something doesn't work
     */
    public static String get(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        // Try to decode response
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()))) {
            for (String line; (line = reader.readLine()) != null; ) {
                result.append(line);
            }
        }
        return result.toString();
    }
}
