package chenpeng.client.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

import chenpeng.client.app.MainActivity;
import chenpeng.client.operator.ShowRemoteFileHandler;

import android.R.integer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class CmdClientSocket {

	
	public static int SERVER_MSG_OK = 0;
	public static int SERVER_MSG_ERROR = 1;
	private int msgType;
	
	private String ip;
	private int port;
	private Handler handler;
	public static final String KEY_SEVER_ACK = "KEY_SERVER_ACK";
	Socket socket;
	String cmd;
	private ArrayList<String> getList = new ArrayList<String>();
	private ArrayList<String> cmdList = new ArrayList<String>();


	public CmdClientSocket(String ip, int port, Handler handler) {
		super();
		this.ip = ip;
		this.port = port;
		this.handler = handler;
	}
	
	public void work(final String cmd)
	{
		this.cmd = cmd;
		String[] split = cmd.split("\n");
		for(int i = 0;i<split.length;i++){
			cmdList.add(split[i]);
		}
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				doCmdTask(cmdList);
			}
		}).start();
	}
	//返回行第一行是否为ok
	private void doCmdTask(ArrayList<String> cmdList)
	{
		ArrayList<String> msgList = new ArrayList<String>();
		try {
			connect();
			writerCmd(cmdList);
			msgList = readSocketMsg();
			if(msgList.get(0).equals("ok")){
				msgType = SERVER_MSG_OK;
			}
			else{
				msgType = SERVER_MSG_ERROR;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			msgType = SERVER_MSG_ERROR;
			e.printStackTrace();
			msgList.add(e.toString());
		}
		Bundle bundle = new Bundle();
		bundle.putStringArrayList(KEY_SEVER_ACK, msgList);
		Message message = handler.obtainMessage();
		message.arg2 = msgType;
		message.setData(bundle);
		handler.sendMessage(message);
		close();
	}
	
	private void close() {
		// TODO Auto-generated method stub
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private ArrayList<String> readSocketMsg() throws IOException {
		// TODO Auto-generated method stub
		ArrayList<String> msgList = new ArrayList<String>();
		
		InputStream inputStream = socket.getInputStream();
		InputStreamReader reader = new InputStreamReader(inputStream,"gbk");
		BufferedReader bufferedReader = new BufferedReader(reader);
		int lineNum = Integer.parseInt(bufferedReader.readLine()) + 1;
		for(int i = 0;i<lineNum;i++)
		{
			msgList.add(bufferedReader.readLine());
		}
		
		return msgList;
	}

	private void writerCmd(ArrayList<String> cmdList) throws IOException {
		// TODO Auto-generated method stub
		OutputStream outputStream = socket.getOutputStream();
		OutputStreamWriter writer = new OutputStreamWriter(outputStream,"gbk");
		writer.write(cmdList.size()+"\n");
		writer.flush();
		for(int i = 0;i<cmdList.size();i++){
			writer.write(cmdList.get(i)+"\n");
			writer.flush();
		}

	}

	private void connect()throws IOException {
		// TODO Auto-generated method stub
		InetSocketAddress address = new InetSocketAddress(ip, port);
		socket = new Socket();
		socket.connect(address,2000);//连接超时
		socket.setSoTimeout(5000);//获取服务器数据超时
	}
	
	public int getMsgType() {
		return msgType;
	}

	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}
}
