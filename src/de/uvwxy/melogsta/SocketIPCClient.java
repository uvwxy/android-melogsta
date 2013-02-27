package de.uvwxy.melogsta;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import android.net.LocalSocket;
import android.net.LocalSocketAddress;

public class SocketIPCClient {
	private int prio = -1;
	private int pid = -1;

	public SocketIPCClient(int priority, int pid) {
		this.prio = priority;
		this.pid = pid;
	}

	public ArrayList<LogHistoryItem> getRemoteLogList() {
		LocalSocket client = new LocalSocket();
		ArrayList<LogHistoryItem> result = null;
		try {
			client.connect(new LocalSocketAddress(SocketIPCServer.SOCKET_ADDRESS_PREFIX + pid));

			DataOutputStream output = new DataOutputStream(client.getOutputStream());
			output.writeInt(prio);

			ObjectInputStream input = new ObjectInputStream(client.getInputStream());
			result = (ArrayList<LogHistoryItem>) input.readObject();

			output.close();
			input.close();
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return result;
	}
}
