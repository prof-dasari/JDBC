import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class RetrieveCLOBDataFromDB {

	public static void main(String[] args) throws SQLException, IOException{
		Connection conn = DBUtil.getConnection(DBType.ORADB);
		
		String sql = "select Resume from NewEmployees where Employee_ID = 500";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()){
			Clob resume = rs.getClob("Resume");
			Reader data = resume.getCharacterStream();
			
			int i;
			String resumeDetails = "";
			while( (i =data.read()) != -1 ){
				resumeDetails += ((char) i);
			}
			System.out.println("Resume Details for Employee 500");
			System.out.println(resumeDetails);
		}
		else{
			System.err.println("No Record Found For Employee With The ID 500.");
		}
		
		rs.close();
		pstmt.close();
		conn.close();
	}
}
