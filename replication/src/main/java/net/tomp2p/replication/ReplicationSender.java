package net.tomp2p.replication;

import net.tomp2p.futures.FutureDone;
import net.tomp2p.peers.Number160;
import net.tomp2p.peers.Number640;
import net.tomp2p.peers.PeerAddress;
import net.tomp2p.storage.Data;

import java.util.NavigableMap;

public interface ReplicationSender {
	FutureDone<?> sendDirect(final PeerAddress other, final Number160 locationKey, final NavigableMap<Number640, Data> dataMap);
}
