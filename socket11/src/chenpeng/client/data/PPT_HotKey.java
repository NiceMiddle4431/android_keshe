package chenpeng.client.data;

import java.util.ArrayList;

public class PPT_HotKey extends Dafault_Hotkey{

	public PPT_HotKey(){
		HotkeyData esc = new HotkeyData("ESC", "key:vk_escape");
		HotkeyData up = new HotkeyData("上一页", "key:vk_page_up");
		HotkeyData down = new HotkeyData("下一页", "key:vk_page_down");
		HotkeyData altSB = new HotkeyData("从头放映", "key:vk_alt+vk_s+vk_b");
		HotkeyData altSC = new HotkeyData("当前放映", "key:vk_alt+vk_s+vk_c");
		list.add(esc);
		list.add(up);
		list.add(down);
		list.add(altSB);
		list.add(altSC);
	}
	public ArrayList<HotkeyData> getList() {
		return list;
	}
	public void ArrayList(ArrayList<HotkeyData> list) {
		this.list = list;
	}	
}
