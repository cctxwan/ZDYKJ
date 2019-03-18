package com.cc.youku.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cc.youku.R;

/**
 * 其他
 */
public class QITAactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //这个是使用xml文件加载的界面（示例1）
        setContentView(R.layout.activity_qitaactivity);
        //这个是使用代码直接实例化的界面（示例2）
//        setContentView(new MyViewTwo(this));


        //滚动的TextView
//        setContentView(new ScrollTextView(this));

    }
}
