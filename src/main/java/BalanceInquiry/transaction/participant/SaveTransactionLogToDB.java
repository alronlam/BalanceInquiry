package transaction.participant;

import java.io.Serializable;

import org.jpos.transaction.TransactionParticipant;

public class SaveTransactionLogToDB implements TransactionParticipant {

	@Override
	public void abort(long id, Serializable context) {
		System.out.println("\n*****\n Abort\n*****\n");
	}

	@Override
	public void commit(long id, Serializable context) {
		System.out.println("\n*****\n Commit\n*****\n");
	}

	@Override
	public int prepare(long id, Serializable context) {
		System.out.println("\n*****\n Prepare\n*****\n");

		return PREPARED;
	}

}
