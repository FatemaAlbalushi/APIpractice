package org.example;
import com.google.gson.Gson;
import okhttp3.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
public class Main {
    public static void main(String[] args) {

        Gson gson = new Gson();
        File dataDirectory = new File("data");
        if (!dataDirectory.exists()) {
            dataDirectory.mkdir();
        }

        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://maps.googleapis.com/maps/api/distancematrix/json").newBuilder();
        urlBuilder.addQueryParameter("origins", "New York, NY, USA");
        urlBuilder.addQueryParameter("destinations", "Washington, DC, USA");
        urlBuilder.addQueryParameter("units", "imperial");


        OkHttpClient client = new OkHttpClient().newBuilder().build();

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .get()
                .build();
//        try (Response response = client.newCall(request).execute()) {
//            System.out.println(response.body().string());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        FileReader reader = null;
        try {
            reader = new FileReader("data/gmap_distance_matrix_response.json");
            HashMap<String, Object> map = gson.fromJson(reader, HashMap.class);
            ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) map.get("rows");
            ArrayList<Map<String, Object>> elements = (ArrayList<Map<String, Object>>) rows.get(0).get("elements");
            Map<String, Object> duration = (Map<String, Object>) elements.get(0).get("duration");
            String durationText = (String) duration.get("text");

            System.out.println("duration: " + durationText);
            //  System.out.println(map);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Exiting Application!");
    }
}