package transaction.participant;

import java.io.Serializable;
import java.sql.SQLException;

import org.jpos.transaction.TransactionParticipant;

import dao.ConnectionManager;

public class OpenDBConnection implements TransactionParticipant {

	@Override
	public void abort(long id, Serializable context) {
		System.out.println("\n*****\n Abort OpenDBConnection\n*****\n");
		try {
			ConnectionManager.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void commit(long id, Serializable context) {
		System.out.println("\n*****\n Commit OpenDBConnection\n*****\n");
		
	}

	@Override
	public int prepare(long id, Serializable context) {
		System.out.println("\n*****\n Prepare OpenDBConnection\n*****\n");
		ConnectionManager.initializeConnection();
		return PREPARED;
	}

}
