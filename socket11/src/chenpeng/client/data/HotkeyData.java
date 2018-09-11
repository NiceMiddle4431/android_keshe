package chenpeng.client.data;

import java.util.ArrayList;
import java.util.List;

public class HotkeyData {

	List<HotkeyData> list = new ArrayList<HotkeyData>();
	private String hotKeyName = "";
	private String hotKeyCmd = "";
	public HotkeyData(String hotKeyName, String hotKeyCmd) {
		super();
		this.hotKeyName = hotKeyName;
		this.hotKeyCmd = hotKeyCmd;
	}
	public String getHotKeyName() {
		return hotKeyName;
	}
	public void setHotKeyName(String hotKeyName) {
		this.hotKeyName = hotKeyName;
	}
	public String getHotKeyCmd() {
		return hotKeyCmd;
	}
	public void setHotKeyCmd(String hotKeyCmd) {
		this.hotKeyCmd = hotKeyCmd;
	}
}
