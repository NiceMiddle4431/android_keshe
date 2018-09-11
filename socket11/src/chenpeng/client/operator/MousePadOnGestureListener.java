package chenpeng.client.operator;

import chenpeng.client.socket.CmdClientSocket;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class MousePadOnGestureListener extends GestureDetector.SimpleOnGestureListener{

	String ip;
	int port;
	ShowNonUiUpdateCmdHandler showNonUiUpdateCmdHandler;
	//Ë«»÷µã»÷×ó¼ü
	public MousePadOnGestureListener(String ip, int port,
			ShowNonUiUpdateCmdHandler showNonUiUpdateCmdHandler) {
		super();
		this.ip = ip;
		this.port = port;
		this.showNonUiUpdateCmdHandler = showNonUiUpdateCmdHandler;
	}
	
	//ÊÖÖ¸»¬¶¯
	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		
		Log.d("test:MouseMove", ""+distanceX);
		Log.d("test:MouseMove", ""+distanceY);
		String cmd = "mov:"+(int)-distanceX+","+(int)-distanceY;
		CmdClientSocket cmdClientSocket = new CmdClientSocket(ip, port, showNonUiUpdateCmdHandler);
		cmdClientSocket.work(cmd);
		return true;
	}

	//Ë«»÷µã»÷×ó¼ü
	@Override
	public boolean onDoubleTap(MotionEvent e) {
		// TODO Auto-generated method stub
		String cmd = "clk:left";
		CmdClientSocket cmdClientSocket = new CmdClientSocket(ip, port, showNonUiUpdateCmdHandler);
		cmdClientSocket.work(cmd);
		Log.d("Ë«»÷", e.toString());
		return true;
		
	}
	
}
