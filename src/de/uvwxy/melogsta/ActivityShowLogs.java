package de.uvwxy.melogsta;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_show_logs);

		defaultListView = (ListView) findViewById(R.id.lvDefault);
		logHistoryList = new ArrayList<LogHistoryItem>();

		int priority;
		if (Log.isCombinedNotification()) {
			for (LogTypeState lts : Log.getAllLogs()) {
				logHistoryList.addAll(lts.logHistory);
			}
		} else if ((priority = getIntent().getIntExtra(Log.BUNDLE_EXTRA_INT_PRIORITY, -1337)) != -1337) {

			switch (priority) {
			case android.util.Log.ASSERT:
				logHistoryList.addAll(Log.getLogWTF().logHistory);
				break;
			case android.util.Log.DEBUG:
				logHistoryList.addAll(Log.getLogD().logHistory);
				break;
			case android.util.Log.ERROR:
				logHistoryList.addAll(Log.getLogE().logHistory);
				break;
			case android.util.Log.INFO:
				logHistoryList.addAll(Log.getLogI().logHistory);
				break;
			case android.util.Log.VERBOSE:
				logHistoryList.addAll(Log.getLogV().logHistory);
				break;
			case android.util.Log.WARN:
				logHistoryList.addAll(Log.getLogW().logHistory);
				break;
			default:
				logHistoryList.addAll(Log.getLogLine().logHistory);
			}
		}

		defaultArrayAdapter = new LogHistoryItemArrayAdapter(this, getLayoutInflater(), R.id.lvDefault, logHistoryList);
		defaultListView.setAdapter(defaultArrayAdapter);
		defaultListView.setOnItemClickListener(defaultListOnItemOnClickHandler);
		defaultListView.setOnItemLongClickListener(defaultListOnItemLongClickHandler);
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
