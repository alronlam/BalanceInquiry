package transaction.participant;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOSource;
import org.jpos.transaction.Context;
import org.jpos.transaction.TransactionParticipant;

import constant.Constant;
import dao.BalanceDao;

public class RetrieveBalanceFromDB implements TransactionParticipant {

	BigDecimal balance;

	BalanceDao balanceDao;
	
	@Override
	public void abort(long id, Serializable context) {
		System.out.println("\n*****\n Abort Retrieve Balance\n*****\n");
	}

	@Override
	public void commit(long id, Serializable context) {
		System.out.println("\n*****\n Commit Retrieve Balance\n*****\n");

		((Context)context).put(Constant.BALANCE, balance);
	}

	@Override
	public int prepare(long id, Serializable context) {
		System.out.println("\n*****\n Prepare Retrieve Balance\n*****\n");

		balanceDao = new BalanceDao();

		ISOMsg msg = (ISOMsg) ((Context)context).get(Constant.REQUEST);
		
		try {
			String accountNumber = (String) msg.getValue(2);
			//replace 1000 with account number
			balance = balanceDao.getBalance(accountNumber);
			
		} catch (SQLException | ISOException e) {
			e.printStackTrace();
		}
		
		return PREPARED;
	}

}
