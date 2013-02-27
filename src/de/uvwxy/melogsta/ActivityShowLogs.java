package de.uvwxy.melogsta;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

public class ActivityShowLogs extends Activity {

	private ArrayList<LogHistoryItem> logHistoryList;
	private LogHistoryItemArrayAdapter defaultArrayAdapter;
	private ListView defaultListView;
	private OnItemClickListener defaultListOnItemOnClickHandler = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
			// TODO: implement OnClick functionality here!
			Log.i("MELOGSTA", "Clicked on pos(" + pos + ") id(" + id + ")");
		}
	};

	private OnItemLongClickListener defaultListOnItemLongClickHandler = new OnItemLongClickListener() {

		@Override
		public boolean onItemLongClick(AdapterView<?> arg0, View view, int pos, long id) {
			// TODO: implement OnLongClick functionality here!
			Log.i("MELOGSTA", "LongClicked on pos(" + pos + ") id(" + id + ")");
			return false;
		}

	};
	private int priority;
	private int remotePID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_show_logs);

		defaultListView = (ListView) findViewById(R.id.lvDefault);
		logHistoryList = new ArrayList<LogHistoryItem>();

		priority = getIntent().getIntExtra(Log.BUNDLE_EXTRA_INT_PRIORITY, -1337);
		remotePID = getIntent().getIntExtra(Log.BUNDLE_EXTRA_INT_PID, android.os.Process.myPid());

		defaultArrayAdapter = new LogHistoryItemArrayAdapter(this, getLayoutInflater(), R.id.lvDefault, logHistoryList);
		defaultListView.setAdapter(defaultArrayAdapter);
		defaultListView.setOnItemClickListener(defaultListOnItemOnClickHandler);
		defaultListView.setOnItemLongClickListener(defaultListOnItemLongClickHandler);

		readLogs();
	}

	public void refreshLogs(View v) {
		readLogs();
	}

	private void readLogs() {
		boolean localLog = android.os.Process.myPid() == remotePID;

		if (localLog) {
			Log.getLocalLog(priority, logHistoryList);
		} else {
			getRemoteLogs(priority, remotePID, logHistoryList);
		}

		defaultArrayAdapter.notifyDataSetChanged();
		android.util.Log.i("MELOGSTA", "LogHistory size. " + logHistoryList.size() + " priority = " + priority);
	}

	private void getRemoteLogs(int priority, int pid, ArrayList<LogHistoryItem> logHistoryList) {
		SocketIPCClient client = new SocketIPCClient(priority, pid);
		ArrayList<LogHistoryItem> remoteList = client.getRemoteLogList();

		if (remoteList != null) {
			logHistoryList.clear();
			logHistoryList.addAll(remoteList);
			android.util.Log.i("MELOGSTA", "Read " + remoteList.size() + " entries");
		} else {
			android.util.Log.i("MELOGSTA", "Read null object");
		}
	}

	public void sendMail(View v) {
		String bugReport = "";

		for (LogHistoryItem lhi : logHistoryList) {
			bugReport += lhi.getTimestamp() + ", " + getPriorityString(lhi.getPriority()) + ", " + lhi.getTag() + ", "
					+ lhi.getMsg() + "\n";
		}

		Intent intentMail = new Intent(android.content.Intent.ACTION_SEND);
		intentMail.setType("plain/text");
		intentMail.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] { Log.getEmailAddress() });
		intentMail.putExtra(android.content.Intent.EXTRA_SUBJECT, "[BUGREPORT][" + getApplicationInfo().packageName
				+ "]");
		intentMail.putExtra(android.content.Intent.EXTRA_TEXT, bugReport);

		startActivity(Intent.createChooser(intentMail, "Send bugreport..."));
	}

	private String getPriorityString(int priority) {
		switch (priority) {
		case android.util.Log.ASSERT:
			return "ASSERT";
		case android.util.Log.DEBUG:
			return "DEBUG";
		case android.util.Log.ERROR:
			return "ERROR";
		case android.util.Log.INFO:
			return "INFO";
		case android.util.Log.VERBOSE:
			return "VERBOSE";
		case android.util.Log.WARN:
			return "WARN";
		default:
			return "" + priority;
		}
	}
}
