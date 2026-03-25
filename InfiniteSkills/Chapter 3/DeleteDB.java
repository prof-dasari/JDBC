import java.sql.*;
import java.io.*;

public class DeleteDB {

   Connection connection;

   public DeleteDB() {
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
         BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
         System.out.println("Enter a title: ");
         String title = input.readLine();
         String delete = "delete from books where title = '" + title + "'";
         System.out.println(delete);
         int deleted = stmt.executeUpdate(delete); // returns 1 for success, 0 for failure
         if (deleted > 0) {
            System.out.println("Successfully deleted " + deleted + " row.");
         }
      }
      catch (Exception e) {
         System.out.println("Error executing SQL");
      }
   }

   public static void main(String[] args) {
      DeleteDB conn = new DeleteDB();
      conn.connectToDB();
      conn.execSQL();
   }
}
   