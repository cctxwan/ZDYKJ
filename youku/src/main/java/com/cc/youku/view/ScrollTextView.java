package com.cc.youku.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 滚动的TextView
 */
public class ScrollTextView extends View {

    //画笔
    private Paint paint = new Paint();
    //默认从0开始滚动
    private float i = 0;
    //自定义线程，让其通过对i值的修改达到滚动的效果
    private MyThread thread;

    /**
     * 界面直接调用用这个构造器
     * @param context
     */
    public ScrollTextView(Context context) {
        super(context);
    }

    /**
     * XML里面用这个构造器
     * @param context
     * @param attrs
     */
    public ScrollTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        //设置滚动字体大小
        paint.setTextSize(50);
        //实现一个textview
        canvas.drawText("我是滚动的TextView", i, 50, paint);
        //实例化自定义线程并启动
        if(thread == null){
            thread = new MyThread();
            thread.start();
        }
    }

    /**
     * 自定义线程
     */
    class MyThread extends Thread{
        @Override
        public void run() {
            super.run();
            //死循环
            while (true){
                //对i值每次修改
                i += 3;
                //当i大于getWidth()（屏幕宽度）时，说明已经看不到了
                if(i > getWidth()){
                    //设置i的大小让其通过paint的mea方法是计算文字长度，达到效果
                    i = 0 - paint.measureText("我是滚动的TextView");
                }

                //view提供的刷新绘制方法的方法
                postInvalidate();
                try {
                    //隔30ms执行
                    this.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
