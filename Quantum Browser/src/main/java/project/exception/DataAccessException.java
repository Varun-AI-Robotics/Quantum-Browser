package project.exception;

/**
 * Custom unchecked exception for wrapping SQLExceptions.
 * Demonstrates user-defined exception + exception handling.
 */
public class DataAccessException extends RuntimeException {

    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
