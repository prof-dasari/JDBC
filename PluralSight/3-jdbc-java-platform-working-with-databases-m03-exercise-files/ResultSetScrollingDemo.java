import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetScrollingDemo {

	public static void main(String[] args) throws SQLException  {
	
		try(
				Connection conn = DBUtil.getConnection(DBType.ORADB);
				
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				
				ResultSet rs = stmt.executeQuery("Select * From Employees Where Rownum <= 10");
				
				)
		{
			String format = "%-4s%-20s%-25s%-10f\n"; 
			
			rs.beforeFirst();
			
			System.out.println("First 10 Rows : ");
			
			while(rs.next()){
				System.out.format(format, rs.getString("Employee_ID"),rs.getString("First_Name"),rs.getString("Last_Name"),rs.getFloat("Salary"));
			}
			
			rs.afterLast();
			System.out.println("Last 10 Rows : ");
			
			while(rs.previous()){
				System.out.format(format, rs.getString("Employee_ID"),rs.getString("First_Name"),rs.getString("Last_Name"),rs.getFloat("Salary"));
			}
			
			rs.first();
			System.out.println("First Record :");
			System.out.format(format, rs.getString("Employee_ID"),rs.getString("First_Name"),rs.getString("Last_Name"),rs.getFloat("Salary"));
			
			rs.last();
			System.out.println("Last Record :");
			System.out.format(format, rs.getString("Employee_ID"),rs.getString("First_Name"),rs.getString("Last_Name"),rs.getFloat("Salary"));
			
			rs.absolute(4);
			System.out.println("Record at 4th Row :");
			System.out.format(format, rs.getString("Employee_ID"),rs.getString("First_Name"),rs.getString("Last_Name"),rs.getFloat("Salary"));
			
			rs.relative(2);
			System.out.println("Record at 6th Row : ");
			System.out.format(format, rs.getString("Employee_ID"),rs.getString("First_Name"),rs.getString("Last_Name"),rs.getFloat("Salary"));
			
			rs.relative(-4);
			System.out.println("Record at 2nd Row : ");
			System.out.format(format, rs.getString("Employee_ID"),rs.getString("First_Name"),rs.getString("Last_Name"),rs.getFloat("Salary"));
			
		}
		catch(SQLException e){
			DBUtil.showErrorMessage(e);
		}
			
	}

}
