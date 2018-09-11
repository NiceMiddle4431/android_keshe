package chenpeng.server.operator;

import java.io.IOException;
import java.util.ArrayList;

public class Cmd {

	String cmdBody;

	public Cmd(String cmdBody) {
		super();
		this.cmdBody = cmdBody;
	}
	
	public ArrayList<String> exeCmd(){
		ArrayList<String> list = new ArrayList<String>();
		
		try {
			Runtime.getRuntime().exec("cmd /c start "+cmdBody);
			list.add(cmdBody);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			list.add(e.toString());
		}
		
		
		return list;
	}
}
