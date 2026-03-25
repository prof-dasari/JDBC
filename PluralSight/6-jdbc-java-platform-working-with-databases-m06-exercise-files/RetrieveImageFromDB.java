import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RetrieveImageFromDB {

	public static void main(String[] args) throws SQLException, IOException {
		Connection conn = DBUtil.getConnection(DBType.ORADB);
		
		String sql = "Select Photo From NewEmployees Where Employee_Id = 500";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		
		if( rs.next()){
			Blob imgBlob = rs.getBlob("Photo");
			
			FileOutputStream fos  = new FileOutputStream("D:/PluralSight Demos/Downloads/img500.jpg");
			
			fos.write(imgBlob.getBytes(1, (int)imgBlob.length()));
			
			fos.flush();
			fos.close();
			
			System.out.println("Photo of Employee 500 has been Downloaded successfully");
		}
		else{
			System.out.println("Employee Record Not Found.");
		}
		
		rs.close();
		pstmt.close();
		conn.close();
	}
}
