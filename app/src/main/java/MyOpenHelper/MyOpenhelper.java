package MyOpenHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by 666 on 2017/11/26.
 */

public class MyOpenhelper extends SQLiteOpenHelper {
    //书写创建数据库语句，定义为常量
    public final String CREAT_TABLE="create table Book (name text primary key ,phoneNumber int," +
            "wName text," +
            "sex text)";
    private Context mContext;
    public MyOpenhelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREAT_TABLE);
        Toast.makeText(mContext,"创建成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
