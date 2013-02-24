package de.uvwxy.melogsta;

import android.content.Context;

public class Log {
	static final String BUNDLE_EXTRA_INT_PRIORITY = "priority";

	private static String emailAddress = "code@uvwxy.de";

	private static Context ctx = null;

	private static boolean logAnything = true;

	private static boolean combinedNotification = false;

	private static LogTypeState logD = new LogTypeState();
	private static LogTypeState logE = new LogTypeState();
	private static LogTypeState logI = new LogTypeState();
	private static LogTypeState logV = new LogTypeState();
	private static LogTypeState logW = new LogTypeState();
	private static LogTypeState logWTF = new LogTypeState();
	private static LogTypeState logLine = new LogTypeState();

	private static LogTypeState[] allLogs = { logD, logE, logI, logV, logW, logWTF, logLine };

	static {
		// setup shared and own notification ids:
		LogTypeState.sharedNotificationID = 1337;
		logD.ownNotificationID = LogTypeState.sharedNotificationID + 1;
		logE.ownNotificationID = logD.ownNotificationID + 1;
		logI.ownNotificationID = logE.ownNotificationID + 1;
		logV.ownNotificationID = logI.ownNotificationID + 1;
		logW.ownNotificationID = logV.ownNotificationID + 1;
		logWTF.ownNotificationID = logW.ownNotificationID + 1;
		logLine.ownNotificationID = logWTF.ownNotificationID + 1;
	}

	public static String getEmailAddress() {
		return emailAddress;
	}

	public static void setEmailAddress(String emailAddress) {
		Log.emailAddress = emailAddress;
	}

	public static boolean isCombinedNotification() {
		return combinedNotification;
	}

	public static boolean isLogAnything() {
		return logAnything;
	}

	public static LogTypeState getLogD() {
		return logD;
	}

	public static LogTypeState getLogE() {
		return logE;
	}

	public static LogTypeState getLogI() {
		return logI;
	}

	public static LogTypeState getLogV() {
		return logV;
	}

	public static LogTypeState getLogW() {
		return logW;
	}

	public static LogTypeState getLogWTF() {
		return logWTF;
	}

	public static LogTypeState getLogLine() {
		return logLine;
	}

	public static LogTypeState[] getAllLogs() {
		return allLogs;
	}

	public static void setLogAnything(boolean logAnything) {
		Log.logAnything = logAnything;
	}

	public static void setContext(Context ctx) {
		Log.ctx = ctx;
	}

	public static void cancelAllNotifications() {
		for (LogTypeState lts : allLogs) {
			LogNotification.cancelNotification(ctx, lts.ownNotificationID);
		}
		LogNotification.cancelNotification(ctx, LogTypeState.sharedNotificationID);
	}

	public static void setCombinedNotification(boolean on) {
		// if separate notifications are enabled: cancel shown notifications
		if (!combinedNotification && on) {
			for (LogTypeState lts : allLogs) {
				LogNotification.cancelNotification(ctx, lts.ownNotificationID);
			}
		}

		// if combined notifications are enabled: cancel shown notification
		if (combinedNotification && !on) {
			LogNotification.cancelNotification(ctx, LogTypeState.sharedNotificationID);
		}

		combinedNotification = on;
	}

	public static void setAllLogToLogCat(boolean on) {
		for (LogTypeState lts : allLogs)
			lts.logToLogCat = on;
	}

	public static void setAllLogToHistory(boolean on) {
		for (LogTypeState lts : allLogs)
			lts.logToHistory = on;
	}

	private static int addLine(LogTypeState lts, int priority, String tag, String msg, Throwable tr) {
		if (!logAnything) {
			return -1;
		}

		if (lts == null)
			return -1;

		if (lts.throwNotification) {
			LogNotification.notify(ctx, combinedNotification ? LogTypeState.sharedNotificationID
					: lts.ownNotificationID, priority, tag, msg);
		}
		if (lts.logHistory != null && lts.logToHistory)
			lts.logHistory.add(new LogHistoryItem(priority, tag, msg, System.currentTimeMillis(), tr));
		if (lts.logToLogCat)
			return android.util.Log.println(priority, tag, msg);
		return 0;
	}

