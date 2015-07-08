package transaction.participant;

import java.io.Serializable;
import java.sql.SQLException;

import org.jpos.iso.ISOMsg;
import org.jpos.transaction.Context;
import org.jpos.transaction.TransactionParticipant;

import constant.Constant;
import dao.BalanceDao;

public class ValidateAccount implements TransactionParticipant {

	@Override
	public void abort(long id, Serializable context) {
		System.out.println("\n*****\nValidateAccount Abort\n*****\n");
	}

	@Override
	public void commit(long id, Serializable context) {
		System.out.println("\n*****\nValidateAccount Commit\n*****\n");
	}

	@Override
	public int prepare(long id, Serializable context) {
		System.out.println("\n*****\nValidateAccount Prepare\n*****\n");

		ISOMsg senderMsg = (ISOMsg) ((Context) context).get(Constant.REQUEST);
		String accountNumber = senderMsg.getString(2);

		BalanceDao balanceDao = new BalanceDao();

		try {

			boolean isAccountValid = balanceDao.isValidAccount(accountNumber);
			if (isAccountValid)
				((Context)context).put(Constant.STATUS, Constant.APPROVED);
			else
				((Context)context).put(Constant.STATUS, Constant.DENIED);

		} catch (SQLException e) {
			e.printStackTrace();
			((Context)context).put(Constant.STATUS, Constant.DENIED);
		}

		return PREPARED;
	}

}
