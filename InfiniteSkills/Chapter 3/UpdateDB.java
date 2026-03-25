import java.sql.*;

public class UpdateDB {

   Connection connection;

   public UpdateDB() {
      try {
         Class.forName("com.mysql.jdbc.Driver").newInstance();
      }
      catch (Exception e) {
         System.out.println("Could not load driver.");
      }
   }

   public void connectToDB() {
      try {
         connection = DriverManager.getConnection(
            "jdbc:mysql://localhost/library", "mike", "password");
         System.out.println("Connected to database.");
      }
      catch (Exception e) {
         System.out.println("Cannot connect to database.");
      }
   }

   public void execSQL() {
      try {
         Statement stmt = connection.createStatement();
         //ResultSet rs = stmt.executeQuery("Select * from books");
         String update = "Update books set title = 'Java: How to Program 3ed'";
         update += " where isbn = '9780132222204'";
         int success = stmt.executeUpdate(update); // returns 1 for success, 0 for failure
         
      }
      catch (Exception e) {
         System.out.println("Error executing SQL");
      }
   }

   public static void main(String[] args) {
      UpdateDB conn = new UpdateDB();
      conn.connectToDB();
      conn.execSQL();
   }
}
   