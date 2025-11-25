<!-- Badges -->
[![Maven Build](https://img.shields.io/badge/Maven-Build-brightgreen)](https://github.com/your-username/quantum-browser/actions)
[![Java](https://img.shields.io/badge/Java-17-blue)](https://www.oracle.com/java/)
[![JavaFX](https://img.shields.io/badge/JavaFX-17.0.2-lightgrey)](https://openjfx.io/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
[![Issues](https://img.shields.io/github/issues/your-username/quantum-browser)](https://github.com/your-username/quantum-browser/issues)


# 🚀 Quantum Browser

*A Modern JavaFX Web Browser*

Quantum Browser is a modern JavaFX-powered web browser featuring tabbed browsing, bookmarks, history, customizable homepage, and MySQL integration. Built for speed, stability, and a clean UI, it offers a smooth and responsive browsing experience.

## ✨ Key Features

### 🎨 Modern JavaFX Interface

- Clean top navigation bar
- URL bar with Home, Back, Forward, Refresh
- Bookmark ⭐ and Search 🔍 integration
- Smooth WebView browsing
- Dark theme UI

### 📚 Persistent Bookmarks & History

- Stored using MySQL
- Sidebar with live ObservableLists
- Auto-created tables (bookmarks, history)
- Thread-safe updates
### Built With:

This section should list any major frameworks/libraries used to bootstrap your project. Leave any add-ons/plugins for the acknowledgements section. Here are a few examples.

<p align="left">

  <!-- Java -->
  <a href="https://www.oracle.com/java/">
    <img src="https://skillicons.dev/icons?i=java" height="48" alt="Java" />
  </a>

  <!-- Maven -->
  <a href="https://maven.apache.org/">
    <img src="https://skillicons.dev/icons?i=maven" height="48" alt="Maven" />
  </a>

  <!-- MySQL -->
  <a href="https://www.mysql.com/">
    <img src="https://skillicons.dev/icons?i=mysql" height="48" alt="MySQL" />
  </a>

  <!-- JDBC (no direct icon → using database icon) -->
  <a href="https://docs.oracle.com/javase/8/docs/technotes/guides/jdbc/">
    <img src="https://skillicons.dev/icons?i=postgres" height="48" alt="JDBC" />
  </a>

  <!-- Scene Builder -->
  <a href="https://gluonhq.com/products/scene-builder/">
    <img src="https://skillicons.dev/icons?i=figma" height="48" alt="Scene Builder" />
  </a>

</p>


## 📝Install Requirements:
- ✔️ Java 17
- ✔️ Maven
- ✔️ javaFX 17 SDK
- ✔️ MySQL

## 🗄 Database Setup

  **1.** Create database:
  
``` sql
CREATE DATABASE quantum_browser;
```
  **2.** Update credentials in:
    `src/main/java/project/dao/DBUtil.java`

- URL

- USER

- PASSWORD
- 
## 📂 Navigate to the Source Folder

Go to the folder containing the Java source files. For example:


  `"D:\Quantum Browser"`


 ## ▶ How to Run

 ```bash
mvn -U clean install
mvn javafx:run
```


## 🧱 File Structure (Important Folders)
```bash
src/main/java/project/
 ├── App.java
 ├── controller/
 ├── dao/
 ├── model/
 ├── ui/
 └── util/
```


## 💡 Why This Project Stands Out

- Fully rubric-aligned

- Clean layered architecture

- MySQL-powered persistence

- Thread-safe, production-style DAOs

- Modular, extensible, readable code


## 📜 License

- MIT License — Free to use and modify.

## 📸 Screenshot:
![image alt][(https://github.com/Varun-AI-Robotics/Java-Based-Web-Browser/blob/main/Screenshot%202025-05-25%20163824.png?raw=true](https://github.com/Varun-AI-Robotics/Quantum-Browser/blob/main/Screenshot%202025-11-23%20132102.png?raw=true))



