package com.cc.listview_ch;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.cc.listview_ch.adapter.ModelAdapter;
import com.cc.listview_ch.model.ModelInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * ListView滑动删除
 * @kcw
 * @2019年3月
 */
public class MainActivity extends AppCompatActivity {

    Activity activity = MainActivity.this;

    private ListView listView;

    private List<ModelInfo> datas = new ArrayList<>();

    private ModelAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    /**
     * 实例化数据
     */
    private void initData() {
        for (int i = 0; i < 30; i++){
            datas.add(new ModelInfo("你是世界第" + i + "帅~~~"));
        }

        adapter = new ModelAdapter(activity, datas);
        listView.setAdapter(adapter);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        //实例化lv
        listView = findViewById(R.id.listview);
    }
}
