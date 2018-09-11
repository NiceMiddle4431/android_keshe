package chenpeng.server.operator;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DIR {

	private ArrayList<String> msgBackList = new ArrayList<String>();

	public DIR() {
		super();
	}

	public ArrayList<String> exeDir(String cmdBody) {
		// TODO Auto-generated me thod stub

		if(cmdBody.equals("...")){
			File[] roots = File.listRoots();
			msgBackList.clear();
			for (int i = 0; i < roots.length; i++) {
				
				String fileName = roots[i].getAbsolutePath();
				System.out.println(fileName);
				long lastModified = roots[i].lastModified();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String fileTime = dateFormat.format(new Date(lastModified));
				String fileSize = "0";
				System.out.println(fileSize);
				String isDir = "1";
				if(!roots[i].isDirectory()){
					isDir = "0";
					fileSize = ""+roots[i].length();
				}
				msgBackList.add(cmdBody+">"+fileName+">"+fileTime+">"+fileSize+">"+isDir+"");
			}

		}else{
			File files = new File(cmdBody);
			if(files.exists()){
				File[] listFiles = files.listFiles();
				msgBackList.clear();
				for (int i = 0; i < listFiles.length; i++) {
					File file = listFiles[i];
					String fileName = file.getName();
					System.out.println(fileName);
					long lastModified = file.lastModified();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					String fileTime = dateFormat.format(new Date(lastModified));
					String fileSize = "0";
					String isDir = "1";
					if(!file.isDirectory()){
						isDir = "0";
						fileSize = ""+file.length();
					}
					msgBackList.add(cmdBody+">"+fileName+">"+fileTime+">"+fileSize+">"+isDir+"");
				}
			}else{
				msgBackList.clear();
				msgBackList.add("文件路径不对");
			}
		}


		return msgBackList;
	}
}
