package chenpeng.server.operator;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class Open {

	String filePath;
	Desktop desktop = Desktop.getDesktop();
	boolean isFalse;
	public Open(String filePath) {
		super();
		this.filePath = filePath;
	}
	public Open(){
	}

	public boolean exeOpen()
	{

		File file = new File(filePath);
		try {
			desktop.open(file);
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
