package de.uvwxy.melogsta;

import java.util.LinkedList;

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

	/**
	 * @see android.util.Log
	 * 
	 * @param tag
	 * @param msg
	 * @return
	 */
	public static int d(String tag, String msg) {
		if (!logAnything) {
			return -1;
		}

		if (logD.logToHistory) {
			// TODO!
		}

		if (logD.logToLogCat) {
			return android.util.Log.d(tag, msg);
		} else {
			return 0;
		}
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
		if (!logAnything) {
			return -1;
		}

		if (logD.logToHistory) {
			// TODO!
		}

		if (logD.logToLogCat) {
			return android.util.Log.d(tag, msg, tr);
		} else {
			return 0;
		}
	}

	/**
	 * @see android.util.Log
	 * 
	 * @param tag
	 * @param msg
	 * @return
	 */
	public static int e(String tag, String msg) {
		if (!logAnything) {
			return -1;
		}

		if (logE.logToHistory) {
			// TODO!
		}

		if (logE.logToLogCat) {
			return android.util.Log.e(tag, msg);
		} else {
			return 0;
		}
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
		if (!logAnything) {
			return -1;
		}

		if (logE.logToHistory) {
			// TODO!
		}

		if (logE.logToLogCat) {
			return android.util.Log.e(tag, msg, tr);
		} else {
			return 0;
		}
	}

	/**
	 * @see android.util.Log
	 * 
	 * @param tr
	 * @return
	 */
	public static String getStackTraceString(Throwable tr) {
		if (!logAnything) {
			return null;
		}

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
		if (!logAnything) {
			return -1;
		}

		if (logI.logToHistory) {
			// TODO!
		}

		if (logI.logToLogCat) {
			return android.util.Log.i(tag, msg);
		} else {
			return 0;
		}
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
		if (!logAnything) {
			return -1;
		}

		if (logI.logToHistory) {
			// TODO!
		}

		if (logI.logToLogCat) {
			return android.util.Log.i(tag, msg, tr);
		} else {
			return 0;
		}
	}

	/**
	 * @see android.util.Log
	 * 
	 * @param tag
	 * @param level
	 * @return
	 */
	public static boolean isLoggable(String tag, int level) {
		if (!logAnything) {
			return false;
		}

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
		if (!logAnything) {
			return -1;
		}

		if (logLine.logToHistory) {
			// TODO!
		}

		if (logLine.logToLogCat) {
			return android.util.Log.println(priority, tag, msg);
		}
		return 0;
	}

	/**
	 * @see android.util.Log
	 * 
	 * @param tag
	 * @param msg
	 * @return
	 */
	public static int v(String tag, String msg) {
		if (!logAnything) {
			return -1;
		}

		if (logV.logToHistory) {
			// TODO!
		}

		if (logV.logToHistory) {
			return android.util.Log.v(tag, msg);
		} else {
			return 0;
		}
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
		if (!logAnything) {
			return -1;
		}

		if (logV.logToHistory) {
			// TODO!
		}

		if (logV.logToLogCat) {
			return android.util.Log.v(tag, msg, tr);
		} else {
			return 0;
		}
	}

	/**
	 * @see android.util.Log
	 * 
	 * @param tag
	 * @param msg
	 * @return
	 */
	public static int w(String tag, String msg) {
		if (!logAnything) {
			return -1;
		}

		if (logW.logToHistory) {
			// TODO!
		}

		if (logW.logToLogCat) {
			return android.util.Log.w(tag, msg);
		} else {
			return 0;
		}
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
		if (!logAnything) {
			return -1;
		}

		if (logW.logToHistory) {
			// TODO!
		}

		if (logW.logToLogCat) {
			return android.util.Log.w(tag, msg, tr);
		} else {
			return 0;
		}
	}

	/**
	 * @see android.util.Log
	 * 
	 * @param tag
	 * @param tr
	 * @return
	 */
	public static int wtf(String tag, Throwable tr) {
		if (!logAnything) {
			return -1;
		}

		if (logWTF.logToHistory) {
			// TODO!
		}

		if (logWTF.logToLogCat) {
			return android.util.Log.wtf(tag, tr);
		} else {
			return 0;
		}
	}

	/**
	 * @see android.util.Log
	 * 
	 * @param tag
	 * @param msg
	 * @return
	 */
	public static int wtf(String tag, String msg) {
		if (!logAnything) {
			return -1;
		}

		if (logWTF.logToHistory) {
			// TODO!
		}

		if (logWTF.logToLogCat) {
			return android.util.Log.wtf(tag, msg);
		} else {
			return 0;
		}
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
		if (!logAnything) {
			return -1;
		}

		if (logWTF.logToHistory) {
			// TODO!
		}

		if (logWTF.logToLogCat) {
			return android.util.Log.wtf(tag, msg, tr);
		} else {
			return 0;
		}
	}

}
