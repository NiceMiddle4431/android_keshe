package chenpeng.server.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import chenpeng.server.operator.Clk;
import chenpeng.server.operator.Cmd;
import chenpeng.server.operator.Cps;
import chenpeng.server.operator.DIR;
import chenpeng.server.operator.Key;
import chenpeng.server.operator.Mov;
import chenpeng.server.operator.Mva;
import chenpeng.server.operator.Open;
import chenpeng.server.operator.Rol;



public class CmdServerSocketThread extends Thread {


	int port = 8019;
	static int connect_count = 0;
	private BufferedReader bufferedReader;
	private ArrayList<String> msgBackList = new ArrayList<String>();
	boolean isFalse = true;
	Socket socket;
	ServerSocket serverSocket;
	public CmdServerSocketThread(int port) {
		super();
		this.port = port;
	}


	public CmdServerSocketThread() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			serverSocket = new ServerSocket(port);
			try {
				work(serverSocket);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void work(ServerSocket serverSocket) throws Exception
	{		

		while(true){
			if(serverSocket.isClosed()){
				System.out.println("The erver is closef for ever.Please create another CmdServerSocket");
			}
			System.out.println("waiting client to connect...");

			socket = serverSocket.accept();
			System.out.println("Client connected from:"+socket.getRemoteSocketAddress().toString());

			ArrayList<String> readSocker = readSocker(socket);
			split(readSocker);


			writeBackMsg(socket);

			//			BufferedOutputStream os = new BufferedOutputStream(socket.getOutputStream());
			//			OutputStreamWriter writer = new OutputStreamWriter(os, "gb2312");
			//			writer.write("Hello, 我是服务端ServerSocket!你是第"+(++connect_count)+"个连接");
			//			writer.flush();
			//			writer.close();
			socket.close();
			System.out.println("当前Socket服务结束");
		}

	}


	//分解命令
	private void split(ArrayList<String> readSocker) throws Exception {
		// TODO Auto-generated method stub
		for (int i = 0; i < readSocker.size(); i++) {
			String cmd = readSocker.get(i);
			cmd.toLowerCase();
			System.out.println(cmd);
			int indexOf = cmd.indexOf(":");
			String cmdHead = cmd.substring(0,indexOf);
			String cmdBody = cmd.substring(indexOf+1);
			System.out.println(cmdHead);
			System.out.println(cmdBody);
			if(cmdHead.equals("dir"))
			{
				DIR dir = new DIR();
				msgBackList = dir.exeDir(cmdBody);
			}else if(cmdHead.equals("open")){
				if(new Open(cmdBody).exeOpen())
				{
					msgBackList.clear();
					msgBackList.add("文件打开成功\n");
				}
				else{
					msgBackList.clear();
					msgBackList.add("文件无法打开\n");
					isFalse = false;
				}
			}else if(cmdHead.equals("key")){
				Key key = new Key(cmdBody);
				msgBackList.clear();
				msgBackList = key.exeKey();
			}else if(cmdHead.equals("mov")){
				msgBackList.clear();
				msgBackList = new Mov(cmdBody).exeMov();;
			}else if(cmdHead.equals("mva")){
				msgBackList.clear();
				msgBackList = new Mva(cmdBody).exeMva();
			}else if(cmdHead.equals("clk")){
				msgBackList.clear();
				msgBackList = new Clk(cmdBody).exeCle();
			}else if(cmdHead.equals("cmd")){
				msgBackList.clear();
				msgBackList = new Cmd(cmdBody).exeCmd();
			}else if(cmdHead.equals("rol")){
				msgBackList.clear();
				msgBackList = new Rol(cmdBody).exeRow();
			}else if(cmdHead.equals("cps")){
				msgBackList.clear();
				msgBackList = new Cps(cmdBody).exeCps();
			}else if(cmdHead.equals("slp")){
				msgBackList.clear();
				int sleep = Integer.valueOf(cmdBody);
				Thread.sleep(sleep);
				msgBackList.add(cmdBody);
			}else if(cmdHead.equals("for")){
				msgBackList.clear();
				
			}
			else
			{
				FileFalse();
				isFalse = false;
			}

		}
	}


	private void FileFalse() {
		// TODO Auto-generated method stub
		msgBackList.clear();
		msgBackList.add("连接失败\n");
	}
	//返回消息
	private void writeBackMsg(Socket socket) throws IOException {
		// TODO Auto-generated method stub
		OutputStream outputStream = socket.getOutputStream();
		OutputStreamWriter writer = new OutputStreamWriter(outputStream,"gbk");
		int lineNum = msgBackList.size();
		writer.write(""+lineNum+"\n");
		writer.flush();
		if(isFalse)
		{
			writer.write("ok\n");
			writer.flush();
			for (int i = 0; i < msgBackList.size(); i++) {
				writer.write(msgBackList.get(i)+"\n");
				writer.flush();			
			}
		}
		else
		{
			writer.write("error\n");
			writer.flush();
			for (int i = 0; i < msgBackList.size(); i++) {
				writer.write(msgBackList.get(i)+"\n");
				writer.flush();			
			}
		}

	}


	//读取命令
	private ArrayList<String> readSocker(Socket socket) throws IOException {
		// TODO Auto-generated method stub


		InputStream inputStream = socket.getInputStream();
		InputStreamReader reader = new InputStreamReader(inputStream,"gbk");
		bufferedReader = new BufferedReader(reader);
		String lineNumStr = bufferedReader.readLine();//读取约定的行数
		int lineNum = Integer.parseInt(lineNumStr);

		ArrayList<String> msgList = new ArrayList<String>();
		for (int i = 0; i < lineNum; i++) {
			String str = bufferedReader.readLine();
			msgList.add(str);
		}
		return msgList;
	}



}
