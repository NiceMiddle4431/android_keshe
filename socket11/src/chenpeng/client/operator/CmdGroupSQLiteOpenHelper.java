package chenpeng.client.operator;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class CmdGroupSQLiteOpenHelper extends SQLiteOpenHelper {

	Context context;
	static String KEY_DBNAME = "CmdGroup";
	SQLiteDatabase db;
	public CmdGroupSQLiteOpenHelper(Context context) {
		super(context, KEY_DBNAME, null, 1);
		// TODO Auto-generated constructor stub
		this.context = context;
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		this.db = db;
		db.execSQL("create table if not exists "+KEY_DBNAME+" (_id integer primary key autoincrement,cmdName text,cmdGroup text)");
		ContentValues cv = new ContentValues();
		cv.put("cmdName", "123213");
		cv.put("cmdGroup", "cmdGroup");
		db.insert(KEY_DBNAME, null, cv);
	}

	
	public void addCmdGroup(String cmdName,String cmdGroup){
		ContentValues cv = new ContentValues();
		cv.put("cmdName", cmdName);
		cv.put("cmdGroup", cmdGroup);
		db.insert(KEY_DBNAME, null, cv);
	}
	
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}


}
