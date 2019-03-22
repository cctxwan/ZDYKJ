package com.cc.viewpager.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cc.viewpager.R;
import com.cc.viewpager.WelcomeActivity;

/**
 * 重用的fragment
 */
public class ViewPagerFragment extends Fragment implements View.OnClickListener {

    View view;

    RelativeLayout rel_vp;

    TextView txt_begin;

    int images[] = {
            R.drawable.guide_bg_1,
            R.drawable.guide_bg_2,
            R.drawable.guide_bg_3,
            R.drawable.guide_bg_4
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_vp, null);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //初始化控件
        initView();
        //获取数据并显示出来
        initData();
    }

    /**
     * 获取activity传入的数据
     */
    private void initData() {
        //获取bundle
        Bundle bundle = getArguments();
        if(bundle == null) return;
        //拿到index值
        int index = bundle.getInt("index");

        rel_vp.setBackgroundResource(images[index]);
        //给txt设置背景
        txt_begin.setBackgroundResource(R.drawable.guide_txt);
        txt_begin.setText("进入首页");
        //当vp滑动到下标为3（第四个界面）的时候，显示这个txt，否则隐藏
        txt_begin.setVisibility(index == 3 ? View.VISIBLE : View.GONE);
    }

    /**
     * 实例化控件
     */
    private void initView() {
        rel_vp = view.findViewById(R.id.rel_vp);
        txt_begin = view.findViewById(R.id.txt_begin);

        txt_begin.setOnClickListener(this);
    }

    /**
     * 单击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        int temdId = v.getId();
        if(temdId == R.id.txt_begin){
            //跳往主界面
            startActivity(new Intent(getActivity(), WelcomeActivity.class));
            //不要任何动画效果
            getActivity().overridePendingTransition(0, 0);
            getActivity().finish();
        }
    }
}
