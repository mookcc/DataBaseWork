package com.example.a666.databasework;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import Bean.StudentBook;
import MyOpenHelper.MyOpenhelper;

/**
 * Created by 666 on 2017/11/26.
 */

public class MateBook extends AppCompatActivity {
    private MyOpenhelper myOpenHelper;
    private List<StudentBook> list;
    private ListView listView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.schollmate_book);
        TextView bt_back= (TextView) findViewById(R.id.back);
        myOpenHelper = new MyOpenhelper(this, "BookStore.db", null, 1);
        SQLiteDatabase db = myOpenHelper.getWritableDatabase();
        Cursor cursor = db.query("BookStore", null, null, null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String phoneNumber = cursor.getString(cursor.getColumnIndex("phoneNumber"));
                String sex = cursor.getString(cursor.getColumnIndex("sex"));
                String wName = cursor.getString(cursor.getColumnIndex("wName"));

                //把数据封装到JavaBean对象中
                StudentBook book = new StudentBook();
                book.setName(name);
                book.setPhoneNumber(phoneNumber);
                book.setSex(sex);
                book.setwName(wName);
                //把JavaBean对象加入到集合里
                list.add(book);
            }
            listView.setAdapter(new MyAdapter());
        }
        cursor.close();
        db.close();
    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view1;
            if (view==null){
                view1=View.inflate(getApplicationContext(),R.layout.book_item,null);
            }else {
                view1=view;
            }
            //找到控件显示数据
            TextView tv_name= (TextView) view1.findViewById(R.id.name);
            TextView tv_wName= (TextView) view1.findViewById(R.id.wName);
            TextView tv_sex= (TextView) view1.findViewById(R.id.sex);
            TextView tv_phone= (TextView) view1.findViewById(R.id.phone);
            //如何显示数据
            StudentBook book=list.get(i);
            tv_name.setText(book.getName());
            tv_wName.setText(book.getwName());
            tv_phone.setText(book.getPhoneNumber());
            tv_sex.setText(book.getSex());

            return view1;
        }
    }
}