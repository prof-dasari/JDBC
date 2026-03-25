import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;

public class InsertRecord extends JFrame {
   private JButton getBookButton, insertBookButton;
   private JList bookList;
   private Connection connection;
   private JTextField isbn, title, author, price, publisher;
   private JTextArea errorText;

   public InsertRecord() {
      try {
         Class.forName("com.mysql.jdbc.Driver").newInstance();
      } 
      catch (Exception e) {
         System.err.println("Unable to load driver.");
         System.exit(1);
      }
   }

   public void loadBooks() {
      Vector<String> v = new Vector<String>();
      try {
         Statement statement = connection.createStatement();
         ResultSet rs = statement.executeQuery("select title from books");
         while (rs.next()) {
            v.addElement(rs.getString("title"));
         }
         rs.close();
      }
      catch (SQLException e) {
         System.err.println("Error executing SQL");
      }
      bookList.setListData(v);
   }

   private void createGUI() {
      Container c = getContentPane();
      c.setLayout(new FlowLayout());
      bookList = new JList();
      loadBooks();
      bookList.setVisibleRowCount(2);
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

      insertBookButton = new JButton("Insert Book");
      insertBookButton.addActionListener (
         new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               try {
                  Statement statement = connection.createStatement();
                  String insert = "insert into books values(";
                     insert += "'" + isbn.getText() + "',";
                     insert += "'" + author.getText() + "',";
                     insert += "'" + title.getText() + "',";
                     insert += "'" + price.getText() + "',";
                     insert += "'" + publisher.getText() + "')";
                  System.out.println(insert);
                  /*int i = statement.executeUpdate("insert into books values(" + 
                     "'" + isbn.getText() + "'," + 
                     "'" + title.getText() + "'," + 
                     "'" + author.getText() + "'," + 
                     "'" + price.getText() + "'," + 
                     "'" + publisher.getText() + ")");*/
                  int i = statement.executeUpdate(insert);
                  errorText.append("Inserted " + i + " rows succcessfully.");
                  bookList.removeAll();
                  loadBooks();
                }
                catch (SQLException ex) {
                   System.err.println("Error executing SQL");
                }
             }
          }
      );

      JPanel first = new JPanel(new GridLayout(3,1));
      first.add(bookListScrollPane);
      first.add(getBookButton);
      first.add(insertBookButton);

      isbn = new JTextField(13);
      title = new JTextField(50);
      author = new JTextField(50);
      price = new JTextField(10);
      publisher = new JTextField(50);
      errorText = new JTextArea(5,15);
      errorText.setEditable(false);

      JPanel second = new JPanel();
      second.setLayout(new GridLayout(6,1));
      second.add(isbn);
      second.add(title);
      second.add(author);
      second.add(price);
      second.add(publisher);
      
      JPanel third = new JPanel();
      third.add(new JScrollPane(errorText));

      c.add(first);
      c.add(second);
      c.add(third);
      setSize(800, 400);
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
      InsertRecord insert = new InsertRecord();

      insert.addWindowListener(
         new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
               System.exit(0);
            }
         }
      );
   
      insert.init();
      insert.createGUI();
   }
}
      
                     