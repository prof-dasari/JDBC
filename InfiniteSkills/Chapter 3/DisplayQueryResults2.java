import java.awt.*;
import sql.*;
import java.util.regex.*;
import javax.swing.*;

public class DisplayQueryResults extends JFrame {
   static final String DRIVER = "com.mysql.jdbc.Driver";
   static final String DATABASE_URL = "jdbc:mysql://localhost/library";
   static final String USERNAME = "mike";
   static final String PASSWORD = "password";

   static final String DEFAULT_QUERY = "Select * from books";

   private ResultSetTableModel tableModel;
   private JTextArea queryArea;

   public DisplayQueryResults() {
      super("Displaying Query Results");

      try {
         tableModel = new ResultSetTableModel(DRIVER, DATABASE_URL, USERNAME,
                            PASSWORD, DEFAULT_QUERY);
         queryArea = new JTextArea(DEFAULT_QUERY, 3, 100);
         queryArea.setWrapStyleWord(true);
         queryArea.setLineWrap(true);

         JScrollPan scrollPane = new JScrollPane(queryArea, 
                                       ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                                       ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
         JButton submitButton = new JButton("Submit Query");
         Box boxNorth = Box.createHorizontalBox();
         boxNorth.add(scrollpane);
         boxNorth.add(submitButton);
         JTable resultTable = new JTable(tableModel);
         JLabel filterLabel = new JLabel("Filter:");
         final JTextField filterText = new JTextField();
         JButton filterButton = new JButton("Apply Filter:");
         Box boxSouth = boxNorth.createHorizontalBox();
         boxSouth.add(filterLabel);
         boxSouth.add(filterText);
         boxSouth.add(filterbutton);
         add(boxNorth, BorderLayout.NORTH);
         add(new JScrollPan(resultTable), BorderLayout.CENTER);
         add(boxSouth, BorderLayout.SOUTH);
   
         submitButton.addActionListener(
            new ActionListener() {
               public void actionPerformed(ActionEvent event) {
                  try {
                     tableModel.setQuery(queryArea.getText());
                  }
                  catch (SQLException ex) {
       	             JOptionPane.showMessageDialog(null, 
                        ex.getMessage(), "Database error", JOptionPane.ERROR_MESSAGE);
                     try {
                        tableModel.setQuery(DEFAULT_QUERY);
                        queryArea.setText(DEFAULT_QUERY);
                     }
                     catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null,
                            ex.getMessage(), "Database error", JOptionPane.ERROR_MESSAGE);
                        tableModel.disconnectFromDatabase();
                        System.exit(1);
                     }
                }
             }
          }