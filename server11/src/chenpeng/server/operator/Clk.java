package chenpeng.server.operator;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Clk {

	String cmdBody;
	Robot robot;
	ArrayList<String> list = new ArrayList<String>();
	public Clk(String cmdBody) throws AWTException{
		this.cmdBody = cmdBody.toLowerCase();
		robot = new Robot();
	}
	public ArrayList<String> exeCle(){
		if(cmdBody.toLowerCase().equals("left")){
			left();
		}else if(cmdBody.equals("right")){
			right();
		}else if(cmdBody.equals("leftpress")){
			leftPress();
		}else if(cmdBody.equals("rightpress")){
			rightPress();
		}else if(cmdBody.equals("leftrelease")){
			leftRelease();
		}else if(cmdBody.equals("rightrelease")){
			rightRelease();
		}else if(cmdBody.equals("value")){
			value();
		}
		list.add(cmdBody);
		return list;
	}
	//���ֻ���
	private void value() {
		// TODO Auto-generated method stub
		
	}
	//������
	private void left(){
		robot.setAutoDelay(3000);
		robot.mousePress(KeyEvent.BUTTON1_MASK);
		robot.setAutoDelay(500);
		robot.mouseRelease(KeyEvent.BUTTON1_MASK);
	}
	//����Ҽ�
	private void right(){
		robot.mousePress(KeyEvent.BUTTON3_MASK);
		robot.setAutoDelay(500);
		robot.mouseRelease(KeyEvent.BUTTON3_MASK);
	}
	//��ס������ͷ�
	private void leftPress(){
		robot.mousePress(KeyEvent.BUTTON1_MASK);
	}
	//�ͷ����
	private void leftRelease(){
		robot.mouseRelease(KeyEvent.BUTTON1_MASK);
	}
	//�ͷ��Ҽ����ͷ�
	private void rightRelease(){
		robot.mousePress(KeyEvent.BUTTON3_MASK);
	}
	//�Ҽ��ͷ�
	private void rightPress(){
		robot.mouseRelease(KeyEvent.BUTTON3_MASK);
	}
}
