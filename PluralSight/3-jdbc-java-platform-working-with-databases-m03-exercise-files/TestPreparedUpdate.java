import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.PreparedStatement;


public class TestPreparedUpdate {

	public static void main(String[] args) throws SQLException  {
				
		Connection conn = DBUtil.getConnection(DBType.ORADB);
		
		String sql = "Update NewEmployees set Salary = ? where Employee_Id = ?";
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter Employee ID :");
		int empno = scanner.nextInt();
		
		System.out.print("Enter New Salary : ");
		double salary = scanner.nextDouble();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setDouble(1, salary);
		pstmt.setInt(2, empno);
		
		int result = pstmt.executeUpdate();
		
		if( result == 1 )
		{
			System.out.println("Employee Salary Updated Successfully.");
		}
		else{
			System.err.println("Error while updating the Salary.");
		}
		
		scanner.close();
		pstmt.close();
		conn.close();
		
	}

}
