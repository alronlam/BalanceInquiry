package server;

import constant.Constant;

import org.jpos.core.Configurable;
import org.jpos.core.Configuration;
import org.jpos.core.ConfigurationException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISORequestListener;
import org.jpos.iso.ISOSource;
import org.jpos.space.Space;
import org.jpos.space.SpaceFactory;
import org.jpos.transaction.Context;

public class JPOSServerListener implements ISORequestListener, Configurable {

	private String queueName;
	private Space<String, Context> sp;
	private Long timeout;

	public JPOSServerListener() {
		super();
	}

	@Override
	public boolean process(ISOSource isoSrc, ISOMsg isoMsg) {
		Context ctx = new Context();
		ctx.put(Constant.REQUEST, isoMsg);
		ctx.put(Constant.ISOSOURCE, isoSrc);
		sp.out(queueName, ctx, timeout);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setConfiguration(Configuration cfg)
			throws ConfigurationException {
		queueName = cfg.get("queue");
		timeout = cfg.getLong("timeout");
		sp = SpaceFactory.getSpace(cfg.get("space"));
	}

}
