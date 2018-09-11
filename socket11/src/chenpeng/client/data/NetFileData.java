package chenpeng.client.data;

import android.util.Log;

public class NetFileData {
	
	private long fileSize = 0;
	private String fileName = "$error";
	private String filePath = ".\\";
	private String fileSizeStr = "0";
	private int fileType = 0;//0表示文件，1目录，2磁盘
	private boolean isDirectory = false;
	private String fileModifiedDate = "1970-01-01 00:00:00";
	//fileInfo为filePath>fileName>fileDate>fileSize>isDir
	public NetFileData(String fileInfo)
	{
		String fileStr[] = fileInfo.split(">");
		if(fileStr.length == 5)
		{
			filePath = fileStr[0];
			fileName = fileStr[1];
			fileModifiedDate = fileStr[2];
			fileSizeStr = ChangeFileSize(fileStr[3]);
			if(fileStr[4].equals("1"))
			{
				fileType = 1;
				isDirectory = true;
			}

		}
	}
	


	private String ChangeFileSize(String fileSizeStr) {
		// TODO Auto-generated method stub
		
		String fileStr = "";
		long fileSize =  Long.parseLong(fileSizeStr);
		
		Log.e("test fileSize", ""+fileSize);
		double B = 1024;
		double KB = 1024*B;
		double MB = 1024*KB;
		
		if(fileSize>MB)
		{
			fileStr = new String().format("%.2fGB", fileSize/MB);
			Log.e("test fileStr", fileStr);
			return fileStr;
		}
		if(fileSize>KB)
		{
			fileStr = new String().format("%.1fMB", fileSize/KB);
			Log.e("test fileStr", fileStr);
			return fileStr;
		}
		
		fileStr = new String().format("%.0fKB", fileSize/B);
		Log.e("test fileStr", fileStr);
		return fileStr;
	}


	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileSizeStr() {
		return fileSizeStr;
	}
	public void setFileSizeStr(String fileSizeStr) {
		this.fileSizeStr = fileSizeStr;
	}
	public boolean isDirectory() {
		return isDirectory;
	}
	public void setDirectory(boolean isDirectory) {
		this.isDirectory = isDirectory;
	}
	public String getFileModifiedDate() {
		return fileModifiedDate;
	}
	public void setFileModifiedDate(String fileModifiedDate) {
		this.fileModifiedDate = fileModifiedDate;
	} 
	public int getFileType() {
		return fileType;
	}
	public void setFileType(int fileType) {
		this.fileType = fileType;
	}
}
