package transaction.participant;

import java.io.Serializable;

import org.jpos.transaction.Context;
import org.jpos.transaction.TransactionParticipant;

public class DepositAmount implements TransactionParticipant {

	@Override
	public void abort(long arg0, Serializable arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void commit(long arg0, Serializable context) {
		Double transactionAmount = Double.parseDouble(((Context) context).getString(4));
		// call the DAO to deposit
	}

	@Override
	public int prepare(long arg0, Serializable arg1) {
		return PREPARED;
	}

}
