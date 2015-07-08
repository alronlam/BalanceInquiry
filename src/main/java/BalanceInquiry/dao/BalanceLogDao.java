package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import object.BalanceLog;
import dao.ConnectionManager;


public class BalanceLogDao{

	public void writeBalanceLog(BalanceLog log) throws SQLException{
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("INSERT INTO logs (account_number, transaction_time, response) VALUES (?, ?, ?)");
		pstmt.setString(1, log.getAccountNumber());
		pstmt.setTimestamp(2, log.getTransactionTime());
		pstmt.setString(3, log.getResponse());
		pstmt.executeUpdate();
		
	}
}