	/**
	 * @see android.util.Log
	 * 
	 * @param tag
	 * @param msg
	 * @return
	 */
	public static int d(String tag, String msg) {
		return addLine(logD, android.util.Log.DEBUG, tag, msg, null);
	}

	/**
	 * @see android.util.Log
	 * 
	 * @param tag
	 * @param msg
	 * @param tr
	 * @return
	 */
	public static int d(String tag, String msg, Throwable tr) {
		return addLine(logD, android.util.Log.DEBUG, tag, msg, tr);
	}

	/**
	 * @see android.util.Log
	 * 
	 * @param tag
	 * @param msg
	 * @return
	 */
	public static int e(String tag, String msg) {
		return addLine(logE, android.util.Log.ERROR, tag, msg, null);

	}

	/**
	 * @see android.util.Log
	 * 
	 * @param tag
	 * @param msg
	 * @param tr
	 * @return
	 */
	public static int e(String tag, String msg, Throwable tr) {
		return addLine(logE, android.util.Log.ERROR, tag, msg, tr);

	}

	/**
	 * @see android.util.Log
	 * 
	 * @param tr
	 * @return
	 */
	public static String getStackTraceString(Throwable tr) {
		return android.util.Log.getStackTraceString(tr);
	}

	/**
	 * @see android.util.Log
	 * 
	 * @param tag
	 * @param msg
	 * @return
	 */
	public static int i(String tag, String msg) {
		android.util.Log.i("MELOGSTA", "i(...)");

		return addLine(logI, android.util.Log.INFO, tag, msg, null);
	}

	/**
	 * @see android.util.Log
	 * 
	 * @param tag
	 * @param msg
	 * @param tr
	 * @return
	 */
	public static int i(String tag, String msg, Throwable tr) {
		return addLine(logI, android.util.Log.INFO, tag, msg, tr);

	}

	/**
	 * @see android.util.Log
	 * 
	 * @param tag
	 * @param level
	 * @return
	 */
	public static boolean isLoggable(String tag, int level) {
		return android.util.Log.isLoggable(tag, level);
	}

	/**
	 * @see android.util.Log
	 * 
	 * @param priority
	 * @param tag
	 * @param msg
	 * @return
	 */
	public int println(int priority, String tag, String msg) {
		return addLine(logLine, priority, tag, msg, null);
	}

	/**
	 * @see android.util.Log
	 * 
	 * @param tag
	 * @param msg
	 * @return
	 */
	public static int v(String tag, String msg) {
		return addLine(logV, android.util.Log.VERBOSE, tag, msg, null);
	}

	/**
	 * @see android.util.Log
	 * 
	 * @param tag
	 * @param msg
	 * @param tr
	 * @return
	 */
	public static int v(String tag, String msg, Throwable tr) {
		return addLine(logV, android.util.Log.VERBOSE, tag, msg, tr);
	}

	/**
	 * @see android.util.Log
	 * 
	 * @param tag
	 * @param msg
	 * @return
	 */
	public static int w(String tag, String msg) {
		return addLine(logW, android.util.Log.WARN, tag, msg, null);
	}

	/**
	 * @see android.util.Log
	 * 
	 * @param tag
	 * @param msg
	 * @param tr
	 * @return
	 */
	public static int w(String tag, String msg, Throwable tr) {
		return addLine(logW, android.util.Log.WARN, tag, msg, tr);
	}

	/**
	 * @see android.util.Log
	 * 
	 * @param tag
	 * @param tr
	 * @return
	 */
	public static int wtf(String tag, Throwable tr) {
		return addLine(logWTF, android.util.Log.ASSERT, tag, "", tr);

	}

	/**
	 * @see android.util.Log
	 * 
	 * @param tag
	 * @param msg
	 * @return
	 */
	public static int wtf(String tag, String msg) {
		return addLine(logWTF, android.util.Log.ASSERT, tag, msg, null);
	}

	/**
	 * @see android.util.Log
	 * 
	 * @param tag
	 * @param msg
	 * @param tr
	 * @return
	 */
	public static int wtf(String tag, String msg, Throwable tr) {
		return addLine(logWTF, android.util.Log.ASSERT, tag, msg, tr);
	}

}
