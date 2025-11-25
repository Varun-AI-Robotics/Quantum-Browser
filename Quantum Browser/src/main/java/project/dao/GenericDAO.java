package project.dao;

import java.util.List;
import project.model.BaseEntity;

/**
 * GenericDAO demonstrates use of interfaces + generics + collections.
 */
public interface GenericDAO<T extends BaseEntity> {

    void save(T entity);

    List<T> findAll();

    void clearAll();
}
