package chenpeng.client.data;

import java.util.ArrayList;

public class Movie_Hotkey extends Dafault_Hotkey {
	
	public Movie_Hotkey(){
		
		HotkeyData space = new HotkeyData("��ͣ", "key:vk_space");
		HotkeyData right = new HotkeyData("���", "key:vk_page_right");
		HotkeyData left = new HotkeyData("����", "key:vk_page_left");
		HotkeyData up = new HotkeyData("����+", "key:vk_page_up");
		HotkeyData down = new HotkeyData("", "key:vk_page_down");
		list.add(space);
		list.add(right);
		list.add(left);
		list.add(up);
		list.add(down);
	}
	public ArrayList<HotkeyData> getList() {
		return list;
	}
	public void ArrayList(ArrayList<HotkeyData> list) {
		this.list = list;
	}	
	
}
