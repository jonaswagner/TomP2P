package net.tomp2p.connection.sctp;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

public class SctpReceiverTestDriver {
	public static void main(String[] args) {
		Sctp.init();
		
		InetSocketAddress local = null;
		try {
			local = new InetSocketAddress(InetAddress.getByName("192.168.0.103"), 1);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		if (local == null) {
			throw new NullPointerException();
		}

		SctpReceiver receiver = null;
		try {
			receiver = new SctpReceiver(local);
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (receiver == null) {
			throw new NullPointerException();
		}
		
		try {
			receiver.listen(local);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			Thread.sleep(400000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
