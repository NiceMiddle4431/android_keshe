package chenpeng.server.operator;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.MouseInfo;
import java.util.ArrayList;
public class Mov {

	double x;
	double y;
	String cmdBody;
	public Mov(String cmdBody){
		this.cmdBody = cmdBody;
		int indexOf = cmdBody.indexOf(",");
		x = Double.parseDouble(cmdBody.substring(0, indexOf));
		y = Double.parseDouble(cmdBody.substring(indexOf+1, cmdBody.length()));
	}
	
	public ArrayList<String> exeMov() throws AWTException{
		ArrayList<String> list = new ArrayList<String>();
		//屏幕大小
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		double width = dimension.getWidth();
		double height = dimension.getHeight();
		//鼠标当前位置
		
		Point point = MouseInfo.getPointerInfo().getLocation();
		
		int pointX = point.x;
		int pointY = point.y;
		System.out.println("test:pointX:"+pointX);
		System.out.println("test:pointY:"+pointY);
		//鼠标移动
		Robot robot = new Robot();
		if((int)(pointX+x) <= width&&(int)(pointY+y) <= height){
			System.out.println("test:pointX+x:"+(int)(pointX+x));
			System.out.println("test:pointY+y:"+(int)(pointY+y));
			robot.mouseMove((int)(pointX+x), (int)(pointY+y));
		}else if((int)(pointX+x)>width){
			robot.mouseMove((int)width, (int)(pointY+y));
		}else if((int)(pointY+y)>height){
			robot.mouseMove((int)(pointX+x), (int)height);
		}else{
			robot.mouseMove((int)width, (int)height);
		}
		list.add(cmdBody);
		return list;
	}
}
