package chenpeng.client.operator;

import java.util.ArrayList;

import chenpeng.client.data.Dafault_Hotkey;
import chenpeng.client.data.HotkeyData;
import chenpeng.client.data.Movie_Hotkey;
import chenpeng.client.data.NetFileData;
import chenpeng.client.data.PPT_HotKey;

public class HotKeyGenerator {

	static ArrayList<HotkeyData> getHotkeyList;
	
	public HotKeyGenerator(NetFileData fileData){

		int indexOf = fileData.getFileName().indexOf(".");
		String fileName = fileData.getFileName().substring(indexOf+1);
		
		if(fileName.equals("ppt")||fileName.equals("pptx")){
			getHotkeyList = new PPT_HotKey().getList();
		}else if(fileName.equals("mp4")||fileName.equals("mov")){
			getHotkeyList = new Movie_Hotkey().getList();
		}else{
			getHotkeyList = new Dafault_Hotkey().getList();
		}
	}

	public ArrayList<HotkeyData> getGetHotkeyList() {
		return getHotkeyList;
	}
}
