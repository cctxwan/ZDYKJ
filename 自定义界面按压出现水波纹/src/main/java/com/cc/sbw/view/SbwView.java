package com.cc.sbw.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * 自定义水波纹
 */
public class SbwView extends View {

    private Paint paint;

    /**
     * 两个相邻水波纹的中心店的最小距离
     */
    private static final int DIS_SOLP = 13;

    protected boolean isRunning = false;

    private ArrayList<Wave> wList;

    /**
     * 从xml进入
     * @param context
     * @param attrs
     */
    public SbwView(Context context, AttributeSet attrs) {
        super(context, attrs);

        wList = new ArrayList<>();
    }

    /**
     * 使用handler处理变大波浪
     */
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            //刷新数据
            flushData();
            //刷新页面
            invalidate();
            //循环动画
            if(isRunning){
                handler.sendEmptyMessageDelayed(0, 50);
            }
        }
    };


    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < wList.size(); i++){
            Wave wave = wList.get(i);
            canvas.drawCircle(wave.pointX, wave.pointY, wave.radius, wave.paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                int x = (int) event.getX();
                int y = (int) event.getY();

                addPoint(x, y);
                break;
            default:
                break;
        }
        return true;
    }

    private void addPoint(int x, int y){
        if(wList.size() == 0){
            addPoint2List(x, y);

            isRunning = true;
            handler.sendEmptyMessage(0);
        }else{
            Wave w = wList.get(wList.size() - 1);

            if(Math.abs(w.pointX - x) > DIS_SOLP || Math.abs(w.pointY - y) > DIS_SOLP){
                addPoint2List(x, y);
            }
        }
    }

    /**
     * 添加新的波浪
     * @param x
     * @param y
     */
    private void addPoint2List(int x, int y){
        Wave w = new Wave();
        w.pointX = x;
        w.pointY = y;

        Paint paint = new Paint();
        paint.setColor(colors[(int)(Math.random()*4)]);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);

        w.paint = paint;
        wList.add(w);
    }

    /**
     * 设置四种颜色
     */
    private int[] colors = new int[]{Color.BLUE, Color.RED, Color.YELLOW, Color.GREEN};


    /**
     * 刷新数据
     */
    private void  flushData(){
        for (int i = 0; i < wList.size(); i ++){
            Wave w = wList.get(i);

            //如果透明度为0，从集合中删除
            int alpha = w.paint.getAlpha();
            if(alpha < 0){
                //这里需要注意，删除掉之后，当前的i值应该减1，否则会漏掉一个对象，但是这里不影响。
                wList.remove(i);
                continue;
            }

            alpha -= 5;
            if(alpha < 5){
                alpha = 0;
            }

            //降低透明度
            w.paint.setAlpha(alpha);

            //扩大半径
            w.radius = w.radius + 3;
            //设置半径厚度
            w.paint.setStrokeWidth(w.radius / 3);
        }

        /**
         * 如果集合为0，精致刷新动画
         */
        if(wList.size() == 0){
            isRunning = false;
        }
    }

    private class Wave{
        //圆心
        int pointX;
        int pointY;

        //画笔
        Paint paint;

        //半径
        int radius;
    }

}
