package chenpeng.server.app;

import chenpeng.server.socket.CmdServerSocketThread;


public class ServerSocketApp {

	public final static int port = 8019;
	public static boolean isFalse = true;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		while(true)
		{
			try {
				if(isFalse)
				{
					isFalse = false;
					CmdServerSocketThread cmdServerSocketThread = new CmdServerSocketThread(port);
					cmdServerSocketThread.start();
					cmdServerSocketThread.join();
					System.out.println("The CmdServerSocketThread is finished!!!");
				}
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				isFalse = true;
			}
		}
	}

}