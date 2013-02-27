package de.uvwxy.melogsta;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

class LogNotification {
	private static NotificationManager nm = null;

	private static void getNM(Context ctx) {
		if (ctx != null && nm == null) {
			nm = (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
		}
	}

	static void notify(Context ctx, int notificationID, int priority, String tag, String message) {
		if (ctx == null) {
			android.util.Log.i("MELOGSTA", "Context was NULL!");
			return;
		}
		int iconID = Log.getIconID(priority);
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
		resultIntent.putExtra(Log.BUNDLE_EXTRA_INT_PID, android.os.Process.myPid());

		PendingIntent resultPendingIntent = PendingIntent.getActivity(ctx, notificationID, resultIntent,
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
