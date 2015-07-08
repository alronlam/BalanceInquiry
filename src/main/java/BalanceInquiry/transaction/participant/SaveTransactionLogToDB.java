package transaction.participant;

import java.io.Serializable;
import java.io.IOException;
import java.sql.SQLException;

import org.jpos.transaction.TransactionParticipant;
import org.jpos.transaction.Context;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOException;

import constant.Constant;
import object.BalanceLog;
import dao.BalanceLogDao;

public class SaveTransactionLogToDB implements TransactionParticipant {

	BalanceLog log;
	BalanceLogDao balanceLogDao;

	@Override
	public void abort(long id, Serializable context) {
		System.out.println("\n*****\n Abort Save Transaction Log\n*****\n");
	}

	@Override
	public void commit(long id, Serializable context) {
		System.out.println("\n*****\n Commit Save Transaction Log\n*****\n");

		BalanceLog balanceLog = (BalanceLog) ((Context)context).get(Constant.LOG);
		try {
			balanceLogDao.writeBalanceLog(balanceLog);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int prepare(long id, Serializable context) {
		System.out.println("\n*****\n Prepare Save Transaction Log\n*****\n");

		balanceLogDao = new BalanceLogDao();
		
		return PREPARED;
	}

}
