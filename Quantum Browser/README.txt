Quantum Browser - JavaFX Project
==============================================

This project is designed to satisfy your Java GUI Project rubric:

1. OOP Implementation (Polymorphism, Inheritance, Exception Handling, Interfaces)
   - Inheritance: BaseEntity -> Bookmark, HistoryEntry
   - Interfaces & Generics: GenericDAO<T extends BaseEntity>
   - Abstract class: BaseDAO<T> implements GenericDAO<T>
   - Exception Handling: custom DataAccessException wraps SQLExceptions
   - Polymorphism: DAOs use the GenericDAO interface for different entities.

2. Collections & Generics
   - Uses List<T>, ObservableList<T>, and GenericDAO<T> with type parameters.
   - ObservableList<Bookmark> and ObservableList<HistoryEntry> back ListView controls.

3. Multithreading & Synchronization
   - ExecutorService dbExecutor runs all DB work on a background thread.
   - synchronized void addHistoryEntry(...) ensures thread-safe history updates.

4. Classes for Database Operations
   - DBUtil, BaseDAO, BookmarkDAO, HistoryDAO fully encapsulate DB logic.

5. Database Connectivity (JDBC)
   - Uses MySQL via mysql-connector-java dependency in pom.xml.
   - URL: jdbc:mysql://localhost:3306/quantum_browser (change in DBUtil if needed).

6. Implement JDBC for Database Connectivity
   - BookmarkDAO & HistoryDAO use PreparedStatement, ResultSet, etc.
   - Tables 'bookmarks' and 'history' are auto-created if they don't exist.

How to run
----------

1. Create MySQL database (once):

   CREATE DATABASE quantum_browser;

2. Update credentials in:
   src/main/java/project/dao/DBUtil.java
   (URL, USER, PASSWORD if different on your system)

3. In a terminal, go to the project folder (where pom.xml is):

   mvn -U clean install
   mvn javafx:run

You should see the Quantum Browser UI with:

- Dark top bar (Home, URL field, Go, Search, Bookmark button)
- Big WebView in the center
- Right side panel with Bookmarks and History (persisted to MySQL)
- Status bar at the bottom.
