import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class TestCallableOut {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try(
				Connection conn = DBUtil.getConnection(DBType.ORADB);
				CallableStatement callableStatement = conn.prepareCall("{ call GetTotalEmployeesByDepartment(?,?) }");
				Scanner scanner = new Scanner(System.in);
				)
		{
			System.out.print("Enter Department ID : ");
			int deptno = Integer.parseInt(scanner.nextLine());
			
			callableStatement.setInt(1, deptno);
			
			callableStatement.registerOutParameter(2, Types.INTEGER);
			
			callableStatement.execute();
			
			int totalEmployees  = callableStatement.getInt(2);
			
			System.out.println("Total Employees Working : " + totalEmployees);
		}
		catch(SQLException ex){
			DBUtil.showErrorMessage(ex);
		}

	}

}
