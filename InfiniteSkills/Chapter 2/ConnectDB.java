import java.sql.*;

public class ConnectDB {

   Connection connection;

   public ConnectDB() {
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
         ResultSet rs = stmt.executeQuery("Select * from books");
         while (rs.next()) {
            System.out.println(rs.getString(1) + " " + rs.getString(2) +
                               " " + rs.getString(3));
         }
      }
      catch (Exception e) {
         System.out.println("Error executing SQL");
      }
   }

   public static void main(String[] args) {
      ConnectDB conn = new ConnectDB();
      conn.connectToDB();
      conn.execSQL();
   }
}
   