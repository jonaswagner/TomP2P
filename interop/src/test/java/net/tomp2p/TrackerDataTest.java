package net.tomp2p;

import net.tomp2p.message.Message;
import net.tomp2p.message.TestMessage;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TrackerDataTest {

	@Test
	public void encodeDecodeTest() throws Exception {

		// create sample data maps
		Message m1 = MessageEncodeDecode.createMessageSetTrackerData();
		
		Message m2 = TestMessage.encodeDecode(m1);

		assertTrue(MessageEncodeDecode.checkIsSameList(m1.trackerDataList(), m2.trackerDataList()));
	}
}
