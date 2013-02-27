package de.uvwxy.melogsta;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import android.net.LocalServerSocket;
import android.net.LocalSocket;

public class SocketIPCServer implements Runnable {
	public static String SOCKET_ADDRESS_PREFIX = "/melogsta/";
	private LocalServerSocket server;
	private LocalSocket receiver;
	private boolean running = true;

	public SocketIPCServer(int pid) {
		try {
			server = new LocalServerSocket(SOCKET_ADDRESS_PREFIX + "" + pid);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		running = false;
	}

	@Override
	public void run() {
		while (running) {
			DataInputStream input = null;
			try {
				receiver = server.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (receiver != null) {
				int prio = -1;
				try {
					input = new DataInputStream(receiver.getInputStream());
					prio = input.readInt();

					ObjectOutputStream output = new ObjectOutputStream(receiver.getOutputStream());
					ArrayList<LogHistoryItem> logHistoryList = new ArrayList<LogHistoryItem>();
					Log.getLocalLog(prio, logHistoryList);
					output.writeObject(logHistoryList);

					input.close();
					output.close();
					receiver.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		}

	}
}
