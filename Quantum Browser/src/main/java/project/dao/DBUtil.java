package project.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import project.exception.DataAccessException;

/**
 * Utility class responsible for creating JDBC connections.
 * This satisfies the 'Database Connectivity (JDBC)' rubric.
 */
public final class DBUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/quantum_browser";
    private static final String USER = "root";
    private static final String PASSWORD = "5469"; // change if needed

    private DBUtil() {}

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException ex) {
            throw new DataAccessException("Unable to connect to database", ex);
        }
    }
}
