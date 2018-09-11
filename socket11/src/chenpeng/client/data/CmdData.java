package chenpeng.client.data;

public class CmdData {
	
	String title;
	String cmd;
	public CmdData(String title, String cmd) {
		super();
		this.title = title;
		this.cmd = cmd;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	
}
