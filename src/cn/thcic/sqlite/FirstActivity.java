package cn.thcic.sqlite;

import java.net.URI;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;

public class FirstActivity extends Activity {
//http://www.cnblogs.com/walkingp/archive/2011/03/28/1997437.html
	//http://sqliteadmin.orbmu2k.de/
	EditText uname;
	EditText upwd;
	Button btncreatedb;
	Button btnupdatedb;
	Button btninsert;
	Button btnupdate;
	Button btndelete;
	Button btnquery;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
		uname=(EditText)findViewById(R.id.uname);
		upwd=(EditText)findViewById(R.id.upwd);
		
		 btncreatedb=(Button)findViewById(R.id.btncreatedb);
		 btnupdatedb=(Button)findViewById(R.id.btnupdatedb);
		 btninsert=(Button)findViewById(R.id.btninsert);
		 btnupdate=(Button)findViewById(R.id.btnupdate);
		 btndelete=(Button)findViewById(R.id.btndelete);
		 btnquery=(Button)findViewById(R.id.btnquery);
		
		btncreatedb.setOnClickListener(new BtncreatedbOnClick());
		btnupdatedb.setOnClickListener(new BtnupdatedbOnClick());
		btninsert.setOnClickListener(new BtninsertOnClick());
		btnupdate.setOnClickListener(new BtnupdateOnClick());
		btndelete.setOnClickListener(new BtndeleteOnClick());
		btnquery.setOnClickListener(new BtnqueryOnClick());
	}

	//�������ݿ�汾
	class BtnupdatedbOnClick implements OnClickListener{

		@Override
		public void onClick(View v) {
//			User user=new User(uname.getText().toString(),upwd.getText().toString());
			SQLiteDBHelper dbHelper=new SQLiteDBHelper(FirstActivity.this, "mydb",2);//�汾�ű仯
			SQLiteDatabase db=dbHelper.getWritableDatabase();
		
		}
		
	}
	//�������ݿ�
	class BtncreatedbOnClick implements OnClickListener{
		
		@Override
		public void onClick(View v) {

			//����һ��dbhelper���������
			SQLiteDBHelper dbHelper=new SQLiteDBHelper(FirstActivity.this, "mydb");//��һ�������ǵ�ǰ�࣬�ڶ������������ݿ�����
			//ͨ�����������ķ�����ȡһ�����ݿ����
			SQLiteDatabase db=dbHelper.getReadableDatabase();
			
		}
		
	}
	
	//����
	class BtninsertOnClick implements OnClickListener{
		
		@Override
		public void onClick(View v) {

			//�������Ҫ��ӵ��и�ֵ
			ContentValues values=new ContentValues();
			//User user=new User(uname.getText().toString(),upwd.getText().toString());
			//��ֵ��ʱ����Ҫָ����������ֵ��
			values.put("id", Integer.parseInt(uname.getText().toString()));
			values.put("uname",upwd.getText().toString());
			
			SQLiteDBHelper dbHelper=new SQLiteDBHelper(FirstActivity.this, "mydb",2);
			SQLiteDatabase db=dbHelper.getReadableDatabase();
			//����insert�����������У������е�ֵ��
			db.insert("user", null, values);
			System.out.println("insert successful");
		}
		
	}
	//���� ܴҰ
	class BtnupdateOnClick implements OnClickListener{
		
		@Override
		public void onClick(View v) {
//			User user=new User(uname.getText().toString(),upwd.getText().toString());
			SQLiteDBHelper dbHelper=new SQLiteDBHelper(FirstActivity.this, "mydb",2);
			SQLiteDatabase db=dbHelper.getWritableDatabase();
			ContentValues values=new ContentValues();
			values.put("uname",upwd.getText().toString());
			
			//update����������Ҫ������ֵ������������ֵ��
			db.update("user", values, "id=?", new String[]{"1"});
			System.out.println("update successful");
			
			
		}
		
	}
	//ɾ�� ע������
	//ɾ��
	class BtndeleteOnClick implements OnClickListener{
		
		@Override
		public void onClick(View v) {

			SQLiteDBHelper dbHelper=new SQLiteDBHelper(FirstActivity.this, "mydb",2);
			SQLiteDatabase db=dbHelper.getWritableDatabase();
		
			//delete��������where��������ʹ��ռλ�������ַ������������������ֵ��
			db.delete("user", "id=?",  new String[]{"1"});
			System.out.println("delete successful");
		}
		
	}
	//��ѯ
	class BtnqueryOnClick implements OnClickListener{
		
		@Override
		public void onClick(View v) {

			SQLiteDBHelper dbHelper=new SQLiteDBHelper(FirstActivity.this, "mydb",2);
			SQLiteDatabase db=dbHelper.getWritableDatabase();

			//cursor �൱��resultset
			Cursor cursor=db.query("user", new String[]{"id","uname"},"id>?", new String[]{"1"}, null, null, null);
			//Ҫ���ж�ȡ�ж�
			while(cursor.moveToNext()){
				//ͨ������getXXX(�е��±�)��ȡ��Ԫ���ֵ���е��±�cursor.getColumnIndex("id")
				int id=cursor.getInt(cursor.getColumnIndex("id"));
				String uname=cursor.getString(cursor.getColumnIndex("uname"));
				System.out.println("id="+id+"   uname"+uname);
			}
			System.out.println("select successful");
		}
		
	}
}
