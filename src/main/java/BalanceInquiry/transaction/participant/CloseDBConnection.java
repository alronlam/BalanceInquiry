package transaction.participant;

import java.io.Serializable;
import java.sql.SQLException;

import org.jpos.transaction.TransactionParticipant;

import dao.ConnectionManager;

public class CloseDBConnection implements TransactionParticipant {

	@Override
	public void abort(long id, Serializable context) {
		System.out.println("\n*****\n Abort\n*****\n");
	}

	@Override
	public void commit(long id, Serializable context) {
		System.out.println("\n*****\n Commit\n*****\n");

		try {
			ConnectionManager.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int prepare(long id, Serializable context) {
		System.out.println("\n*****\n Prepare\n*****\n");

		return PREPARED;
	}

}
