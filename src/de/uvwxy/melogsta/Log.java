package de.uvwxy.melogsta;

import java.util.List;

import android.content.Context;

public class Log {
	static final String BUNDLE_EXTRA_INT_PRIORITY = "priority";
	static final String BUNDLE_EXTRA_INT_PID = "logPid";

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
	private static SocketIPCServer server;
	private static Thread serverThread;

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

	public static void disableAllNotifications() {
		for (LogTypeState lts : allLogs) {
			LogNotification.cancelNotification(ctx, lts.ownNotificationID);
			lts.throwNotification = false;
		}
	}

	public static void enableAllNotifications() {
		for (LogTypeState lts : allLogs) {
			lts.throwNotification = true;
		}
	}

	public static void setAllLogToLogCat(boolean on) {
		for (LogTypeState lts : allLogs)
			lts.logToLogCat = on;
	}

	public static void setAllLogToHistory(boolean on) {
		for (LogTypeState lts : allLogs)
			lts.logToHistory = on;
	}

	public static void startIPCServer() {
		android.util.Log.i("MELOGSTA", "Starting IPCServer");
		server = new SocketIPCServer(android.os.Process.myPid());
		serverThread = new Thread(server);
		serverThread.start();
	}

	public static void stopIPCServer() {
		android.util.Log.i("MELOGSTA", "Stopping IPCServer");
		if (server != null) {
			server.stop();
			serverThread.interrupt();
		}
	}

	public static void getLocalLog(int priority, List<LogHistoryItem> logHistoryList) {
		if (isCombinedNotification()) {
			for (LogTypeState lts : Log.getAllLogs()) {
				logHistoryList.addAll(lts.logHistory);
			}
		} else {
			switch (priority) {
			case android.util.Log.ASSERT:
				android.util.Log.i("MELOGSTA", "Log Size: " + Log.getLogWTF().logHistory.size());
				logHistoryList.addAll(Log.getLogWTF().logHistory);
				break;
			case android.util.Log.DEBUG:
				android.util.Log.i("MELOGSTA", "Log Size: " + Log.getLogD().logHistory.size());
				logHistoryList.addAll(Log.getLogD().logHistory);
				break;
			case android.util.Log.ERROR:
				android.util.Log.i("MELOGSTA", "Log Size: " + Log.getLogE().logHistory.size());
				logHistoryList.addAll(Log.getLogE().logHistory);
				break;
			case android.util.Log.INFO:
				android.util.Log.i("MELOGSTA", "Log Size: " + Log.getLogI().logHistory.size());
				logHistoryList.addAll(Log.getLogI().logHistory);
				break;
			case android.util.Log.VERBOSE:
				android.util.Log.i("MELOGSTA", "Log Size: " + Log.getLogV().logHistory.size());
				logHistoryList.addAll(Log.getLogV().logHistory);
				break;
			case android.util.Log.WARN:
				android.util.Log.i("MELOGSTA", "Log Size: " + Log.getLogW().logHistory.size());
				logHistoryList.addAll(Log.getLogW().logHistory);
				break;
			default:
				logHistoryList.addAll(Log.getLogLine().logHistory);
			}
		}
	}

	private static int addLine(LogTypeState lts, int priority, String tag, String msg, Throwable tr) {
		if (!logAnything) {
			return -1;
		}

		if (lts == null) {
			android.util.Log.i("MELOGSTA", "LTS == null, prio = " + priority);
			return -1;
		}

		if (lts.throwNotification) {
			LogNotification.notify(ctx, combinedNotification ? LogTypeState.sharedNotificationID
					: lts.ownNotificationID, priority, tag, msg);
		}
		if (lts.logHistory != null && lts.logToHistory) {
			android.util.Log.i("MELOGSTA", "adding (" + priority + ") " + tag + ", " + msg);
			lts.logHistory.add(new LogHistoryItem(priority, tag, msg, System.currentTimeMillis(), tr));
		} else {
			android.util.Log.i("MELOGSTA", "LogHistory is null? = " + (lts.logHistory == null) + "priority = "
					+ priority);
		}
		if (lts.logToLogCat)
			return android.util.Log.println(priority, tag, msg);
		return 0;
	}

	/**
	 * @param priority
	 * @return
	 */
	public static int getIconID(int priority) {
		int iconID;
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
		return iconID;
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
