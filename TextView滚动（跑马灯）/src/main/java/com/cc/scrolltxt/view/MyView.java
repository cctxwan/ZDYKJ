package com.cc.scrolltxt.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.cc.scrolltxt.R;

/**
 * 自定义（文字、直线、矩形、圆形、图片）
 */
public class MyView extends View {


    private Bitmap bitmap;

    /**
     * 这个构造方法是通过代码实例化的时候会调用
     * @param context
     */
    public MyView(Context context) {
        super(context);
        //构造Bitmap
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
    }

    /**
     * 这个是通过xml文件加载时会调用
     * @param context
     * @param attrs
     */
    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //构造Bitmap
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //这行代码没有用（源码里面什么都没写）
        super.onDraw(canvas);

        //实例化绘画类，可以当成一个画笔
        Paint paint = new Paint();
        //文字大小（像素）
        paint.setTextSize(60);
        //设置画笔为红色
        paint.setColor(Color.RED);
        //设置为空心
        paint.setStyle(Paint.Style.STROKE);

        /**
         * 文字
         * 第一个参数：内容
         * 第二个参数：起始的x坐标
         * 第三个参数：终止的y坐标
         */
        canvas.drawText("你是最帅的", 10, 60, paint);

        /**
         * 直线
         * 第一个参数：起始X坐标
         * 第二个参数：起始Y坐标
         * 第三个参数：终止X坐标
         * 第四个参数：终止Y坐标
         */
        canvas.drawLine(10, 120, 200, 120, paint);

        /**
         * 矩形
         * 第一个参数：距左边距离
         * 第二个参数：距上边距离
         * 第三个参数：距右边距离
         * 第四个参数：距下边距离
         */
        canvas.drawRect(10, 150, 200, 220, paint);

        /**
         * 圆角矩形
         * 第一个参数：距左边距离
         * 第二个参数：距上边距离
         * 第三个参数：距右边距离
         * 第四个参数：距下边距离
         * 第五个参数：X轴的圆角弧度
         * 第六个参数：Y轴的圆角弧度
         */
        canvas.drawRoundRect(10, 250, 200, 450, 10, 10, paint);

        /**
         * 圆
         * 第一个参数：与第二个参数配置确定圆心的坐标
         * 第二个参数：与第一个参数配置确定圆心的坐标
         * 第三个参数：半径
         */
        canvas.drawCircle(100, 600, 90, paint);

        /**
         * 直线
         * 第一个参数：图片
         * 第二个参数：距左边的距离
         * 第三个参数：距上边的距离
         */
        canvas.drawBitmap(bitmap, 10, 750, paint);
    }
}
