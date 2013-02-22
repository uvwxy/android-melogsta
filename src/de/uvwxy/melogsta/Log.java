package de.uvwxy.melogsta;

public class Log {
	private static boolean on = true;
	private static boolean logCatOn = true;
	private static boolean notificationsOn = true;

	public static boolean isOn() {
		return on;
	}

	public static boolean isLogCatOn() {
		return logCatOn;
	}

	public static boolean isNotificationsOn() {
		return notificationsOn;
	}

	public static void setOn(boolean on) {
		Log.on = on;
	}

	public static void setLogCatOn(boolean logCatOn) {
		Log.logCatOn = logCatOn;
	}

	public static void setNotificationsOn(boolean notificationsOn) {
		Log.notificationsOn = notificationsOn;
	}

	/**
	 * @see android.util.Log
	 * 
	 * @param tag
	 * @param msg
	 * @return
	 */
	public static int d(String tag, String msg) {
		if (notificationsOn) {
			// TODO!
		}

		if (logCatOn) {
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
		if (notificationsOn) {
			// TODO!
		}

		if (logCatOn) {
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
		if (notificationsOn) {
			// TODO!
		}

		if (logCatOn) {
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
		if (notificationsOn) {
			// TODO!
		}

		if (logCatOn) {
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
		if (notificationsOn) {
			// TODO!
		}

		if (logCatOn) {
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
		if (notificationsOn) {
			// TODO!
		}

		if (logCatOn) {
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

		if (logCatOn) {
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
		if (notificationsOn) {
			// TODO!
		}

		if (logCatOn) {
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
		if (notificationsOn) {
			// TODO!
		}

		if (logCatOn) {
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
		if (notificationsOn) {
			// TODO!
		}

		if (logCatOn) {
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
		if (notificationsOn) {
			// TODO!
		}

		if (logCatOn) {
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
		if (notificationsOn) {
			// TODO!
		}

		if (logCatOn) {
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
		if (notificationsOn) {
			// TODO!
		}

		if (logCatOn) {
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
		if (notificationsOn) {
			// TODO!
		}

		if (logCatOn) {
			return android.util.Log.wtf(tag, msg, tr);
		} else {
			return 0;
		}
	}

}
