package chenpeng.client.view;

import java.util.ArrayList;
import java.util.List;

import chenpeng.client.app.R;
import chenpeng.client.data.HotkeyData;
import chenpeng.client.socket.CmdClientSocket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class HotKeyGridAdapter extends ArrayAdapter {

	Context context;
	ArrayList<HotkeyData> list;
	CmdClientSocket cmdClientSocket;
	public HotKeyGridAdapter(Context context, ArrayList<HotkeyData> list,CmdClientSocket cmdClientSocket) {
		super(context,android.R.layout.simple_list_item_1);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.list = list;
		this.cmdClientSocket = cmdClientSocket;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}


	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = LayoutInflater.from(context).inflate(R.layout.hotkey_row_view, null, false);
		TextView tv = (TextView) view.findViewById(R.id.textView1);
		tv.setText(list.get(position).getHotKeyName());
		tv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				cmdClientSocket.work(list.get(position).getHotKeyCmd());
			}
		});
		return view;
	}	
	
}
