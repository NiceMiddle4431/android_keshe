package chenpeng.server.operator;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.util.ArrayList;

public class Mva {
	
	double x;
	double y;
	Dimension dimension;
	String cmdBody;
	public Mva(String cmdBody) throws AWTException{
		this.cmdBody = cmdBody;
		int indexOf = cmdBody.indexOf(",");
		x = Double.parseDouble(cmdBody.substring(0, indexOf));
		y = Double.parseDouble(cmdBody.substring(indexOf+1, cmdBody.length()));
	}
	public ArrayList<String> exeMva() throws AWTException{
		ArrayList<String> list = new ArrayList<String>();
		if(x>=0&&x<=1&&y>=0&&y<=1){
			dimension = Toolkit.getDefaultToolkit().getScreenSize();;
			double width = dimension.getWidth();
			double height = dimension.getHeight();
			System.out.println("test:width:"+width);
			System.out.println("test:height:"+height);
			Robot robot = new Robot();
			robot.mouseMove((int)(width*x), (int)(height*y));
			list.add(cmdBody);
		}
		else{
			list = new Mov(cmdBody).exeMov();
		}
		
		return list;
	}
}
