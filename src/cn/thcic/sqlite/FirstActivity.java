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

	//更新数据库版本
	class BtnupdatedbOnClick implements OnClickListener{

		@Override
		public void onClick(View v) {
//			User user=new User(uname.getText().toString(),upwd.getText().toString());
			SQLiteDBHelper dbHelper=new SQLiteDBHelper(FirstActivity.this, "mydb",2);//版本号变化
			SQLiteDatabase db=dbHelper.getWritableDatabase();
		
		}
		
	}
	//创建数据库
	class BtncreatedbOnClick implements OnClickListener{
		
		@Override
		public void onClick(View v) {

			//创建一个dbhelper辅助类对象
			SQLiteDBHelper dbHelper=new SQLiteDBHelper(FirstActivity.this, "mydb");//第一个参数是当前类，第二个参数是数据库名称
			//通过辅助类对象的方法获取一个数据库对象
			SQLiteDatabase db=dbHelper.getReadableDatabase();
			
		}
		
	}
	
	//增加
	class BtninsertOnClick implements OnClickListener{
		
		@Override
		public void onClick(View v) {

			//负责给需要添加到列赋值
			ContentValues values=new ContentValues();
			//User user=new User(uname.getText().toString(),upwd.getText().toString());
			//赋值的时候，需要指定（列名，值）
			values.put("id", Integer.parseInt(uname.getText().toString()));
			values.put("uname",upwd.getText().toString());
			
			SQLiteDBHelper dbHelper=new SQLiteDBHelper(FirstActivity.this, "mydb",2);
			SQLiteDatabase db=dbHelper.getReadableDatabase();
			//进行insert（表名，空列，所有列的值）
			db.insert("user", null, values);
			System.out.println("insert successful");
		}
		
	}
	//更新 艽野
	class BtnupdateOnClick implements OnClickListener{
		
		@Override
		public void onClick(View v) {
//			User user=new User(uname.getText().toString(),upwd.getText().toString());
			SQLiteDBHelper dbHelper=new SQLiteDBHelper(FirstActivity.this, "mydb",2);
			SQLiteDatabase db=dbHelper.getWritableDatabase();
			ContentValues values=new ContentValues();
			values.put("uname",upwd.getText().toString());
			
			//update（表名，需要更新列值，条件，条件值）
			db.update("user", values, "id=?", new String[]{"1"});
			System.out.println("update successful");
			
			
		}
		
	}
	//删除 注意条件
	//删除
	class BtndeleteOnClick implements OnClickListener{
		
		@Override
		public void onClick(View v) {

			SQLiteDBHelper dbHelper=new SQLiteDBHelper(FirstActivity.this, "mydb",2);
			SQLiteDatabase db=dbHelper.getWritableDatabase();
		
			//delete（表名，where条件可以使用占位符？，字符串的数组包含条件的值）
			db.delete("user", "id=?",  new String[]{"1"});
			System.out.println("delete successful");
		}
		
	}
	//查询
	class BtnqueryOnClick implements OnClickListener{
		
		@Override
		public void onClick(View v) {

			SQLiteDBHelper dbHelper=new SQLiteDBHelper(FirstActivity.this, "mydb",2);
			SQLiteDatabase db=dbHelper.getWritableDatabase();

			//cursor 相当于resultset
			Cursor cursor=db.query("user", new String[]{"id","uname"},"id>?", new String[]{"1"}, null, null, null);
			//要进行读取判断
			while(cursor.moveToNext()){
				//通过各种getXXX(列的下标)获取单元格的值，列的下标cursor.getColumnIndex("id")
				int id=cursor.getInt(cursor.getColumnIndex("id"));
				String uname=cursor.getString(cursor.getColumnIndex("uname"));
				System.out.println("id="+id+"   uname"+uname);
			}
			System.out.println("select successful");
		}
		
	}
}
