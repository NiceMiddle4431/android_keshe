package chenpeng.server.operator;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.util.ArrayList;

public class Cps {

	String cmdBody;

	public Cps(String cmdBody) {
		super();
		this.cmdBody = cmdBody;
	}

	public ArrayList<String> exeCps(){
		ArrayList<String> list = new ArrayList<String>();
		
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();//获取系统剪切板
		Transferable text = new StringSelection(cmdBody);
		clipboard.setContents(text, null);
		try {
			new Key("vk_ctrl+vk_v").exeKey();
			list.add(cmdBody);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			list.add(e.toString());
		}
		
		return list;
	}
}
