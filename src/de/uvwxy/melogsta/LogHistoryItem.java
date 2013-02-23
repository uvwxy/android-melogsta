package de.uvwxy.melogsta;

public class LogHistoryItem {
	private int priority;
	private String tag;
	private String msg;
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

	public void setTr(Throwable tr) {
		this.tr = tr;
	}

	public LogHistoryItem(int priority, String tag, String msg, Throwable tr) {
		super();
		this.priority = priority;
		this.tag = tag;
		this.msg = msg;
		this.tr = tr;
	}
}
