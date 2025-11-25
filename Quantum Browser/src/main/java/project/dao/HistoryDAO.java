package project.dao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import project.model.HistoryEntry;

/**
 * DAO class for history entries.
 */
public class HistoryDAO extends BaseDAO<HistoryEntry> {

    public HistoryDAO(Connection connection) {
        super(connection);
        createTableIfNeeded();
    }

    private void createTableIfNeeded() {
        String sql = "CREATE TABLE IF NOT EXISTS history (" +
                     "id INT PRIMARY KEY AUTO_INCREMENT," +
                     "url TEXT," +
                     "visited_at TIMESTAMP)";
        try (Statement st = connection.createStatement()) {
            st.execute(sql);
        } catch (SQLException ex) {
            handleSqlException(ex);
        }
    }

    @Override
    public void save(HistoryEntry entry) {
        String sql = "INSERT INTO history(url, visited_at) VALUES(?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, entry.getUrl());
            ps.setTimestamp(2, Timestamp.valueOf(entry.getVisitedAt()));
            ps.executeUpdate();
        } catch (SQLException ex) {
            handleSqlException(ex);
        }
    }

    @Override
    public List<HistoryEntry> findAll() {
        List<HistoryEntry> result = new ArrayList<>();
        String sql = "SELECT id, url, visited_at FROM history ORDER BY visited_at DESC";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                HistoryEntry h = new HistoryEntry(
                        rs.getInt("id"),
                        rs.getString("url"),
                        rs.getTimestamp("visited_at").toLocalDateTime()
                );
                result.add(h);
            }
        } catch (SQLException ex) {
            handleSqlException(ex);
        }
        return result;
    }

    @Override
    public void clearAll() {
        try (Statement st = connection.createStatement()) {
            st.executeUpdate("DELETE FROM history");
        } catch (SQLException ex) {
            handleSqlException(ex);
        }
    }
}
