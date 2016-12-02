package cn.thcic.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * 1.getReadableDatabase()��getWritableDatabase()���sqlitedatabase����ͨ������������ݲ���
 * 2.oncreate onUpgrade�������ݿ�Ĵ����͸���
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
	public void onCreate(SQLiteDatabase db) {//��������е�һ�δ������ݿ��ʱ��ִ�У�ʵ�����ǵ���getReadableDatabase()��getWritableDatabase()ʱ����  

		System.out.println("sqlite onCreate");
		db.execSQL("create table user(id int,uname varchar(20))");//����һ����
		
	}
	//�������ݿ�汾
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		System.out.println("sqlite onUpgrade");
		
	}

}
