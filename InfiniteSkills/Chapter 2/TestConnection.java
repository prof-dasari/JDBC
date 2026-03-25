public class TestConnection {
   public static void main(String[] args) {
      try {
         Class.forName("com.mysql.jdbc.Driver").newInstance();
         System.out.println("Connected to MySQL");
      }
      catch (Exception e) {
         System.out.println("Cannot connect to MySQL");
      }
   }
}
