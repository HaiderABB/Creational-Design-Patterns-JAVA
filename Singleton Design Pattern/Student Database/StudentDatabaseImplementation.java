// User class

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

abstract class User {
  protected String username;
  protected String password;
  protected String type;

  public User(String username, String password, String type) {
    this.username = username;
    this.password = password;
    this.type = type;
  }

  public abstract boolean login(String username, String password);
}

// Student class
class Student extends User {
  public Student(String username, String password) {
    super(username, password, "student");
  }

  @Override
  public boolean login(String username, String password) {
    // Perform authentication specific to students
    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/school_db", "sa", "12345678")) {
      String sql = "SELECT * FROM students WHERE username=? AND password=?";
      try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, username);
        stmt.setString(2, password);
        try (ResultSet rs = stmt.executeQuery()) {
          return rs.next();
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }
}

// Teacher class
class Teacher extends User {
  public Teacher(String username, String password) {
    super(username, password, "teacher");
  }

  @Override
  public boolean login(String username, String password) {
    // Perform authentication specific to teachers
    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/school_db", "sa", "12345678")) {
      String sql = "SELECT * FROM teachers WHERE username=? AND password=?";
      try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, username);
        stmt.setString(2, password);
        try (ResultSet rs = stmt.executeQuery()) {
          return rs.next();
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }
}

// Database class (Singleton)
class Database {
  private static Database instance;

  private Database() {
  } // private constructor to prevent instantiation from outside the class

  public static synchronized Database getInstance() {
    if (instance == null) {
      instance = new Database();
    }
    return instance;
  }
}

// Main class for testing
public class StudentDatabaseImplementation {
  public static void main(String[] args) {
    Student student = new Student("haider", "haider123");
    Teacher teacher = new Teacher("amirwali", "amir123");

    // Attempt login
    System.out.println("Student login result: " + student.login("haider", "haider123"));
    System.out.println("Teacher login result: " + teacher.login("amirwali", "amir123"));

    Database database1 = Database.getInstance();
    Database database2 = Database.getInstance();

    System.out.println("Are the database instances the same? " + (database1 == database2));
  }
}