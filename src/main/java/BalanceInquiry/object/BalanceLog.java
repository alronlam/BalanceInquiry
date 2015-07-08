package object;

import java.sql.Timestamp;

public class BalanceLog {

	int id;
	String accountNumber;
	Timestamp transactionTime;
	String response;
	
	public BalanceLog(String accountNumber, Timestamp transactionTime,
			String response) {
		super();
		this.accountNumber = accountNumber;
		this.transactionTime = transactionTime;
		this.response = response;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public Timestamp getTransactionTime() {
		return transactionTime;
	}
	public void setTransactionTime(Timestamp transactionTime) {
		this.transactionTime = transactionTime;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	
	
}