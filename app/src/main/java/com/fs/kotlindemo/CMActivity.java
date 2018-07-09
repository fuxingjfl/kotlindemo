package com.fs.kotlindemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2018/7/3.
 */
//测试
public class CMActivity extends Activity {

    private MyLayout myLayout;
    private Button button1,button2;
    private ImageView imageview1;
    private RelativeLayout rl_xg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.smsj_layout);
        myLayout=findViewById(R.id.my_layout);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        imageview1=findViewById(R.id.imageview1);
        rl_xg=findViewById(R.id.rl_xg);
        myLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("TAG", "myLayout on touch");
                return false;
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "You clicked button1");
            }
        });
        button2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.d("TAG", "You clicked button2");
                return false;
            }
        });
        imageview1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.d("TAG", "You Touch imageview1");
                return false;
            }
        });
        rl_xg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
