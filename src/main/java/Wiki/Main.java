package Wiki;

import java.util.Scanner;

/**
 * Main class that searches for Wikipedia articles related to a user inputted topic and displays
 * the first three search results with their titles and snippets.
 */
public class Main {

    /**
     * Main method that executes the program.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        // Prompt the user to enter a topic to search for
        System.out.println("Enter a topic to search for on Wikipedia: ");
        Scanner scanner = new Scanner(System.in);
        String topic = scanner.nextLine();

        // Create a Wikipedia API object
        WikipediaApi wikiApi = new WikipediaApi();

        // Search for articles related to the topic
        WikipediaArticle[] articles = wikiApi.search(topic).toArray(new WikipediaArticle[0]);

        // Display the first three search results with their titles and snippets
        for (int i = 0; i < articles.length; i++) {
            WikipediaArticle article = articles[i];
            String title = article.getTitle();
            String snippet = article.getSnippet();
            String url = article.getUrl();
            System.out.println("Title: " + title);
            System.out.println("Snippet: " + snippet);
            System.out.println("URL: " + url);
            System.out.println();
        }
    }
}
