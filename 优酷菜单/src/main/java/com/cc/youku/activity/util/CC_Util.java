package com.cc.youku.activity.util;

import android.animation.ObjectAnimator;
import android.view.ViewGroup;

/**
 * 动画代码逻辑
 * @kcw
 */
public class CC_Util {


    /**
     * 提取重复代码
     * @param view
     */
    public static void showView(ViewGroup view) {
        showView(view, 0);
    }

    /**
     * 显示动画
     * @param view
     */
    public static void showView(ViewGroup view, int startOffset) {
//        //视图动画（第一种）
//        RotateAnimation as = new RotateAnimation(180, 360, view.getWidth()/2, view.getHeight());
//        //设置动画时间
//        as.setDuration(300);
//        //设置动画结束后的停留状态
//        as.setFillAfter(true);
//        //隐藏动画
//        view.startAnimation(as);
//
//        for (int i = 0; i < view.getChildCount(); i++){
//            View child = view.getChildAt(i);
//            child.setEnabled(true);
//        }



        //属性动画（第二种）
        ObjectAnimator oa = ObjectAnimator.ofFloat(view, "rotation", 180, 360);
        oa.setDuration(300);
        //动画延迟时间
        oa.setStartDelay(startOffset);
        oa.start();

        view.setPivotX(view.getWidth()/2);
        view.setPivotY(view.getHeight());
    }

    /**
     * 隐藏动画
     * @param view
     */
    public static void hideView(ViewGroup view) {
        hideView(view, 0);
    }

    /**
     * 隐藏动画
     * @param view
     * @param startOffset  设置隐藏时间
     */
    public static void hideView(ViewGroup view, int startOffset) {
//        //视图动画（第一种）
//        RotateAnimation as = new RotateAnimation(0, 180, view.getWidth()/2, view.getHeight());
//        //设置动画时间
//        as.setDuration(300);
//        //设置动画结束后的停留状态
//        as.setFillAfter(true);
//        as.setStartOffset(startOffset);
//        //隐藏动画
//        view.startAnimation(as);
//
//        for (int i = 0; i < view.getChildCount(); i++){
//            View child = view.getChildAt(i);
//            child.setEnabled(false);
//        }




        //属性动画（第二种）
        ObjectAnimator oa = ObjectAnimator.ofFloat(view, "rotation", 0, 180);
        oa.setDuration(300);
        //动画延迟时间
        oa.setStartDelay(startOffset);
        oa.start();

        view.setPivotX(view.getWidth()/2);
        view.setPivotY(view.getHeight());
    }
}
