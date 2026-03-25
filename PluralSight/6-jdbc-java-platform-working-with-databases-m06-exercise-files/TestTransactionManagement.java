import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class TestTransactionManagement {

	public static void main(String[] args) throws SQLException{
		// TODO Auto-generated method stub
		try{
		Connection conn = DBUtil.getConnection(DBType.ORADB);
		conn.setAutoCommit(false);

		PreparedStatement pstmt = null;
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("PSBank Transactions");
		System.out.println("----------------------");
		System.out.print("Enter From Account # :");
		int fromAcno = Integer.parseInt(scanner.nextLine());
		System.out.print("Enter To Account # : ");
		int toAcno = Integer.parseInt(scanner.nextLine());
		System.out.print("Enter Amount To Transfer : ");
		double amount = Double.parseDouble(scanner.nextLine());
		
		String withdrawSQL = "Update PSBank set Amount = Amount - ? where Acno = ?";
		pstmt = conn.prepareStatement(withdrawSQL);
		pstmt.setDouble(1, amount);
		pstmt.setInt(2, fromAcno);
		pstmt.executeUpdate();
				
		String depositSQL = "Update PSBank set Amount = Amount + ? where Acno = ?";
		pstmt = conn.prepareStatement(depositSQL);
		pstmt.setDouble(1, amount);
		pstmt.setInt(2, toAcno);
		pstmt.executeUpdate();
		
		String sql = "Select Amount From PSBank where Acno = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, fromAcno);
		ResultSet rs = pstmt.executeQuery();
		double balanceAmount=0;
		if( rs.next()){
			balanceAmount = rs.getDouble("Amount");
		}
		
		if( balanceAmount >= 5000){
			conn.commit();
			System.out.println("Amount Transferred Successfully...");
		}
		else{
			conn.rollback();
			System.out.println("Insufficient Funds : " + balanceAmount + " Transactions Rollbacked..");
		}
		
		scanner.close();
		pstmt.close();
		conn.close();
		}
		catch(Exception ex){
			System.err.println(ex.getMessage());
		}
	}

}
