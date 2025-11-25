package project.model;

/**
 * BaseEntity demonstrates inheritance.
 * All database entities will extend this class.
 */
public abstract class BaseEntity {
    private int id;

    public BaseEntity() {}

    public BaseEntity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
