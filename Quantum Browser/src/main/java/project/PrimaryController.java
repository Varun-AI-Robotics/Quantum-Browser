package project;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import project.dao.BookmarkDAO;
import project.dao.DBUtil;
import project.dao.HistoryDAO;
import project.model.Bookmark;
import project.model.HistoryEntry;

/**
 * Main controller for Quantum Browser.
 *
 * Demonstrates:
 * - OOP (uses DAO classes, entities, custom exception)
 * - Collections & Generics (ObservableList<Bookmark>, List<HistoryEntry>, etc.)
 * - Multithreading & Synchronization (ExecutorService for DB operations)
 */
public class PrimaryController {

    @FXML private WebView webView;
    @FXML private TextField addressBar;
    @FXML private ListView<Bookmark> bookmarksList;
    @FXML private ListView<HistoryEntry> historyList;
    @FXML private Label statusLabel;

    private WebEngine engine;

    // DAO instances (database layer)
    private BookmarkDAO bookmarkDAO;
    private HistoryDAO historyDAO;

    // Observable collections for UI binding
    private final ObservableList<Bookmark> bookmarks = FXCollections.observableArrayList();
    private final ObservableList<HistoryEntry> history = FXCollections.observableArrayList();

    // Single threaded executor for background DB operations
    private final ExecutorService dbExecutor = Executors.newSingleThreadExecutor();

    @FXML
    public void initialize() {
        engine = webView.getEngine();
        engine.load("https://www.google.com");

        bookmarkDAO = new BookmarkDAO(DBUtil.getConnection());
        historyDAO = new HistoryDAO(DBUtil.getConnection());

        bookmarksList.setItems(bookmarks);
        historyList.setItems(history);

        // Load bookmarks & history from DB in background thread
        dbExecutor.submit(() -> {
            List<Bookmark> bk = bookmarkDAO.findAll();
            List<HistoryEntry> hs = historyDAO.findAll();

            Platform.runLater(() -> {
                bookmarks.setAll(bk);
                history.setAll(hs);
                statusLabel.setText("Loaded bookmarks & history from DB");
            });
        });

        // Track page navigation and add to history automatically
        engine.locationProperty().addListener((obs, oldLoc, newLoc) -> {
            if (newLoc != null && !newLoc.isEmpty()) {
                HistoryEntry entry = new HistoryEntry(newLoc, LocalDateTime.now());
                addHistoryEntry(entry);
            }
        });

        statusLabel.setText("Ready");
    }

    // --- UI Actions ---

    @FXML
    private void onHome(ActionEvent event) {
        engine.load("https://www.google.com");
    }

    @FXML
    private void onGo(ActionEvent event) {
        loadPage(addressBar.getText());
    }

    @FXML
    private void onSearch(ActionEvent event) {
        String query = addressBar.getText();
        if (query == null || query.isBlank()) return;

        String url = "https://www.google.com/search?q=" + query.replace(" ", "+");
        engine.load(url);
    }

    @FXML
    private void onAddBookmark(ActionEvent event) {
        String currentUrl = engine.getLocation();
        if (currentUrl == null || currentUrl.isBlank()) return;

        TextInputDialog dialog = new TextInputDialog(currentUrl);
        dialog.setHeaderText("Add Bookmark");
        dialog.setContentText("Title:");
        dialog.setTitle("Bookmark");

        dialog.showAndWait().ifPresent(title -> {
            Bookmark bookmark = new Bookmark(title, currentUrl);
            bookmarks.add(0, bookmark); // update UI immediately

            // persist in DB in background thread
            dbExecutor.submit(() -> bookmarkDAO.save(bookmark));
            statusLabel.setText("Bookmark added");
        });
    }

    @FXML
    private void onBookmarkDoubleClick() {
        Bookmark selected = bookmarksList.getSelectionModel().getSelectedItem();
        if (selected != null) {
            loadPage(selected.getUrl());
        }
    }

    @FXML
    private void onClearHistory(ActionEvent event) {
        history.clear();
        dbExecutor.submit(() -> historyDAO.clearAll());
        statusLabel.setText("History cleared from DB");
    }

    // --- Helper methods using synchronization ---

    /**
     * Adds a history entry in a thread-safe way and persists it asynchronously.
     */
    private synchronized void addHistoryEntry(HistoryEntry entry) {
        history.add(0, entry); // thread-safe because method is synchronized
        dbExecutor.submit(() -> historyDAO.save(entry));
    }

    private void loadPage(String url) {
        if (url == null || url.isBlank()) return;

        // simple heuristic to add protocol if missing
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "http://" + url;
        }
        engine.load(url);
        statusLabel.setText("Loading " + url);
    }

    // Clean up executor when window is closed (optional)
    public void shutdown() {
        dbExecutor.shutdownNow();
    }
}
