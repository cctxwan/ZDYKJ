package com.cc.scrolltxt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * 滚动的txt和一些其他的东西
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //这个是使用xml文件加载的界面（示例1）
        setContentView(R.layout.activity_main);

        //这个是使用代码直接实例化的界面（示例2）
//        setContentView(new MyViewTwo(this));


        //滚动的TextView
//        setContentView(new ScrollTextView(this));
    }
}
