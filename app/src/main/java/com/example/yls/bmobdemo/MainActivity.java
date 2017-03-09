package com.example.yls.bmobdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class MainActivity extends AppCompatActivity implements onDelListener{
    private Button btn1,btn2;
    private TextView tv1;
    private List<Person>data=new ArrayList<>();
    private RecyclerView mRecyclerView;
    private PersonAdapter mPersonAdapter;
    public static MainActivity mactivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mactivity=this;
        btn1= (Button) findViewById(R.id.btn_1);
        btn2= (Button) findViewById(R.id.btn_2);
        mRecyclerView= (RecyclerView) findViewById(R.id.rv_1);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mPersonAdapter=new PersonAdapter(data,MainActivity.this);
        mRecyclerView.setAdapter(mPersonAdapter);
        Bmob.initialize(this, "411d05d98700cf8f3ffb72ef0ae4d43a");
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queryAll();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,newPerson.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void queryAll() {
        data.clear();
        BmobQuery<Person> query = new BmobQuery<Person>();
        query.findObjects(new FindListener<Person>() {
            @Override
            public void done(List<Person> list, BmobException e) {
                if(e==null){
                    for (int i = 0; i < list.size(); i++) {
                        data.add(list.get(i));
                    }
                    mPersonAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void refresh() {
        queryAll();
    }

    @Override
    public void del(String name) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        queryAll();
    }

}
