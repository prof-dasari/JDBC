import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class TestPreparedInsert {

	public static void main(String[] args) throws SQLException  {
		// TODO Auto-generated method stub

		 Connection conn = DBUtil.getConnection(DBType.ORADB);
		 
		 int empno;
		 String ename,email;
		 java.sql.Date hiredate;
		 double salary;
		 
		 Scanner scanner = new Scanner(System.in);
		 
		 System.out.print("Enter Employee ID :");
		 empno = Integer.parseInt(scanner.nextLine());
		 
		 System.out.print("Enter Employee Name :");
		 ename = scanner.nextLine();
		 
		 System.out.print("Ente Email : ");
		 email = scanner.nextLine();
		 
		 System.out.print("Enter Date of Joining : ");
		 hiredate = java.sql.Date.valueOf(scanner.nextLine());
		 
		 System.out.print("Enter Salary : ");
		 salary = scanner.nextDouble();
		 
		 String sql = "insert into NewEmployees values ( ?,?,?,?,? )";
		 
		 PreparedStatement pstmt  = conn.prepareStatement(sql);
		 
		 pstmt.setInt(1, empno);
		 pstmt.setString(2, ename);
		 pstmt.setString(3, email);
		 pstmt.setDate(4, hiredate);
		 pstmt.setDouble(5, salary);
		 
		 int result = pstmt.executeUpdate();
		 
		 if( result == 1 )
		 {
			 System.out.println("Record Inserted Successfully.");
		 }
		 else{
			 System.err.println("Error while adding the record.");
		 }
		 
		 scanner.close();
		 pstmt.close();
		 conn.close();
	}

}
