import org.jpos.iso.ISOChannel;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.channel.ASCIIChannel;
import org.jpos.iso.packager.ISO87APackager;
import org.jpos.q2.Q2;
import org.jpos.transaction.TransactionManager;
import org.jpos.util.LogSource;
import org.jpos.util.Logger;
import org.jpos.util.SimpleLogListener;

import constant.Constant;

public class Client {

	public static void main(String[] args) throws Exception {
		Logger logger = new Logger();
		logger.addListener(new SimpleLogListener(System.out));
		ISOChannel channel = new ASCIIChannel("localhost", 8080, new ISO87APackager());

		((LogSource) channel).setLogger(logger, "test-channel");
		channel.connect();

		ISOMsg m = new ISOMsg();
		m.setMTI("0200");
		m.set(2, "4001");
		m.set(3, "300000");
		m.set(4, "00000001");
		m.set(7, "301");
		m.set(14, "1");
		m.set(39, "1");
		m.set(41, "1");
		m.set(49, "1");

		channel.send(m);
		ISOMsg r = channel.receive();

		if (r.getString(39).equals(Constant.RESPONSE_OK))
			System.out.println("\n\n*****\n\nServer says I have a balance of " + r.getValue(4) + ".\n\n*****\n\n");
		else
			System.out.println("\n\n*****\n\nServer denied my request. :(\n\n*****\n\n");

		channel.disconnect();

		TransactionManager tm;
		Q2 q2Server = new Q2();
		q2Server.start();
	}
}
