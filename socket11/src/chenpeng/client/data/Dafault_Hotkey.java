package chenpeng.client.data;

import java.util.ArrayList;

public class Dafault_Hotkey {
	
	ArrayList<HotkeyData> list = new ArrayList<HotkeyData>();
	
	public Dafault_Hotkey(){
		HotkeyData altTab = new HotkeyData("�л�����", "key:vk_alt+vk_tab");
		HotkeyData altf4 = new HotkeyData("�˳�����", "key:vk_alt+vk_f4");
		HotkeyData alt_n = new HotkeyData("��С��", "key:vk_alt+vk_space+vk_n");
		HotkeyData alt_x = new HotkeyData("���", "key:vk_alt+vk_space+vk_x");
		list.add(altTab);
		list.add(altf4);
		list.add(alt_n);
		list.add(alt_x);
	}

	public ArrayList<HotkeyData> getList() {
		return list;
	}

	public void setList(ArrayList<HotkeyData> list) {
		this.list = list;
	}
	
}
