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
		android.util.Log.i("MELOGSTA", "SocketIPCClient created");
	}

	public ArrayList<LogHistoryItem> getRemoteLogList() {
		LocalSocket client = new LocalSocket();
		ArrayList<LogHistoryItem> result = null;
		try {
			client.connect(new LocalSocketAddress(SocketIPCServer.SOCKET_ADDRESS_PREFIX + pid));
			android.util.Log.i("MELOGSTA", "SocketIPCClient connected");

			DataOutputStream output = new DataOutputStream(client.getOutputStream());
			output.writeInt(prio);
			android.util.Log.i("MELOGSTA", "SocketIPCClient written prio");

			ObjectInputStream input = new ObjectInputStream(client.getInputStream());
			android.util.Log.i("MELOGSTA", "SocketIPCClient reading object");
			result = (ArrayList<LogHistoryItem>) input.readObject();

			android.util.Log.i("MELOGSTA", "SocketIPCClient closing streams");
			output.close();
			input.close();
			client.close();
			android.util.Log.i("MELOGSTA", "SocketIPCClient object read");
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return result;
	}
}
