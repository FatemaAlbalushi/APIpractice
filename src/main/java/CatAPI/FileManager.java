package CatAPI;

import org.apache.commons.io.IOUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * This class manages the storage and retrieval of cat images.
 */
public class FileManager {

    private String dataDirectoryPath;

    /**
     * Creates a new instance of the FileManager class.
     */
    public FileManager() {
        this.dataDirectoryPath = "data/CatImages";
    }


    /**
     * this class  is used to set the DataDirectoryPat
     * @param dataDirectoryPath
     */
    public void setDataDirectoryPath(String dataDirectoryPath) {
        this.dataDirectoryPath = dataDirectoryPath;
    }

    public String getDataDirectoryPath() {
        return dataDirectoryPath;
    }


    /**
     * Creates the data directory if it does not exist.
     * @throws IOException If an error occurs while creating the directory.
     */
    public void createDataDirectoryIfNotExists() throws IOException {
        Path dataDirectory = Paths.get(dataDirectoryPath);
        if (!Files.exists(dataDirectory)) {
            Files.createDirectory(dataDirectory);
        }
    }

    /**
     * Generates a unique filename for a cat image.
     * @return A string representing the filename to be used to save the image.
     */
    public String generateUniqueFilename() {
        String uniqueId = UUID.randomUUID().toString();
        return dataDirectoryPath + "/" + uniqueId + ".jpg";
    }

    /**
     * Checks whether a cat image with a given URL has already been saved.
     * @param imageUrl The URL of the cat image to check.
     * @return true if an image with the given URL has already been saved; false otherwise.
     */
    public boolean isImageAlreadySaved(String imageUrl) {
        String imageName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
        Path imagePath = Paths.get(dataDirectoryPath, imageName);
        return Files.exists(imagePath);
    }

    /**
     * Saves a cat image to a file with a given filename.
     * @param imageUrl The URL of the cat image to save.
     * @param filename The name of the file to save the image to.
     * @throws IOException If an error occurs while saving the image.
     */
    public void saveImage(String imageUrl, String filename) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(filename)) {
            byte[] imageBytes = IOUtils.toByteArray(new URL(imageUrl));
            fos.write(imageBytes);
        }
    }


}

