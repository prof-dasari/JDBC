import java.sql.*;
import java.io.*;

public class InsertDB {

   Connection connection;

   public InsertDB() {
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
         System.out.print("Enter the isbn: ");
         String isbn = input.readLine();
         System.out.print("Enter the title: ");
         String title = input.readLine();
         System.out.print("Enter the author: ");
         String author = input.readLine();
         System.out.print("Enter the price: ");
         String p = input.readLine();
         double price = Double.parseDouble(p);
         System.out.print("Enter the publisher: ");
         String pub = input.readLine();
         String insert = "Insert into books values (" + "'" + isbn + "','" + title +
                         "','" + author + "'," + price + ",'" + pub + "')";
         System.out.println(insert);
         int inserted = stmt.executeUpdate(insert); // returns 1 for success, 0 for failure
         if (inserted > 0) {
            System.out.println("Successfully inserted " + inserted + " row.");
         }
      }
      catch (Exception e) {
         System.out.println("Error executing SQL");
      }
   }

   public static void main(String[] args) {
      InsertDB conn = new InsertDB();
      conn.connectToDB();
      conn.execSQL();
   }
}
   