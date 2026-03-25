import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestManageDBResources {

	
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		try {
			//conn = DriverManager.getConnection(dbUrl, username, password);
			conn = DBUtil.getConnection(DBType.MYSQLDB);
			
			System.out.println("Connection Established to MYSQL Database");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}
		finally{
			conn.close();
		}
		
		
	

	}

}
