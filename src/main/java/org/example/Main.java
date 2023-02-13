package org.example;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Main class that performs the distance calculation between the origin and destination
 * entered by the user and saves the response to a file.
 */
public class Main {

    /**
     * Main method that executes the program.
     * @param args command line arguments.
     */
    public static void main(String[] args) {



        UserInputValidation userInputValidation = new UserInputValidation();
        // Prompt the user to enter origin
        System.out.println("Enter your origin in the format (place name, country name): ");
        String origin = userInputValidation.getUserChoiceString();

        // Prompt the user to enter destination
        System.out.println("Enter your destination in the format (place name, country name): ");
        String destination = userInputValidation.getUserChoiceString();

        // Build the URL for the Google Distance Matrix API
        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://maps.googleapis.com/maps/api/distancematrix/json").newBuilder();
        urlBuilder.addQueryParameter("origins", origin);
        urlBuilder.addQueryParameter("destinations", destination);
        urlBuilder.addQueryParameter("units", "imperial");



        // Create an HTTP client
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        // Create a request
        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .get()
                .build();

        // Execute the request and get the response
        Response response =null;
        try {
            response = client.newCall(request).execute();
            String responseBody = response.body().string();
            writetofile(responseBody);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Read the response from the file
        readfromfile();

        System.out.println("Exiting Application!");
    }

    /**
     * Reads the API response from a file and extracts the duration between the two addresses.
     */
    public static void readfromfile(){
        // Create a Gson object to parse the JSON response
        FileReader reader = null;
        try {
            // Create a Gson object to parse the JSON response
            Gson gson = new Gson();
           // Read the response from the file
            reader = new FileReader("data/response.json");
            HashMap<String, Object> map = gson.fromJson(reader, HashMap.class);

            // Get the duration from the response data
            ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) map.get("rows");
            ArrayList<Map<String, Object>> elements = (ArrayList<Map<String, Object>>) rows.get(0).get("elements");
            Map<String, Object> duration = (Map<String, Object>) elements.get(0).get("duration");
            String durationText = (String) duration.get("text");

            System.out.println("duration: " + durationText);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Writes the given response body to a file in JSON format.
     * @param responseBody the response body to be written to the file
     */
    public static void writetofile(String responseBody){
        // Create a new Gson object with pretty printing enabled
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        // Convert the response body to a Java object
        Object responseObject = gson.fromJson(responseBody, Object.class);

        try (FileWriter file = new FileWriter("data/response.json")) {
            // Write the Java object to the file in JSON format
            gson.toJson(responseObject, file);
        } catch (IOException e) {
            // Print error message if an exception occurs
            System.out.println("Error saving the response to a file: " + e.getMessage());

        }
    }
}