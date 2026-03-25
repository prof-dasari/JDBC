import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class TestPreparedStatementDelete {

	public static void main(String[] args) throws SQLException {
		
		Connection conn = DBUtil.getConnection(DBType.ORADB);
		
		String sql = "Delete from NewEmployees where Employee_Id = ?";
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter Employee ID :");
		int empno = scanner.nextInt();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, empno);
		
		int result = pstmt.executeUpdate();
		
		if( result == 1){
			System.out.println("Employee Record Removed Successfully.");
		}
		else{
			System.err.println("Error While Removing Employee Record.");
		}
		
		scanner.close();
		pstmt.close();
		conn.close();
	}

}
