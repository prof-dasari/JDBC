import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.CallableStatement;

public class TestCallableIn {

	public static void main(String[] args) {
		try(
				Connection conn = DBUtil.getConnection(DBType.ORADB);
				
				CallableStatement callableStatement = conn.prepareCall("{call AddNewEmployee(?,?,?,?,?)}");
				
				)
		{
			Scanner scanner = new Scanner(System.in);
			System.out.print("Enter Employee # : ");
			int empno = Integer.parseInt(scanner.nextLine());
			System.out.print("Enter Employee Name : ");
			String ename = scanner.nextLine();
			System.out.print("Enter Email ID :");
			String email = scanner.nextLine();
			System.out.print("Enter Hiredate : ");
			Date doj = java.sql.Date.valueOf(scanner.nextLine());
			System.out.print("Enter Salary :");
			double salary = scanner.nextDouble();

			callableStatement.setInt(1, empno);
			callableStatement.setString(2, ename);
			callableStatement.setString(3, email);
			callableStatement.setDate(4, doj);
			callableStatement.setDouble(5, salary);
			
			callableStatement.execute();
			
			System.out.println("Employee Record Added Successfully.");
			
		}
		catch(SQLException ex){
			DBUtil.showErrorMessage(ex);
		}
		
		
	}

}
