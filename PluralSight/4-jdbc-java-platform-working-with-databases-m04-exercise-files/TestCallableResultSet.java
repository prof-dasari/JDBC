import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import oracle.jdbc.internal.OracleCallableStatement;

import oracle.jdbc.OracleTypes;

public class TestCallableResultSet {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		try(
				Connection conn = DBUtil.getConnection(DBType.ORADB);
				CallableStatement callableStatement = conn.prepareCall("{call GetEmployeesByRefCursor(?,?)}");
				Scanner scanner = new Scanner(System.in);
				){
			System.out.print("Enter Department ID : ");
			int deptno = Integer.parseInt(scanner.nextLine());
			
			callableStatement.setInt(1, deptno);
			callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
			callableStatement.execute();
			
			
			ResultSet rs = ((oracle.jdbc.internal.OracleCallableStatement)callableStatement).getCursor(2);
			
			String format = "%-4s%-50s%-25s%-10f\n";
			
			while(rs.next()){
				System.out.format(format,rs.getString("Employee_ID"),rs.getString("Employee_Name"),rs.getString("Email"),rs.getFloat("Salary"));
			}
			
		}
		catch(SQLException ex){
			DBUtil.showErrorMessage(ex);
		}

	}

}
