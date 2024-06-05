import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

// Mock database for student authentication
class StudentDatabase {
  private static Map<String, String> studentCredentials = new HashMap<>();

  static {
    // Initialize student credentials (username, password)
    studentCredentials.put("haider", "haider123");
    studentCredentials.put("rehan", "rehan456");
    // Add more student credentials as needed
  }

  public static boolean authenticate(String username, String password) {
    if (studentCredentials.containsKey(username)) {
      return studentCredentials.get(username).equals(password);
    }
    return false;
  }
}

class TeacherDatabase {
  private static Map<String, String> teacherCredentials = new HashMap<>();

  static {
    teacherCredentials.put("amirwali", "amir123");
    teacherCredentials.put("anoshakhan", "anosha123");

  }

  public static boolean authenticate(String username, String password) {
    if (teacherCredentials.containsKey(username)) {
      return teacherCredentials.get(username).equals(password);
    }
    return false;
  }
}