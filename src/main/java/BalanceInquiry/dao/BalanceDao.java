package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.ConnectionManager;

public class BalanceDao{


	public BigDecimal getBalance(String accountNumber) throws SQLException{

		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("SELECT balance FROM accounts WHERE account_number = ?");
		pstmt.setString(1, accountNumber);
		ResultSet result = pstmt.executeQuery();

		while(result.next()){
			BigDecimal balance = result.getBigDecimal("balance");
			return balance;
		}
		return null;
	}

	public boolean isValidAccount(String accountNumber) throws SQLException{
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("SELECT * accounts WHERE account_number = ?");
		pstmt.setString(1, accountNumber);
		ResultSet result = pstmt.executeQuery();

		while(result.next()){
			return true;
		}
		return false;
	}
}