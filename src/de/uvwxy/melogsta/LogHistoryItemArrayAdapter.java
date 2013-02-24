package de.uvwxy.melogsta;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LogHistoryItemArrayAdapter extends ArrayAdapter<LogHistoryItem> {
	private Context context;
	private Resources r;
	private LayoutInflater inflater;
	private ArrayList<LogHistoryItem> logList;

	public LogHistoryItemArrayAdapter(Context context, LayoutInflater inflater, int textViewResourceId,
			List<LogHistoryItem> objects) {
		super(context, textViewResourceId, objects);
		this.inflater = inflater;
		this.context = context;
		this.logList = (ArrayList<LogHistoryItem>) objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View entry = inflater.inflate(R.layout.lv_log_history_item, parent, false);

		TextView tvLine1 = (TextView) entry.findViewById(R.id.tvTag);
		TextView tvLine2 = (TextView) entry.findViewById(R.id.tvMsg);
		ImageView ivIcon = (ImageView) entry.findViewById(R.id.ivIcon);

		LogHistoryItem item = logList.get(position);

		String line1 = item.getTag() != null ? item.getTag() : "[empty]";
		line1 += ": " + getTimeString(item.getTimestamp());
		String line2 = item.getMsg() != null ? item.getMsg() : "[empty]";
		tvLine1.setText(line1);
		tvLine2.setText(line2);

		r = context.getResources();
		ivIcon.setImageBitmap(BitmapFactory.decodeResource(r, R.drawable.icon_info));

		return entry;
	}

	public static String getTimeString(long time) {
		int ss = ((int) time) % 60;
		int mm = ((int) time / 60) % 60;
		int hh = ((int) time) / 60 / 60;

		return "" + (hh < 10 ? "0" + hh : hh) + ":" + (mm < 10 ? "0" + mm : mm) + ":" + (ss < 10 ? "0" + ss : ss);
	}

	public static String getDateTime(Context context, long timestamp) {
		Date date = new Date(timestamp);
		String res = DateFormat.getDateFormat(context).format(date);
		res += " " + DateFormat.getTimeFormat(context).format(date);
		return res;
	}

	public static String getDate(long timestamp, int type) {
		String ds = null;

		switch (type) {

		default:
			Calendar c = new GregorianCalendar();
			c.setTimeInMillis(timestamp);

			ds = c.get(Calendar.DAY_OF_MONTH) + "." + (c.get(Calendar.MONTH) + 1) + "." + c.get(Calendar.YEAR);
		}

		return ds;
	}
}