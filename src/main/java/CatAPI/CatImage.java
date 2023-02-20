package CatAPI;

import java.io.IOException;

/**
 * This class represents a cat image.
 */
public class CatImage {

    private final String imageUrl;
    private final String uniqueFilename;

    /**
     * Creates a new instance of the CatImage class with the specified image URL and unique filename.
     * @param imageUrl The URL of the cat image.
     * @param uniqueFilename The unique filename to be used to save the image.
     */
    public CatImage(String imageUrl, String uniqueFilename) {
        this.imageUrl = imageUrl;
        this.uniqueFilename = uniqueFilename;
    }

    /**
     * Gets the URL of the cat image.
     * @return A string representing the URL of the cat image.
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Gets the unique filename of the cat image.
     * @return A string representing the unique filename of the cat image.
     */
    public String getUniqueFilename() {
        return uniqueFilename;
    }

    /**
     * Saves the cat image to a file with the unique filename.
     * @param fileManager An instance of the FileManager class used to save the image.
     * @throws IOException If an error occurs while saving the image.
     */
    public void saveToFile(FileManager fileManager) throws IOException {
        if (!fileManager.isImageAlreadySaved(imageUrl)) {
            fileManager.saveImage(imageUrl, uniqueFilename);
            System.out.println("Saved image with URL: " + imageUrl + " to file: " + uniqueFilename);
        } else {
            System.out.println("Image with URL: " + imageUrl + " has already been saved.");
        }
    }
}

