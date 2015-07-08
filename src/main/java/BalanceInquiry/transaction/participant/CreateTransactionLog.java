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

	@Override
	public void abort(long id, Serializable context) {
		System.out.println("\n*****\n Abort Create Transaction Log\n*****\n");
	}

	@Override
	public void commit(long id, Serializable context) {
		System.out.println("\n*****\n Commit Create Transaction Log\n*****\n");
		
		((Context)context).put(Constant.LOG, log);
	}

	@Override
	public int prepare(long id, Serializable context) {
		System.out.println("\n*****\n Prepare Create Transaction Log\n*****\n");

		ISOMsg msg = (ISOMsg) ((Context) context).get(Constant.REQUEST);
		String status = (String) ((Context) context).get(Constant.STATUS);
		java.util.Date date = new java.util.Date();
		Timestamp current = new Timestamp(date.getTime());
		
		String accountNumber;
		try {
			accountNumber = (String) msg.getValue(2);
			log = new BalanceLog(accountNumber, current, status);
			
			return PREPARED;
		} catch (ISOException e) {
			e.printStackTrace();
			return ABORTED;
		}
		
	}

}
