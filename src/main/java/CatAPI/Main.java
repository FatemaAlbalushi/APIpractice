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

            // Get a list of all saved cat images
            List<String> savedImageFilenames = new ArrayList<>();
            Path dataDirectory = Path.of(fileManager.getDataDirectoryPath());
            Files.walk(dataDirectory)
                    .filter(Files::isRegularFile)
                    .forEach(file -> savedImageFilenames.add(file.toString()));

            // Choose a random image from the list
            if (savedImageFilenames.size() > 0) {
                String randomImageFilename = savedImageFilenames.get(new Random().nextInt(savedImageFilenames.size()));
                System.out.println("Displaying image: " + randomImageFilename);
                // code to display the image
            } else {
                System.out.println("No saved cat images found.");
            }

        } catch (IOException e) {
            System.err.println("An error occurred while fetching or saving the cat image: " + e.getMessage());
        }
    }
}
