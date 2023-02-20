package Wiki;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that interacts with the Wikipedia API to search for articles and extract information.
 */
public class WikipediaApi {
    private static final String API_URL = "https://en.wikipedia.org/w/api.php";

    /**
     * Searches for Wikipedia articles related to the specified topic and returns a list of search results.
     *
     * @param topic the topic to search for
     * @return a list of search results
     * @throws IOException if an error occurs while making the API request
     */
    public List<WikipediaArticle> search(String topic) {
        // Build the URL for the Wikipedia API
        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://en.wikipedia.org/w/api.php").newBuilder();
        urlBuilder.addQueryParameter("action", "query");
        urlBuilder.addQueryParameter("format", "json");
        urlBuilder.addQueryParameter("list", "search");
        urlBuilder.addQueryParameter("srsearch", topic);
        urlBuilder.addQueryParameter("utf8", "1");

        // Create an HTTP client
        OkHttpClient client = new OkHttpClient();

        // Create a request
        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .get()
                .build();

        // Execute the request and get the response
        Response response = null;
        try {
            response = client.newCall(request).execute();
            String responseBody = response.body().string();

            // Parse the response JSON
            JsonParser jsonParser = new JsonParser();
            JsonObject rootObject = jsonParser.parse(responseBody).getAsJsonObject();
            JsonArray searchResults = rootObject.getAsJsonObject("query").getAsJsonArray("search");

            // Create a list of WikipediaArticle objects from the search results
            List<WikipediaArticle> articles = new ArrayList<>();
            for (int i = 0; i < searchResults.size(); i++) {
                JsonObject result = searchResults.get(i).getAsJsonObject();
                String title = result.get("title").getAsString();
                String snippet = result.get("snippet").getAsString().replaceAll("<.*?>", "");
                String pageId = result.get("pageid").getAsString();
                String url = "https://en.wikipedia.org/wiki?curid=" + pageId;
                WikipediaArticle article = new WikipediaArticle(title, snippet, url);
                articles.add(article);
            }
            return articles;
        } catch (IOException e) {
            System.out.println("An error occurred. Error message: " + e.getMessage());
            return null;
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

}
