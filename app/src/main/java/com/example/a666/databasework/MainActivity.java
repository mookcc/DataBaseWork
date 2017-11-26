package com.example.a666.databasework;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import MyOpenHelper.MyOpenhelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private MyOpenhelper myOpenHelper;
    private Button add;
    private Button read;
    private Button esc;
    private EditText addName;
    private EditText addMName;
    private EditText addPhone;
    private RadioGroup addSex;
    private RadioButton male;
    private RadioButton female;
    private String sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //1创建数据库
        myOpenHelper = new MyOpenhelper(this, "BookStore.db", null, 1);
        //实例化控件
        add= (Button) findViewById(R.id.add);
        read= (Button) findViewById(R.id.read);
        esc= (Button) findViewById(R.id.esc);
        addName= (EditText) findViewById(R.id.addName);
        addMName= (EditText) findViewById(R.id.addWName);
        addPhone= (EditText) findViewById(R.id.addPhone);
        addSex= (RadioGroup) findViewById(R.id.addSex);
        male = (RadioButton)findViewById(R.id.male);
        female = (RadioButton)findViewById(R.id.female);
        addSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(male.getId()==i){
                    sex = male.getText().toString();
                }
                if(female.getId()==i){
                    sex = female.getText().toString();
                }
                Toast.makeText(getApplicationContext(),sex,Toast.LENGTH_SHORT).show();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //1获取数据库对象
                SQLiteDatabase db = myOpenHelper.getWritableDatabase();
                //2增加数据
                ContentValues values = new ContentValues();
                values.put("name", addName.getText().toString());
                values.put("phoneNumber", addPhone.getText().toString());
                values.put("wName", addMName.toString());
                values.put("sex", sex);
                db.insert("StudentBook", null, values);
                //3关闭数据库
                values.clear();
                Toast.makeText(getApplication(),"数据添加成功",Toast.LENGTH_SHORT).show();
            }
        });
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),MateBook.class);
                startActivity(intent);
            }
        });
        esc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    @Override
    public void onClick(View view) {

        //设置点击事件
        switch (view.getId()){
            case R.id.add:
                //1获取数据库对象
                SQLiteDatabase db = myOpenHelper.getWritableDatabase();
                //2增加数据
                ContentValues values = new ContentValues();
                values.put("name", addName.getText().toString());
                values.put("phoneNumber", addPhone.getText().toString());
                values.put("wName", addMName.toString());
                values.put("sex", sex);
                db.insert("StudentBook", null, values);
                //3关闭数据库
                values.clear();
                Toast.makeText(getApplication(),"数据添加成功",Toast.LENGTH_SHORT).show();
                break;
            case R.id.read:
                Intent intent=new Intent(this,MateBook.class);
                startActivity(intent);
                break;
            case R.id.esc:
                this.finish();
                break;

        }
    }
}
