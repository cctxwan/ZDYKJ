package com.cc.scrolltxt.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

/**
 * 自定义View（滚动的textview， 圆（动图））
 */
public class MyViewTwo extends View {

    private Paint paint = new Paint();

    private float rx = 0;

    private MyThread thread;

    private RectF rectF = new RectF(0, 80, 100, 180);

    private float i = 0;

    public MyViewTwo(Context context) {
        super(context);
    }

    public MyViewTwo(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //设置textview字体大小
        paint.setTextSize(50);

        //颜色
//        paint.setColor(Color.RED);
        canvas.drawText("我是滚动的TextView", rx, 50, paint);

        canvas.drawArc(rectF, 0, i, true, paint);

        if(thread == null){
            thread = new MyThread();
            thread.start();
        }
    }

    /**
     * 创建自己的线程
     */
    class MyThread extends Thread{

        Random random = new Random();

        @Override
        public void run() {

            //循环模拟滚动
            while (true){
                rx += 3;


                i ++;


                //当rx坐标大于屏幕宽度的时候重新从0开始绘制
                if(rx > getWidth()){
                    //paint所提供的计算文字长度的方法
                    rx = 0 - paint.measureText("我是滚动的TextView");
                }

                if(i > 360){
                    i = 0;
                }


                int r = random.nextInt(256);
                int g = random.nextInt(256);
                int b = random.nextInt(256);

                //边滚动、转圈边替换颜色（使用这个的话需要注释掉paint.setColor()方法）
                paint.setARGB(255, r, g, b);

                //在线程中更新绘制方法
                postInvalidate();

                try {
                    this.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
