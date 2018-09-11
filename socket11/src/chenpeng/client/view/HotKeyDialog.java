package chenpeng.client.view;

import java.util.ArrayList;
import java.util.zip.Inflater;

import chenpeng.client.app.R;
import chenpeng.client.data.HotkeyData;
import chenpeng.client.socket.CmdClientSocket;

import android.app.AlertDialog;
import android.content.Context;
import android.text.AlteredCharSequence;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;

public class HotKeyDialog {

	private Context context;
	private ArrayList<HotkeyData> hotkeyList;
	private String title;
	private CmdClientSocket cmdClientSocket;
	public HotKeyDialog(Context context, ArrayList<HotkeyData> hotkeyList,
			String title, CmdClientSocket cmdClientSocket) {
		super();
		this.context = context;
		this.hotkeyList = hotkeyList;
		this.title = title;
		this.cmdClientSocket = cmdClientSocket;
	}
	public void Show() {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(title);
		
		View view = LayoutInflater.from(context).inflate(R.layout.hotkey_view,null,false);
		GridView gridView = (GridView) view.findViewById(R.id.gridView1);
		HotKeyGridAdapter adapter = new HotKeyGridAdapter(context, hotkeyList,cmdClientSocket);
		gridView.setAdapter(adapter);
		
		builder.setView(view);
		builder.show();
	}
}
