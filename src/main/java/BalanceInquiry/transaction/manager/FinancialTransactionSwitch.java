package transaction.manager;

import java.io.Serializable;

import org.jpos.core.Configurable;
import org.jpos.core.Configuration;
import org.jpos.core.ConfigurationException;
import org.jpos.iso.ISOMsg;
import org.jpos.transaction.Context;
import org.jpos.transaction.GroupSelector;

import constant.Constant;

public class FinancialTransactionSwitch implements GroupSelector, Configurable {

	private Configuration cfg;

	@Override
	public void abort(long arg0, Serializable arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void commit(long arg0, Serializable arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public int prepare(long arg0, Serializable arg1) {
    System.out.println("IN PREPARE");
		return PREPARED | READONLY | NO_JOIN;
	}

	@Override
	public void setConfiguration(Configuration cfg) throws ConfigurationException {
		this.cfg = cfg;
	}

	@Override
	public String select(long arg0, Serializable context) {
		try {
			ISOMsg reqMsg = (ISOMsg) ((Context) context).get(Constant.REQUEST);

			if (cfg.getBoolean("debug", true))
				System.out.println("\n*****\nIn Switch: Request with Transaction Code " + reqMsg.getString(3) + " is "
						+ cfg.get(reqMsg.getString(3)) + "!\n*****\n");

			return cfg.get(reqMsg.getString(3), null);
		} catch (Exception e) {
			return null;
		}
	}

}
