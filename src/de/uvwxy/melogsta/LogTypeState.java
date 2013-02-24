package de.uvwxy.melogsta;

import java.util.LinkedList;

class LogTypeState {
	// default visibility: only in package
	boolean logToHistory = true;
	boolean logToLogCat = true;
	boolean throwNotification = true;
	int ownNotificationID;
	static int sharedNotificationID;
	LinkedList<LogHistoryItem> logHistory = new LinkedList<LogHistoryItem>();

}
