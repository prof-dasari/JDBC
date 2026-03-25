import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
public class DatabaseMetadataDemo {
	public static void main(String[] args) {
		try(Connection conn = DBUtil.getConnection(DBType.ORADB);){

			DatabaseMetaData dbmd = conn.getMetaData();
			
			//Retrieving Database Information
			System.out.println("Driver Name : " + dbmd.getDriverName());
			System.out.println("Driver Version : " + dbmd.getDriverVersion());
			System.out.println("Logged In User : " + dbmd.getUserName());
			System.out.println("Database Product Name : " + dbmd.getDatabaseProductName());
			System.out.println("Database Product Version : " + dbmd.getDatabaseProductVersion());
			
			//Retrieving all the tables names from connected database
			String catalog = null;
			String schemaPattern = null;
			String tableNamePattern = null;
			String schemaTypes[] = { "TABLE" };
			ResultSet rs = dbmd.getTables(catalog,schemaPattern,tableNamePattern,schemaTypes);
			System.out.println("Tables");
			System.out.println("============");
			while( rs.next() )
			{
			 System.out.println(  rs.getString("TABLE_NAME") );
			}
			
			//Retrieving Columns Present within the Table
			String columnNamePattern = null;
			rs = dbmd.getColumns(catalog, schemaPattern, "COUNTRIES", columnNamePattern);
			System.out.println("Column Details");
			System.out.println("=======================");
			while( rs.next() )
			{
			 System.out.println(  rs.getString("COLUMN_NAME") );
			}
			
			rs.close();
			
		}
		catch(SQLException ex){
			DBUtil.showErrorMessage(ex);
		}
	}
}
