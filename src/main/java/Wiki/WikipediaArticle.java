package Wiki;

public class WikipediaArticle {
    private String title;
    private String snippet;
    private String url;

    public WikipediaArticle(String title, String snippet, String url) {
        this.title = title;
        this.snippet = snippet;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getSnippet() {
        return snippet;
    }

    public String getUrl() {
        return url;
    }

    public void display() {
        System.out.println("Title: " + title);
        System.out.println("Snippet: " + snippet);
        System.out.println("URL: " + url);
        System.out.println();
    }
}
