package transaction.participant;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;

import org.jpos.iso.ISOMsg;
import org.jpos.transaction.Context;
import org.jpos.transaction.TransactionParticipant;

import constant.Constant;
import dao.BalanceDao;

public class DepositAmount implements TransactionParticipant {

	@Override
	public void abort(long arg0, Serializable arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void commit(long arg0, Serializable context) {

		ISOMsg requestMsg = (ISOMsg) ((Context) context).get(Constant.REQUEST);
		Double amount = Double.parseDouble(requestMsg.getString(4));
		String accountNumber = requestMsg.getString(2);
		BalanceDao balanceDao = new BalanceDao();
		try {
			balanceDao.deposit(accountNumber, BigDecimal.valueOf(amount));
			((Context) context).put(Constant.STATUS, Constant.APPROVED);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			((Context) context).put(Constant.STATUS, Constant.DENIED);
		}
	}

	@Override
	public int prepare(long arg0, Serializable arg1) {
		return PREPARED;
	}

}
