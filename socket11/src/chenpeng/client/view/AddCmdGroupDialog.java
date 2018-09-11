package chenpeng.client.view;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import chenpeng.client.app.R;
import chenpeng.client.data.HotkeyData;
import chenpeng.client.operator.CmdGroupSQLiteOpenHelper;
import chenpeng.client.socket.CmdClientSocket;

public class AddCmdGroupDialog {
	
	private Context context;
	public AddCmdGroupDialog(Context context) {
		super();
		this.context = context;
	}
	public void Show() {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("添加组合命令");
		
		View view = LayoutInflater.from(context).inflate(R.layout.cmd_add_group,null,false);
		EditText et1 = (EditText) view.findViewById(R.id.editText1);
		EditText et2 = (EditText) view.findViewById(R.id.editText2);
		final String cmdGroupName = et1.getText().toString();
		final String cmdGroupCmd = et2.getText().toString();
		builder.setPositiveButton("确定", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				CmdGroupSQLiteOpenHelper sqLiteOpenHelper = new CmdGroupSQLiteOpenHelper(context);
				sqLiteOpenHelper.addCmdGroup(cmdGroupName, cmdGroupCmd);
			}
		});
		
		builder.setView(view);
		builder.show();
	}

	
}
