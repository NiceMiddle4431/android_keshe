package chenpeng.client.view;

import java.util.Random;

import org.w3c.dom.Text;

import chenpeng.client.app.R;
import chenpeng.client.operator.MousePadOnGestureListener;
import chenpeng.client.operator.ShowNonUiUpdateCmdHandler;
import chenpeng.client.socket.CmdClientSocket;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;

public class MouseMoveDialog {

	private Context context;
	String ip;
	int port;
	ShowNonUiUpdateCmdHandler showNonUiUpdateCmdHandler;
	public MouseMoveDialog(Context context, String ip, int port,
			ShowNonUiUpdateCmdHandler showNonUiUpdateCmdHandler) {
		super();
		this.context = context;
		this.ip = ip;
		this.port = port;
		this.showNonUiUpdateCmdHandler = showNonUiUpdateCmdHandler;
	}
	public View Show(){
		AlertDialog.Builder builder = new Builder(context);
		builder.setTitle("鼠标控制");
		
		View view = LayoutInflater.from(context).inflate(R.layout.mouse_move, null,false);
		TextView mousePad = (TextView) view.findViewById(R.id.textView1);
		final GestureDetector gestureDetector = new GestureDetector(context, new MousePadOnGestureListener(ip,port,showNonUiUpdateCmdHandler));
		mousePad.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				gestureDetector.onTouchEvent(event);
				Log.d("鼠标事件设置成功","");
				return true;
			}
		});
		Button left = (Button) view.findViewById(R.id.button1);
		left.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CmdClientSocket cmdClientSocket = new CmdClientSocket(ip, port, showNonUiUpdateCmdHandler);
				String cmd = "clk:left";
				cmdClientSocket.work(cmd);
			}
		});
		Button right = (Button) view.findViewById(R.id.button3);
		right.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CmdClientSocket cmdClientSocket = new CmdClientSocket(ip, port, showNonUiUpdateCmdHandler);
				String cmd = "clk:right";
				cmdClientSocket.work(cmd);
			}
		});
		Button up = (Button) view.findViewById(R.id.button2);
		up.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CmdClientSocket cmdClientSocket = new CmdClientSocket(ip, port, showNonUiUpdateCmdHandler);
				Random random = new Random();
				String cmd = "rol:"+(0-random.nextInt(5)-1);
				cmdClientSocket.work(cmd);
			}
		});
		Button down = (Button) view.findViewById(R.id.button4);
		down.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CmdClientSocket cmdClientSocket = new CmdClientSocket(ip, port, showNonUiUpdateCmdHandler);
				Random random = new Random();
				String cmd = "rol:"+(random.nextInt(5)+1);
				cmdClientSocket.work(cmd);
			}
		});
		builder.setView(view);
		builder.show();
		return view;
	}
}
