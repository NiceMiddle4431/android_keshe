package chenpeng.client.operator;

import java.util.ArrayList;

import chenpeng.client.app.MainActivity;
import chenpeng.client.data.NetFileData;
import chenpeng.client.socket.CmdClientSocket;
import chenpeng.client.view.NetFileListAdapter;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

public class ShowRemoteFileHandler extends Handler {

	private Context context;
	private ListView listView;
	ArrayList<NetFileData> fileList;
	NetFileListAdapter adapter;

	public ShowRemoteFileHandler(Context context, ListView listView) {
		super();
		this.context = context;
		this.listView = listView;
	}

	@Override
	public void handleMessage(Message msg) {
		// TODO Auto-generated method stub
		super.handleMessage(msg);
		//获取数据更新UI
		Bundle bundle = msg.getData();
		
		if(msg.arg2 == CmdClientSocket.SERVER_MSG_OK)
		{
			ArrayList<String> fileInfoList = bundle.getStringArrayList(CmdClientSocket.KEY_SEVER_ACK);
			fileList = new ArrayList<NetFileData>();

			for(int i = 0;i<fileInfoList.size();i++)
			{
				NetFileData netFileData = new NetFileData(fileInfoList.get(i));
				fileList.add(netFileData);
			}
			adapter = new NetFileListAdapter(context, fileList);
			Log.e("testAdapter", "adapter设置");
			listView.setAdapter(adapter);
			Log.e("testAdapter", "adapter设置成功");
		}
		else if(msg.arg2 == CmdClientSocket.SERVER_MSG_ERROR)
		{
			Toast.makeText(context, "服务器连接不上", Toast.LENGTH_SHORT).show();
		}
		
	}

}













