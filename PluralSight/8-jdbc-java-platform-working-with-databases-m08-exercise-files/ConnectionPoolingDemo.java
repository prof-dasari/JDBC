import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.PooledConnection;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;

public class ConnectionPoolingDemo {

	public static void main(String[] args) throws SQLException {
		
		OracleConnectionPoolDataSource ds = new OracleConnectionPoolDataSource();
		
		ds.setDriverType("thin");
		ds.setServerName("localhost");
		ds.setPortNumber(1521);
		ds.setServiceName("xe");
		ds.setUser("hr");
		ds.setPassword("hr");
		
		PooledConnection pconn = ds.getPooledConnection();
		
		Connection conn = pconn.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("Select * From Departments");
		
		String format = "%-30s%-50s%-25s\n";
		System.out.format(format,"Department #","Department Name","Location");
		System.out.format(format,"-------------","-----------------","-------------");
		
		while(rs.next()){
			System.out.format(format,rs.getString("Department_ID"),rs.getString("Department_Name"), rs.getString("Location_Id"));
		}
		
		rs.close();
		stmt.close();
		conn.close();
		pconn.close();
	}
}
