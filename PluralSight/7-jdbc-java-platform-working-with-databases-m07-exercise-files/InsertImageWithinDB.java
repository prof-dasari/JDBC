import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertImageWithinDB {

	public static void main(String[] args) throws SQLException, IOException{
		// TODO Auto-generated method stub
		
		Connection conn = DBUtil.getConnection(DBType.ORADB);
		
		String sql = "Update NewEmployees Set Photo = ? where Employee_ID = 500";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		File file = new File("D:/PluralSight Demos/Sekhar.jpg");
		
		FileInputStream fis = new FileInputStream(file);
		
		pstmt.setBinaryStream(1, fis, fis.available());
		
		int count  = pstmt.executeUpdate();
		
		System.out.println("Total Records Updated : " + count);
		pstmt.close();
		conn.close();

	}

}
