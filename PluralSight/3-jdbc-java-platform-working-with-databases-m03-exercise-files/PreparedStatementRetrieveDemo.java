import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreparedStatementRetrieveDemo {

	public static void main(String[] args) throws SQLException {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			conn = DBUtil.getConnection(DBType.ORADB);
			
			String sql = "Select * From Employees where Salary < ? and Department_Id = ?";
			
			pstmt = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			prepareStatement(pstmt,10000,50);
			System.out.println("-------------------");
			prepareStatement(pstmt,3000,50);
			
		}
		catch(SQLException ex){
			DBUtil.showErrorMessage(ex);
		}
	}

	private static void prepareStatement(PreparedStatement pstmt,double salary,int department_id) throws SQLException {
		ResultSet rs;
		pstmt.setDouble(1, salary);
		pstmt.setInt(2, department_id);
		
		rs = pstmt.executeQuery();
		
		String format = "%-4s%-20s%-25s%-10f\n"; 
		while(rs.next()){
			System.out.format(format, rs.getString("Employee_Id"), rs.getString("First_Name"),rs.getString("Last_Name"),rs.getFloat("Salary"));
		}
		
		rs.last();
		System.out.println("Total Employees : " + rs.getRow());
	}
}
