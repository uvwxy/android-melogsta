package de.uvwxy.melogsta;

public class Log {
	private static boolean on = true;

	private static boolean logCat_println_On = true;
	
	private static boolean logCat_D_On = true;
	private static boolean logCat_E_On = true;
	private static boolean logCat_I_On = true;
	private static boolean logCat_V_On = true;
	private static boolean logCat_W_On = true;
	private static boolean logCat_WTF_On = true;

	private static boolean notifications_D_On = true;
	private static boolean notifications_E_On = true;
	private static boolean notifications_I_On = true;
	private static boolean notifications_V_On = true;
	private static boolean notifications_W_On = true;
	private static boolean notifications_WTF_On = true;

	/**
	 * @see android.util.Log
	 * 
	 * @param tag
	 * @param msg
	 * @return
	 */
	public static int d(String tag, String msg) {
		if (notifications_D_On) {
			// TODO!
		}

		if (logCat_D_On) {
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
		if (notifications_D_On) {
			// TODO!
		}

		if (logCat_D_On) {
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
		if (notifications_E_On) {
			// TODO!
		}

		if (logCat_E_On) {
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
		if (notifications_E_On) {
			// TODO!
		}

		if (logCat_E_On) {
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
		if (notifications_I_On) {
			// TODO!
		}

		if (logCat_I_On) {
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
		if (notifications_I_On) {
			// TODO!
		}

		if (logCat_I_On) {
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

		if (logCat_println_On) {
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
		if (notifications_V_On) {
			// TODO!
		}

		if (logCat_V_On) {
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
		if (notifications_V_On) {
			// TODO!
		}

		if (logCat_V_On) {
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
		if (notifications_W_On) {
			// TODO!
		}

		if (logCat_W_On) {
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
		if (notifications_W_On) {
			// TODO!
		}

		if (logCat_W_On) {
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
		if (notifications_WTF_On) {
			// TODO!
		}

		if (logCat_WTF_On) {
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
		if (notifications_WTF_On) {
			// TODO!
		}

		if (logCat_WTF_On) {
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
		if (notifications_WTF_On) {
			// TODO!
		}

		if (logCat_WTF_On) {
			return android.util.Log.wtf(tag, msg, tr);
		} else {
			return 0;
		}
	}

}
