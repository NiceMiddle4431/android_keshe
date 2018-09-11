package chenpeng.server.operator;

import java.awt.Robot;
import java.util.ArrayList;


public class Key {

	String cmdBody;
	private Robot robot;
	private ArrayList<String> msgBackList;
	public Key(String cmdBody) throws Exception{
		// TODO Auto-generated constructor stub
		msgBackList = new ArrayList<String>();
		this.cmdBody = cmdBody;
		msgBackList.add("key:"+cmdBody);
		robot = new Robot();		
	}
	public ArrayList<String> exeKey(){
		int indexOf = cmdBody.indexOf(",");//判断是否单按键或者组合键
		if(indexOf>0){//组合键		indexOf返回第一次出现这个字符的位置,没有返回-1
			comboKeyPress(cmdBody); 			//类型B
		}else{
			int indexOf2 = cmdBody.indexOf("+");
			if(indexOf2>=0){//组合键
				simpleComboKeyPress(cmdBody);	//类型C
			}else{
				singleKeyPress(cmdBody);//单按键	//类型A
			}
		}
		return msgBackList;
	}
	//类型B
	private void comboKeyPress(String cmdBody) {
		// TODO Auto-generated method stub
		//将命令同感,分解为按下顺序和释放顺序，然后通过+分解成单个顺序；
		robot.delay(3000);
		String[] split = cmdBody.split(",");
		String[] splitHead = split[0].split("\\+");//按下顺序
		String[] splitTail = split[1].split("\\+");//释放顺序
		for(int i = 0;i<splitHead.length;i++){
			int visualKey = VisualKeyMap.getVisualKey(splitHead[i]);
			robot.keyPress(visualKey);
			robot.setAutoDelay(500);
			System.out.println("test:KeyPress:"+visualKey);
		}
		for(int i = 0;i<splitTail.length;i++){
			int visualKey = VisualKeyMap.getVisualKey(splitTail[i]);
			robot.keyRelease(visualKey);
			robot.setAutoDelay(500);
			System.out.println("test:KeyRelease:"+visualKey);
		}
	}
	//类型C
	private void simpleComboKeyPress(String cmdBody) {
		// TODO Auto-generated method stub
		robot.delay(3000);
		String[] split = cmdBody.split("\\+");
		for(int i = 0;i<split.length;i++){
			int visualKey = VisualKeyMap.getVisualKey(split[i]);
			robot.keyPress(visualKey);
			robot.setAutoDelay(500);
			System.out.println("test:KeyPress:"+visualKey);
		}
		for(int i = split.length-1;i>=0;i--){
			int visualKey = VisualKeyMap.getVisualKey(split[i]);
			robot.keyRelease(visualKey);
			robot.setAutoDelay(500);
			System.out.println("test:KeyRelease:"+visualKey);
		}
	}
	//类型A
	private void singleKeyPress(String cmdBody) {
		// TODO Auto-generated method stub
		robot.delay(3000);
		int visualKey = VisualKeyMap.getVisualKey(cmdBody);
		robot.keyPress(visualKey);
		robot.setAutoDelay(500);
		robot.keyRelease(visualKey);
		robot.setAutoDelay(500);
		System.out.println("test:"+visualKey);
	}

}
