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

public class CreateInquiryResponse implements TransactionParticipant, Configurable {

	private Configuration cfg;

	@Override
	public void abort(long id, Serializable context) {
		if (cfg.getBoolean("debug", true))
			System.out.println("\n*****\nCreateInquiryResponse Abort\n*****\n");
	}

	@Override
	public void commit(long id, Serializable context) {
		if (cfg.getBoolean("debug", true))
			System.out.println("\n*****\nCreateInquiryResponse Commit\n*****\n");

		ISOMsg responseMsg = (ISOMsg) ((Context) context).get(Constant.REQUEST);
		try {

			Object balance = ((Context) context).get(Constant.BALANCE);

			if (balance != null) {

				responseMsg.set(39, Constant.RESPONSE_OK);
				responseMsg.set(4, balance.toString());

				if (cfg.getBoolean("debug", true))
					System.out.println("\n*****\nCreateInquiryResponse Commit: Balance is "+balance.toString()+"\n*****\n");

			} else{

				responseMsg.set(39, Constant.RESPONSE_NO);

				if (cfg.getBoolean("debug", true))
					System.out.println("\n*****\nCreateInquiryResponse Commit: Balance is unavailable\n*****\n");

			}

			((Context) context).put(Constant.RESPONSE, responseMsg);
		} catch (ISOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int prepare(long id, Serializable context) {
		if (cfg.getBoolean("debug", true))
			System.out.println("\n*****\nCreateInquiryResponse Prepare\n*****\n");

		return PREPARED;
	}

	@Override
	public void setConfiguration(Configuration cfg) throws ConfigurationException {
		this.cfg = cfg;
	}

}
