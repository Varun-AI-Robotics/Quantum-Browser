package project.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import project.model.Bookmark;

/**
 * DAO class for bookmarks.
 */
public class BookmarkDAO extends BaseDAO<Bookmark> {

    public BookmarkDAO(Connection connection) {
        super(connection);
        createTableIfNeeded();
    }

    private void createTableIfNeeded() {
        String sql = "CREATE TABLE IF NOT EXISTS bookmarks (" +
                     "id INT PRIMARY KEY AUTO_INCREMENT," +
                     "title VARCHAR(255)," +
                     "url TEXT)";
        try (Statement st = connection.createStatement()) {
            st.execute(sql);
        } catch (SQLException ex) {
            handleSqlException(ex);
        }
    }

    @Override
    public void save(Bookmark bookmark) {
        String sql = "INSERT INTO bookmarks(title, url) VALUES(?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, bookmark.getTitle());
            ps.setString(2, bookmark.getUrl());
            ps.executeUpdate();
        } catch (SQLException ex) {
            handleSqlException(ex);
        }
    }

    @Override
    public List<Bookmark> findAll() {
        List<Bookmark> result = new ArrayList<>();
        String sql = "SELECT id, title, url FROM bookmarks ORDER BY id DESC";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Bookmark b = new Bookmark(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("url")
                );
                result.add(b);
            }
        } catch (SQLException ex) {
            handleSqlException(ex);
        }
        return result;
    }

    @Override
    public void clearAll() {
        try (Statement st = connection.createStatement()) {
            st.executeUpdate("DELETE FROM bookmarks");
        } catch (SQLException ex) {
            handleSqlException(ex);
        }
    }
}
