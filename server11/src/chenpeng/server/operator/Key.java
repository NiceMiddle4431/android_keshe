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
		int indexOf = cmdBody.indexOf(",");//�ж��Ƿ񵥰���������ϼ�
		if(indexOf>0){//��ϼ�		indexOf���ص�һ�γ�������ַ���λ��,û�з���-1
			comboKeyPress(cmdBody); 			//����B
		}else{
			int indexOf2 = cmdBody.indexOf("+");
			if(indexOf2>=0){//��ϼ�
				simpleComboKeyPress(cmdBody);	//����C
			}else{
				singleKeyPress(cmdBody);//������	//����A
			}
		}
		return msgBackList;
	}
	//����B
	private void comboKeyPress(String cmdBody) {
		// TODO Auto-generated method stub
		//������ͬ��,�ֽ�Ϊ����˳����ͷ�˳��Ȼ��ͨ��+�ֽ�ɵ���˳��
		robot.delay(3000);
		String[] split = cmdBody.split(",");
		String[] splitHead = split[0].split("\\+");//����˳��
		String[] splitTail = split[1].split("\\+");//�ͷ�˳��
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
	//����C
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
	//����A
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
