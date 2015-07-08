package transaction.participant;

import java.io.Serializable;

import org.jpos.core.Configurable;
import org.jpos.core.Configuration;
import org.jpos.core.ConfigurationException;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.transaction.Context;
import org.jpos.transaction.TransactionParticipant;

import constant.Constant;

public class ValidateFields implements TransactionParticipant, Configurable {

	private Configuration cfg;

	@Override
	public void abort(long id, Serializable context) {
		if(cfg.getBoolean("debug", true))
			System.out.println("\n*****\nValidateFields Abort\n*****\n");
	}

	@Override
	public void commit(long id, Serializable context) {
		if(cfg.getBoolean("debug", true))
			System.out.println("\n*****\nValidateFields Commit\n*****\n");
	}

	@Override
	public int prepare(long id, Serializable context) {
		if(cfg.getBoolean("debug", true))
			System.out.println("\n*****\nValidateFields Prepare\n*****\n");

		ISOMsg requestMsg = (ISOMsg) ((Context) context).get(Constant.REQUEST);

		int[] requiredFields = this.retrieveAllRequiredFields();
		for (int i : requiredFields) {
			if (!isFieldPresent(i, requestMsg))
				return ABORTED;
		}

		return PREPARED | NO_JOIN;
	}

	@Override
	public void setConfiguration(Configuration cfg) throws ConfigurationException {
		this.cfg = cfg;
	}

	private int[] retrieveAllRequiredFields() {
		return cfg.getInts(Constant.VALIDATE_FIELDS_REQUIRED);
	}

	private boolean isFieldPresent(int fieldNum, ISOMsg msg) {

		try {

			Object fieldValue = msg.getValue(fieldNum);
			return fieldValue != null;

		} catch (ISOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
