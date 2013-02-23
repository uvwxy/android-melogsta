package de.uvwxy.melogsta;

import java.util.LinkedList;

public class LogTypeState {
	// default visibility: only in package
	boolean logToHistory = true;
	boolean logToLogCat = true;
	boolean throwNotification = true;
	boolean notificationHasBeenThrown;
	LinkedList<LogHistoryItem> logHistory = new LinkedList<LogHistoryItem>();

}
