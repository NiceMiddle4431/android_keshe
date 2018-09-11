package chenpeng.client.view;

import java.util.ArrayList;

import chenpeng.client.app.R;
import chenpeng.client.data.NetFileData;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class NetFileListAdapter extends ArrayAdapter<String> {

	private Context context;
	private ArrayList<NetFileData> fileList;;

	public NetFileListAdapter(Context context, ArrayList<NetFileData> fileList) {
		super(context,android.R.layout.simple_list_item_1);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.fileList = fileList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return fileList.size();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		View view = LayoutInflater.from(context).inflate(R.layout.file_row_item, null, false);
		TextView fileName = (TextView) view.findViewById(R.id.textView1);
		TextView fileModifiedDate = (TextView) view.findViewById(R.id.textView2);
		TextView fileSize = (TextView) view.findViewById(R.id.textView3);
		ImageView img = (ImageView) view.findViewById(R.id.imageView1);

		if(fileList.get(position).isDirectory())
		{
			img.setImageResource(R.drawable.directory);
		}
		else
		{		
			String name = fileList.get(position).getFileName().toString().toLowerCase();
			int i = name.indexOf(".");
			String string = name.substring(i+1);
			Log.e("test", string);
			if(string.equals("mp3"))
			{
				img.setImageResource(R.drawable.mp3);
			}else if(string.equals("mp4"))
			{
				img.setImageResource(R.drawable.mp4);
			}else if(string.equals("txt"))
			{
				img.setImageResource(R.drawable.txt);
			}else if(string.equals("pan")){
				img.setImageResource(R.drawable.panfu);
			}
			else
			{
				img.setImageResource(R.drawable.wenjian);
			}

		}
		fileName.setText(fileList.get(position).getFileName());
		fileModifiedDate.setText(fileList.get(position).getFileModifiedDate());
		fileSize.setText(fileList.get(position).getFileSizeStr());
		return view;



	}

	public NetFileData getFileData(int position) {
		// TODO Auto-generated method stub
		NetFileData fileData = fileList.get(position);
		return fileData;
	}
}
