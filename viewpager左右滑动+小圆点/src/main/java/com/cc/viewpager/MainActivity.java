package com.cc.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.cc.viewpager.adapter.ViewPagerAdapter;
import com.cc.viewpager.fragment.ViewPagerFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * viewpager左右滑动（支持显示小圆点）
 */
public class MainActivity extends AppCompatActivity {

    //viewpager
    ViewPager viewPager;

    //viewpager的适配加载器
    PagerAdapter pagerAdapter;

    //小圆点
    LinearLayout lin_zsq;

    List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,
                WindowManager.LayoutParams. FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        
        //实例化控件
        initView();
        //实例化数据
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        //循环加载一下Frag
        for (int i = 0; i < 4; i++){
            //实例化fragment，通过index值实现重用
            ViewPagerFragment fragment = new ViewPagerFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("index", i);
            fragment.setArguments(bundle);
            //添加每一个fragment进入容器中
            fragments.add(fragment);
        }


        //实例化适配器，并将fragments容器里面的每一个frag传进去加载
        pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments);
        //绑定适配器
        viewPager.setAdapter(pagerAdapter);

        //监听滑动时间.
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float v, int i1) {
                for (int i = 0; i < fragments.size(); i++){
                    lin_zsq
                            .getChildAt(i)
                            .setBackgroundResource(
                                    position == i
                                            ? R.drawable.zsq_selected
                                            : R.drawable.zsq_not_selected
                            );
                }
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        initZSQ();
    }


    /**
     * 初始化指示器
     */
    private void initZSQ() {
        //首先，计算一个指示器小圆点的大小
        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10f, getResources().getDisplayMetrics());

        //生成小圆点
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, width);

        //根据容器中的fragment数量生成多个
        for (int i = 0; i <fragments.size(); i++){
            View view = new View(this);
            params.setMargins(5, 5, 5, 5);
            view.setId(i);
            view.setBackgroundResource(
                    i == 0
                            ? R.drawable.zsq_selected
                            : R.drawable.zsq_not_selected
            );

            view.setLayoutParams(params);
            lin_zsq.addView(view, i);
        }
    }

    /**
     * 初始化控件
     */
    private void initView() {
        //得到viewpager和页面指示器小圆点
        viewPager = findViewById(R.id.viewpaper);
        lin_zsq = findViewById(R.id.zsq);
    }
}
