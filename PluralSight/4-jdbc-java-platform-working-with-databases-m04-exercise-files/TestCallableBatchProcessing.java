import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.CallableStatement;

public class TestCallableBatchProcessing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try(
				Connection conn = DBUtil.getConnection(DBType.ORADB);
				CallableStatement callableStatement = conn.prepareCall("call AddNewEmployee(?,?,?,?,?)");
				){
			String option;
			do{
			Scanner scanner = new Scanner(System.in);
			System.out.print("Enter Employee # : ");
			int empno = Integer.parseInt(scanner.nextLine());
			System.out.print("Enter Employee Name : ");
			String ename = scanner.nextLine();
			System.out.print("Enter Email ID :");
			String email = scanner.nextLine();
			System.out.print("Enter Hiredate : ");
			Date dob = java.sql.Date.valueOf(scanner.nextLine());
			System.out.print("Enter Salary :");
			double salary = Double.parseDouble(scanner.nextLine());
			
			callableStatement.setInt(1, empno);
			callableStatement.setString(2, ename);
			callableStatement.setString(3, email);
			callableStatement.setDate(4, dob);
			callableStatement.setDouble(5, salary);
			
			callableStatement.addBatch();
			
			System.out.print("Do You Want To Add Another Record (yes /no): ");
			option = scanner.nextLine();
			}while( option.equals("yes"));
			
			int[] updateCounts = callableStatement.executeBatch();
			
			System.out.println("Total Records Inserted are : " + updateCounts.length  );
			
			
		}catch(SQLException ex){
		DBUtil.showErrorMessage(ex);
		}

	}

}
