import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class IteratingWithResultSetsUsingTryWithResource {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
		try(
				Connection conn = DBUtil.getConnection(DBType.ORADB);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("Select * from Employees");
				) {
			
			
			String format = "%-4s%-20s%-25s%-10f\n"; 
			while (rs.next()) {
				System.out.format(format,rs.getString("Employee_ID"),rs.getString("First_Name"),rs.getString("Last_Name"),rs.getFloat("Salary"));
			}
						
			
		} catch (SQLException e) {
			// TODO: handle exception
			DBUtil.showErrorMessage(e);
		}
		
		
	}

}
