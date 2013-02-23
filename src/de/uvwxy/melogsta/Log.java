package de.uvwxy.melogsta;

public class Log {
	private static boolean logAnything = true;

	private static LogTypeState logD = new LogTypeState();
	private static LogTypeState logE = new LogTypeState();
	private static LogTypeState logI = new LogTypeState();
	private static LogTypeState logV = new LogTypeState();
	private static LogTypeState logW = new LogTypeState();
	private static LogTypeState logWTF = new LogTypeState();
	private static LogTypeState logLine = new LogTypeState();

	private static LogTypeState[] allLogs = { logD, logE, logI, logV, logW, logWTF, logLine };

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
		if (lts.logHistory != null && lts.logToHistory)
			lts.logHistory.add(new LogHistoryItem(priority, tag, msg, tr));
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

	public static int wtf(String tag, String msg, Throwable tr) {
		return addLine(logWTF, android.util.Log.ASSERT, tag, msg, tr);
	}

}
