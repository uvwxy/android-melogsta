package de.uvwxy.melogsta;

import java.util.LinkedList;

public class LogTypeState {
	// default visibility: only in package
	boolean logToHistory = true;
	boolean logToLogCat = true;
	LinkedList<LogHistoryItem> logHistory = new LinkedList<LogHistoryItem>();

}
