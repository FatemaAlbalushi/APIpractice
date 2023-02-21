package CatAPI;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


import java.io.IOException;
import java.util.Collections;

/**
 * This class interacts with the Cat API to fetch cat images.
 */
public class CatAPI {

    private final OkHttpClient client;

    /**
     * Creates a new instance of the CatAPI class.
     */
    public CatAPI() {
        this.client = new OkHttpClient();
    }

    /**
     * Fetches a cat image URL from the Cat API.
     * @return A string representing the URL of a cat image.
     * @throws IOException If an error occurs while communicating with the API.
     */
    public String getCatImageUrl() throws IOException {
        Request request = new Request.Builder()
                .url("https://api.thecatapi.com/v1/images/search")
                .get()
                .build();

        Response response = client.newCall(request).execute();
        String responseData = response.body().string();

        JSONArray jsonArray = JSON.parseArray(responseData);
        JSONObject jsonObject = jsonArray.getJSONObject(0);

        return jsonObject.getString("url");
    }
}
