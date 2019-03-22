package com.cc.viewpager.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * view加载的适配器
 * 需要实现FragmentPagerAdapter这个适配器加载器
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    //定义一个fragment容器，用来接收页面显示的界面
    private List<Fragment> fragments;

    /**
     * 构造气
     * @param fm
     */
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * 构造器
     * @param fm
     * @param fragments
     */
    public ViewPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int postion) {
        return fragments.get(postion);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
