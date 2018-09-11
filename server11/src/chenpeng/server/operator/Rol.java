package chenpeng.server.operator;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.ArrayList;

public class Rol {

	int value;

	public Rol(String value) {
		super();
		this.value = Integer.valueOf(value);
	}
	public ArrayList<String> exeRow(){
		ArrayList<String> list = new ArrayList<String>();
		
		try {
			Robot robot = new Robot();
			robot.delay(2500);
			robot.mouseWheel(value);
			list.add(""+value);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			list.add(""+e.toString());
		}
		return list;
	}
}
