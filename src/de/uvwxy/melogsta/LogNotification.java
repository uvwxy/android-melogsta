package de.uvwxy.melogsta;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

class LogNotification {
	private static NotificationManager nm = null;

	private static void getNM(Context ctx) {
		if (ctx != null && nm == null) {
			nm = (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
		}
	}

	static void notify(Context ctx, int notificationID, int priority, String tag, String message) {
		android.util.Log.i("MELOGSTA", "notify(...)");

		int iconID = R.drawable.icon_line;
		switch (priority) {
		case android.util.Log.ASSERT:
			iconID = R.drawable.icon_wtf;
			break;
		case android.util.Log.DEBUG:
			iconID = R.drawable.icon_debug;
			break;
		case android.util.Log.ERROR:
			iconID = R.drawable.icon_error;
			break;
		case android.util.Log.INFO:
			iconID = R.drawable.icon_info;
			break;
		case android.util.Log.VERBOSE:
			iconID = R.drawable.icon_verbose;
			break;
		case android.util.Log.WARN:
			iconID = R.drawable.icon_warn;
			break;
		default:
			iconID = R.drawable.icon_line;
		}

		updateNotification(ctx, notificationID, priority, iconID, ctx.getPackageName(), tag + ": " + message);
	}

	private static void updateNotification(Context ctx, int notificationID, int priority, int iconID, String title,
			String contentText) {
		getNM(ctx);
		if (nm == null)
			return;

		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(ctx).setSmallIcon(iconID)
				.setContentTitle(title).setContentText(contentText);
		// Creates an explicit intent for an Activity in your app
		Intent resultIntent = new Intent(ctx, ActivityShowLogs.class);

		if (!Log.isCombinedNotification()) {
			resultIntent.putExtra(Log.BUNDLE_EXTRA_INT_PRIORITY, priority);
		}

		PendingIntent resultPendingIntent = PendingIntent.getActivity(ctx, 0, resultIntent,
				PendingIntent.FLAG_CANCEL_CURRENT);

		mBuilder.setContentIntent(resultPendingIntent);

		nm.notify(notificationID, mBuilder.build());
	}

	public static void cancelNotification(Context ctx, int notificationID) {
		getNM(ctx);
		if (nm == null)
			return;
		nm.cancel(notificationID);
	}
}
