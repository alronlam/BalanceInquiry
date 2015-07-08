package transaction.participant;

import java.io.IOException;
import java.io.Serializable;

import org.jpos.core.Configurable;
import org.jpos.core.Configuration;
import org.jpos.core.ConfigurationException;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOSource;
import org.jpos.transaction.Context;
import org.jpos.transaction.TransactionParticipant;

import constant.Constant;

public class SendInquiryResponse implements TransactionParticipant, Configurable {

	private Configuration cfg;

	@Override
	public void abort(long id, Serializable context) {
		if (cfg.getBoolean("debug", true))
			System.out.println("\n*****\nSendInquiryResponse Abort\n*****\n");
	}

	@Override
	public void commit(long id, Serializable context) {
		if (cfg.getBoolean("debug", true))
			System.out.println("\n*****\nSendInquiryResponse Commit\n*****\n");

		ISOSource sender = (ISOSource) ((Context) context).get(Constant.ISOSOURCE);
		ISOMsg responseMsg = (ISOMsg) ((Context) context).get(Constant.RESPONSE);
		try {
			sender.send(responseMsg);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ISOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int prepare(long id, Serializable context) {
		if (cfg.getBoolean("debug", true))
			System.out.println("\n*****\nSendInquiryResponse Prepare\n*****\n");

		return PREPARED;
	}

	@Override
	public void setConfiguration(Configuration arg0) throws ConfigurationException {
		this.cfg = cfg;
	}

}
