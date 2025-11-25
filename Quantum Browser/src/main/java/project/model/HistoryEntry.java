package project.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * History entry entity.
 */
public class HistoryEntry extends BaseEntity {

    private String url;
    private LocalDateTime visitedAt;

    public HistoryEntry() {
    }

    public HistoryEntry(String url, LocalDateTime visitedAt) {
        this.url = url;
        this.visitedAt = visitedAt;
    }

    public HistoryEntry(int id, String url, LocalDateTime visitedAt) {
        super(id);
        this.url = url;
        this.visitedAt = visitedAt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDateTime getVisitedAt() {
        return visitedAt;
    }

    public void setVisitedAt(LocalDateTime visitedAt) {
        this.visitedAt = visitedAt;
    }

    @Override
    public String toString() {
        String time = visitedAt != null
                ? visitedAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                : "";
        return time + " - " + url;
    }
}
