package chenpeng.client.app;

import java.util.ArrayList;

import chenpeng.client.data.AppValues;
import chenpeng.client.data.CmdGroup;
import chenpeng.client.data.NetFileData;
import chenpeng.client.operator.HotKeyGenerator;
import chenpeng.client.operator.ShowNonUiUpdateCmdHandler;
import chenpeng.client.operator.ShowRemoteFileHandler;
import chenpeng.client.socket.CmdClientSocket;
import chenpeng.client.view.AddCmdGroupDialog;
import chenpeng.client.view.HotKeyDialog;
import chenpeng.client.view.MouseMoveDialog;
import chenpeng.client.view.NetFileListAdapter;

import android.R.integer;
import android.R.layout;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.database.DataSetObserver;
import android.text.AlteredCharSequence;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	EditText ed1,ed2;
	ListView lv;
	Button bt;
	String text;
	String ip = "";
	int port = 7;
	ShowNonUiUpdateCmdHandler showNonUiUpdateCmdHandler;
	ShowRemoteFileHandler showRemoteFileHandler;
	AppValues appValues;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		appValues = (AppValues)getApplication();
		ip = appValues.getIp();
		port = appValues.getPort();
		ed1 = (EditText) findViewById(R.id.editText1);
		ed2 = (EditText) findViewById(R.id.editText2);
		bt = (Button) findViewById(R.id.button1);
		lv = (ListView) findViewById(R.id.listView1);
		ed1.setText(ip);
		ed2.setText(""+port);

		bt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ip = ed1.getText().toString();
				port = Integer.parseInt(ed2.getText().toString()); 
				appValues.setIp(ip);
				appValues.setPort(port);
				appValues.SaveData(ip, port);
				ShowRemoteFileHandler fileHandler = new ShowRemoteFileHandler(MainActivity.this, lv);
				CmdClientSocket socket = new CmdClientSocket(ip, port, fileHandler);
				socket.work("dir:...");
			}
		});
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				showRemoteFileHandler = new ShowRemoteFileHandler(MainActivity.this, lv);
				showNonUiUpdateCmdHandler = new ShowNonUiUpdateCmdHandler(MainActivity.this);
				Log.d("test:", "handler创建成功");
				NetFileListAdapter adapter = (NetFileListAdapter) arg0.getAdapter();
				NetFileData fileData = adapter.getFileData(arg2);
				Log.d("test:", "fileData创建成功");
				String path = fileData.getFilePath();
				if(path.equals("...")){
					path="";
				}
				String fileName = fileData.getFileName();
				String filePath = path+fileName+"//";
				if(fileData.getFileType()>=1)
				{
					CmdClientSocket socket = new CmdClientSocket(ip, port, showRemoteFileHandler);
					socket.work("dir:"+filePath);
				}
				else{
					CmdClientSocket socket = new CmdClientSocket(ip, port, showNonUiUpdateCmdHandler);
					socket.work("open:"+filePath);
				}

			}
		});

		registerForContextMenu(lv);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		MenuInflater menuInflater = new MenuInflater(this);
		menuInflater.inflate(R.menu.context_menu, menu);


		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub


		//获取点击的listView上的文件信息
		AdapterContextMenuInfo adapterContextMenuInfo = (AdapterContextMenuInfo) item.getMenuInfo();
		int pos = adapterContextMenuInfo.position;
		Log.d("position", ""+pos);
		NetFileListAdapter adapter = (NetFileListAdapter) lv.getAdapter();
		NetFileData fileData = adapter.getFileData(pos);
		Log.d("fileData", fileData.getFileName());
		if(item.getItemId() == R.id.item1){
			showHotKeyDialog(fileData);			
		}
		else if(item.getItemId()==R.id.item2){
			showMouthMove();
		}else if(item.getItemId()==R.id.item3){
			CmdClientSocket cmdClientSocket = new CmdClientSocket(ip, port, showNonUiUpdateCmdHandler);
			cmdClientSocket.work("cmd:www.baidu.com\nslp:2000\ncps:android");
		}else if(item.getItemId()==R.id.item4){
			showAddCmdGroup();
		}
		
		return true;
	}

	private void showAddCmdGroup() {
		// TODO Auto-generated method stub
		new AddCmdGroupDialog(MainActivity.this).Show();
	}

	private void showMouthMove() {
		// TODO Auto-generated method stub
		CmdClientSocket cmdClientSocket = new CmdClientSocket(ip, port, showNonUiUpdateCmdHandler);
		new MouseMoveDialog(MainActivity.this, ip,port,showNonUiUpdateCmdHandler).Show();
	}

	private void showHotKeyDialog(NetFileData fileData) {
		// TODO Auto-generated method stub
		CmdClientSocket cmdClientSocket = new CmdClientSocket(ip, port, showNonUiUpdateCmdHandler);
		HotKeyGenerator hotKeyGenerator = new HotKeyGenerator(fileData);
		new HotKeyDialog(MainActivity.this, hotKeyGenerator.getGetHotkeyList(), "热键操作表", cmdClientSocket).Show();
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
