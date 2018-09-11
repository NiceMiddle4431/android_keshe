package chenpeng.client.operator;

import java.util.ArrayList;

import chenpeng.client.socket.CmdClientSocket;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.NoCopySpan.Concrete;
import android.widget.Toast;

public class ShowNonUiUpdateCmdHandler extends Handler {

	Context context;
	
	

	public ShowNonUiUpdateCmdHandler(Context context) {
		super();
		this.context = context;
	}

	@Override
	public void handleMessage(Message msg) {
		// TODO Auto-generated method stub
		Bundle bundle = msg.getData();
		ArrayList<String> msglist = bundle.getStringArrayList(CmdClientSocket.KEY_SEVER_ACK);
		Toast.makeText(context, msglist.get(0), Toast.LENGTH_SHORT).show();
	}
}
