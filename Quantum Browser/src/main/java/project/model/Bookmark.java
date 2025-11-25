package project.model;

/**
 * Bookmark entity extending BaseEntity (inheritance).
 */
public class Bookmark extends BaseEntity {

    private String title;
    private String url;

    public Bookmark() {
    }

    public Bookmark(int id, String title, String url) {
        super(id);
        this.title = title;
        this.url = url;
    }

    public Bookmark(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return title + " - " + url;
    }
}
