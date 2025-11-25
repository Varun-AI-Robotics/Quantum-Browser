package project.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import project.exception.DataAccessException;
import project.model.BaseEntity;

/**
 * BaseDAO is an abstract class implementing common DAO operations.
 * It demonstrates inheritance and exception handling.
 */
public abstract class BaseDAO<T extends BaseEntity> implements GenericDAO<T> {

    protected final Connection connection;

    protected BaseDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public abstract void save(T entity);

    @Override
    public abstract List<T> findAll();

    @Override
    public abstract void clearAll();

    protected void handleSqlException(SQLException ex) {
        throw new DataAccessException("Database error: " + ex.getMessage(), ex);
    }
}
