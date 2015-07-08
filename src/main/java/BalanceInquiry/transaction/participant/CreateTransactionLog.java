package transaction.participant;

import java.io.Serializable;
import java.sql.SQLException;
import java.sql.Timestamp;

import object.BalanceLog;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.transaction.Context;
import org.jpos.transaction.TransactionParticipant;

import constant.Constant;
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
		((Context) context).put(Constant.LOG, log);
	}

	@Override
	public int prepare(long id, Serializable context) {
		System.out.println("\n*****\n Prepare Create Transaction Log\n*****\n");

		ISOMsg msg = (ISOMsg) ((Context) context).get(Constant.REQUEST);
		java.util.Date date = new java.util.Date();
		Timestamp current = new Timestamp(date.getTime());

		String accountNumber;
		try {
			accountNumber = (String) msg.getValue(2);
			// approved but if something goes wrong it will be set to denied in
			// the abort() function
			log = new BalanceLog(accountNumber, current, Constant.APPROVED);

			return PREPARED;
		} catch (ISOException e) {
			e.printStackTrace();
			return ABORTED;
		}

	}

}
