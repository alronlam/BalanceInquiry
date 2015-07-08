package transaction.participant;

import java.io.Serializable;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.jpos.transaction.TransactionParticipant;
import org.jpos.transaction.Context;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOException;

import constant.Constant;
import object.BalanceLog;
import dao.BalanceLogDao;

public class CreateTransactionLog implements TransactionParticipant {

	BalanceLog log;
	BalanceLogDao balanceLogDao;

	@Override
	public void abort(long id, Serializable context) {
		System.out.println("\n*****\n Abort Create Transaction Log\n*****\n");
	}

	@Override
	public void commit(long id, Serializable context) {
		System.out.println("\n*****\n Commit Create Transaction Log\n*****\n");

		try {
			balanceLogDao.writeBalanceLog(log);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int prepare(long id, Serializable context) {
		System.out.println("\n*****\n Prepare Create Transaction Log\n*****\n");

		ISOMsg msg = (ISOMsg) ((Context) context).get(Constant.REQUEST);

		java.util.Date date = new java.util.Date();
		Timestamp current = new Timestamp(date.getTime());
		
		//edit to get values from msg
		log = new BalanceLog("1000", current, "APPROVED");
	
		balanceLogDao = new BalanceLogDao();

		return PREPARED;
	}

}
