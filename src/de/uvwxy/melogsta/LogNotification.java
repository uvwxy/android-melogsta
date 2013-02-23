package de.uvwxy.melogsta;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

public class LogNotification {
	private NotificationManager nm = null;

	private void getNM(Context ctx) {
		if (ctx != null && nm == null) {
			nm = (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
		}
	}

	void notify(Context ctx, int priority, String message) {
		int iconID = -1;
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
		
		// TODO: handle multiple notification types and all notifications as one notification type
		
		updateNotification(ctx, notificationID, iconID, null, null);
	}

	private void updateNotification(Context ctx, int notificationID, int iconID, String title, String contentText) {
		getNM(ctx);
		if (nm == null)
			return;

		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(ctx).setSmallIcon(iconID)
				.setContentTitle(title).setContentText(contentText);
		// Creates an explicit intent for an Activity in your app
		Intent resultIntent = new Intent(ctx, ActivityShowLogs.class);

		// The stack builder object will contain an artificial back stack for the
		// started Activity.
		// This ensures that navigating backward from the Activity leads out of
		// your application to the Home screen.
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(ctx);
		// Adds the back stack for the Intent (but not the Intent itself)
		stackBuilder.addParentStack(ActivityShowLogs.class);
		// Adds the Intent that starts the Activity to the top of the stack
		stackBuilder.addNextIntent(resultIntent);

		PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
		mBuilder.setContentIntent(resultPendingIntent);

		// mId allows you to update the notification later on.
		nm.notify(notificationID, mBuilder.build());
	}
}
