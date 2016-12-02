package cn.thcic.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * 1.getReadableDatabase()、getWritableDatabase()获得sqlitedatabase对象，通过对象进行数据操作
 * 2.oncreate onUpgrade进行数据库的创建和更新
 * */
public class SQLiteDBHelper extends SQLiteOpenHelper{

	private static final int VERSION=1;
	public SQLiteDBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	public SQLiteDBHelper(Context context, String name, 
			int version) {
		this(context, name, null, version);
		// TODO Auto-generated constructor stub
	}
	public SQLiteDBHelper(Context context, String name) {
		this(context, name, VERSION);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void onCreate(SQLiteDatabase db) {//这个函数中第一次创建数据库的时候执行，实际上是调用getReadableDatabase()、getWritableDatabase()时调用  

		System.out.println("sqlite onCreate");
		db.execSQL("create table user(id int,uname varchar(20))");//创建一个表
		
	}
	//更新数据库版本
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		System.out.println("sqlite onUpgrade");
		
	}

}
