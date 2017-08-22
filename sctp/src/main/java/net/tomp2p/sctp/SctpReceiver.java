package net.tomp2p.sctp;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

import net.tomp2p.utils.Pair;

public class SctpReceiver {

	final private SctpSocket socket;
	final private UdpLink link;

	public SctpReceiver(InetSocketAddress local) throws IOException {
		socket = Sctp.createSocket(local.getPort());
		link = new UdpLink(socket, local.getAddress().getHostAddress(), local.getPort());
		socket.setLink(link);
	}

	public void listen(InetSocketAddress local) throws IOException {
		socket.listen();

		SctpDataCallback callback = new SctpDataCallback() {

			@Override
			public void onSctpPacket(byte[] data, int sid, int ssn, int tsn, long ppid, int context, int flags,
					Pair<InetAddress, Integer> remote) {
				String s = new String(data, StandardCharsets.UTF_8);

				System.out.println("got message from " + remote.element0() + ":" + remote.element1() +": \\n " + s);

				String message = s + " replied";

				int success = -1;
				//try {
					link.setRemoteIp(remote.element0());
					link.setRemotePort(remote.element1());
					//link.onConnOut(socket, s.getBytes());
					// success = socket.send(message.getBytes(), 0, message.getBytes().length,
					// false, 1, 0);
				//} catch (IOException e) {
				//	e.printStackTrace();
				//}
			}
		};

		SctpListenThread thread = new SctpListenThread(socket, callback);
		thread.start();

	}

}