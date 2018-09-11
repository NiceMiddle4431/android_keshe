package chenpeng.client.data;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class AppValues extends Application {

	static AppValues appValues;
	
	private String ip;
	private int port;
	SharedPreferences sp;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		appValues = this;
		sp = getSharedPreferences("IpPost", MODE_PRIVATE);
		ip = sp.getString("ip", "");
		port = sp.getInt("port", 8019);
	}
	
	public void SaveData(String ip,int port){
		Editor edit = sp.edit();
		edit.putString("ip", ip);
		edit.putInt("port", port);
		edit.commit();
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	
	
}
