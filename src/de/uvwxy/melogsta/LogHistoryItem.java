package de.uvwxy.melogsta;

import java.io.Serializable;

class LogHistoryItem implements Serializable {
	private static final long serialVersionUID = 4591651302918734176L;

	private int priority;
	private String tag;
	private String msg;
	private long timestamp;
	private Throwable tr;

	public int getPriority() {
		return priority;
	}

	public String getTag() {
		return tag;
	}

	public String getMsg() {
		return msg;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public Throwable getTr() {
		return tr;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public void setTr(Throwable tr) {
		this.tr = tr;
	}

	public LogHistoryItem(int priority, String tag, String msg, long timestamp, Throwable tr) {
		super();
		this.priority = priority;
		this.tag = tag;
		this.msg = msg;
		this.timestamp = timestamp;
		this.tr = tr;
	}
}
