package com.cc.youku.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.cc.youku.R;
import com.cc.youku.activity.util.CC_Util;

/**
 * 安卓仿优酷菜单动画
 * @kcw
 * @version 2019年3月
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //里面一层的菜单和中间那层的菜单
    ImageView icon_home, icon_menu;

    //三个大布局，这里注意下，xml先从三布局开始写，否则的话会被覆盖掉
    RelativeLayout level1, level2, level3;

    //默认设置中间布局显示
    private boolean isLevel2 = true;

    //默认设置外层布局显示
    private boolean isLevel3 = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        //默认关闭第二层和第三层
        initData();
    }

    private void initData() {
        if(isLevel2){
            isLevel2 = false;
            CC_Util.hideView(level2);
            if(isLevel3){
                isLevel3 = false;
                CC_Util.hideView(level3, 120);
            }
        }else{
            isLevel2 = true;
            CC_Util.showView(level2);
        }
    }

    /**
     * 初始化布局
     * 并为按钮设置点击事件
     */
    private void initView() {
        //获取两个图片控件
        icon_home = findViewById(R.id.icon_home);
        icon_menu = findViewById(R.id.icon_menu);

        //三个re布局
        level1 = findViewById(R.id.level1);
        level2 = findViewById(R.id.level2);
        level3 = findViewById(R.id.level3);

        //点击事件
        icon_home.setOnClickListener(this);
        icon_menu.setOnClickListener(this);

        level1.setOnClickListener(this);
        level2.setOnClickListener(this);
        level3.setOnClickListener(this);
    }

    /**
     * 单击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        int temdId = v.getId();
        //最里面一层
        if(temdId == R.id.icon_home || temdId == R.id.level1){
            Log.i("TAG", "icon_home");
            //点击最里一层，外面两层都需要关闭
            //当中间层显示时
            if(isLevel2){
                //设置为不显示
                isLevel2 = false;
                //隐藏中间层
                CC_Util.hideView(level2);
                //并判断最外层是否显示
                if(isLevel3){
                    //隐藏
                    isLevel3 = false;
                    CC_Util.hideView(level3, 120);
                }
            }else{
                //当中间层不显示时，修改状态并显示
                isLevel2 = true;
                CC_Util.showView(level2);
            }
        }else if(temdId == R.id.icon_menu){
            Log.i("TAG", "icon_menu");
            //当点击中间层时，只对最外面一层处理
            if(isLevel3){
                //为true则关闭，反之，显示
                isLevel3 = false;
                CC_Util.hideView(level3);
            }else{
                isLevel3 = true;
                CC_Util.showView(level3);
            }
        }
    }
}
