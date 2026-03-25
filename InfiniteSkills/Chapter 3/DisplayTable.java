import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;

public class DisplayTable extends JFrame {
   private JButton getBookButton;
   private JList bookList;
   private Connection connection;
   private JTextField isbn, author, title, price, publisher;

   public DisplayTable() {
      try {
         Class.forName("com.mysql.jdbc.Driver").newInstance();
      }
      catch (Exception e) {
         System.err.println("Problem with driver");
         System.exit(1);
      }
   }

   private void createGUI() {
      Container c = getContentPane();
      c.setLayout(new FlowLayout());
      Statement statement = null;
      Vector<String> vec = new Vector<String>();
      try {
         statement = connection.createStatement();
         ResultSet rs = statement.executeQuery("Select title from books");
         while (rs.next()) {
            vec.addElement(rs.getString("title"));
         }
         rs.close();
      }
      catch (SQLException e) { }
      bookList = new JList(vec);
      bookList.setVisibleRowCount(3);
      JScrollPane bookListScrollPane = new JScrollPane(bookList);

      getBookButton = new JButton("Get Book Title");
      getBookButton.addActionListener(
         new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               String query = "select * from books where title = " + 
                      bookList.getSelectedValue();
               try {
                  Statement statement = connection.createStatement();
                  
                  ResultSet rs = statement.executeQuery(
                     "select * from books where title = '"
                     + bookList.getSelectedValue() + "'");
		  /*ResultSet rs = statement.executeQuery(
                    "select * from books where title = 'Java:How To Program'");	*/
                  if (rs.next()) {
                     isbn.setText(rs.getString("isbn"));
                     title.setText(rs.getString("title"));
                     author.setText(rs.getString("author"));
                     price.setText(rs.getString("price"));
                     publisher.setText(rs.getString("publisher"));
                  }
                } 
                catch (SQLException ex) { isbn.setText(query); }
            }
         }
      );

      JPanel first = new JPanel();
      first.add(bookListScrollPane);
      first.add(getBookButton);
      isbn = new JTextField(15);
      title = new JTextField(50);
      author = new JTextField(50);
      price = new JTextField(8);
      publisher = new JTextField(50);
      JPanel second = new JPanel();
      second.setLayout(new GridLayout(5,1));
      second.add(isbn);
      second.add(title);
      second.add(author);
      second.add(price);
      second.add(publisher);
      c.add(first);
      c.add(second);
      setSize(800,400);
      setVisible(true);
   }

   public void connectToDB() throws Exception {
     //Connection conn = null;
      try {
         String userName = "root";
         String password = "meredith1";
         String url = "jdbc:mysql://localhost/library";
         Class.forName("com.mysql.jdbc.Driver").newInstance();
         connection = DriverManager.getConnection(url, userName, password);
         //if (conn != null) System.out.println("Database connection successful.");
      }
      catch (SQLException e) {
         System.out.println("Can't connect to database");
         System.exit(1);
      }
   }

   private void init() throws Exception{
      connectToDB();
   }

   public static void main(String[] args) throws Exception {
      DisplayTable displayBooks = new DisplayTable();

      displayBooks.addWindowListener(
         new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
               System.exit(0);
            }
         }
      );
   
      displayBooks.init();
      displayBooks.createGUI();
   }
}
      
      