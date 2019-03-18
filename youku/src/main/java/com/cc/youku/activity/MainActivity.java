package com.cc.youku.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cc.youku.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button bt_qita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        bt_qita = findViewById(R.id.bt_qita);

        bt_qita.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int temdId = v.getId();
        if(temdId == R.id.bt_qita){
            Intent intent = new Intent(MainActivity.this, QITAactivity.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
        }
    }
}