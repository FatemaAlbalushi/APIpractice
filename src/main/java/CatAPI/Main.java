package CatAPI;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class is the entry point of the program and fetches cat images from an API and saves them on the local machine.
 */
public class Main {

    /**
     * The entry point of the program.
     */
    public static void main(String[] args) {
        try {
            FileManager fileManager = new FileManager();
            fileManager.createDataDirectoryIfNotExists();

            CatAPI catAPI = new CatAPI();
            String catImageUrl = catAPI.getCatImageUrl();

            String uniqueFilename = fileManager.generateUniqueFilename();
            CatImage catImage = new CatImage(catImageUrl, uniqueFilename);
            catImage.saveToFile(fileManager);
        } catch (IOException e) {
            System.err.println("An error occurred while fetching or saving the cat image: " + e.getMessage());
        }
    }
}
