package com.cc.listview_ch.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Scroller;

/**
 * 侧滑
 * @kcw
 */
public class SlideLayout extends FrameLayout {

    //item内容的view
    private View txt_view;
    //删除按钮的view
    private View txt_delete;

    //item内容和删除按钮的高度
    private int txt_width;
    private int delete_width;

    //item内容和删除按钮的宽度
    private int heigth;

    //这是一个滚动器
    private Scroller scroller;

    /**
     * 构造方法，默认xml进来
     * @param context
     * @param attrs
     */
    public SlideLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        //实例化出滚动器
        scroller = new Scroller(context);
    }

    /**
     * 布局文件加载好才调用这个方法
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //item默认是第一个孩子
        txt_view = getChildAt(0);
        //删除布局view默认是第二个孩子
        txt_delete = getChildAt(1);
    }

    /**
     * 测量宽高
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取item内容的宽度
        txt_width = txt_view.getMeasuredWidth();
        //获取删除按钮的宽度
        delete_width = txt_delete.getMeasuredWidth();

        //item内容和删除的高度都一样
        heigth = getMeasuredHeight();
    }

    /**
     * 指定删除位置按钮
     * @param changed
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        //指定删除按钮的位置
        txt_delete.layout(txt_width, 0, txt_width + delete_width, heigth);
    }


    //记录开始坐标
    private float startX;
    private float startY;
    //
    private float downX;
    private float downY;


    /**
     * true:拦截子事件，重新执行onTouchEvent方法
     * false：不拦截
     * @param event
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        boolean intercept = false;
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.i("TAG", "ACTION_DOWN");
                //获取开始的坐标
                downX = startX = event.getX();
                downY = startY = event.getY();
                if(onStatechangelistenter != null){
                    onStatechangelistenter.onDown(this);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i("TAG", "ACTION_MOVE");
                //拿到结束的坐标
                float endX = event.getX();


                //重新获取开始的坐标
                startX = event.getX();


                //在X、Y轴滑动的距离
                float DX = Math.abs(endX - downX);
                if(DX > 8){
                    intercept = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return  intercept;
    }

    /**
     * 设置手势事件
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.i("TAG", "ACTION_DOWN");
                //获取开始的坐标
                downX = startX = event.getX();
                downY = startY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i("TAG", "ACTION_MOVE");
                //拿到结束的坐标
                float endX = event.getX();
                float endY = event.getY();

                //计算偏移量（获取X坐标的滑动长度）
                float length = endX - startX;

                //x轴
                int scroll = (int) (getScrollX() - length);
                //如果小于0，说明没有滑动，还为0
                if(scroll < 0){
                    scroll = 0;
                //如果待遇delete控件的长度，说明已经滑动到了最大值，就等于删除按钮的长度
                }else if(scroll > delete_width){
                    scroll = delete_width;
                }


                scrollTo(scroll, getScrollY());

                //重新获取开始的坐标
                startX = event.getX();
                startY = event.getY();


                //在X、Y轴滑动的距离
                float DX = Math.abs(endX - downX);
                float DY = Math.abs(endY - downY);
                if(DX > DY && DX > 8){
                    //水平方向拦截
                    //侧滑
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
            case MotionEvent.ACTION_UP:
                Log.i("TAG", "ACTION_UP");
                //滑动的偏移量
                int totalScrollX = getScrollX();
                if(totalScrollX < delete_width / 2) {
                    //抹平偏移量（关闭侧滑）
                    closeMenu();
                }else{
                    //大于1/2，打开
                    openMenu();
                }
                break;
        }
        return true;
    }

    /**
     * 关闭侧滑
     */
    public void closeMenu() {
        //0代表itemview的整个长度
        //拿到x轴的偏移数据
        int X = 0 - getScrollX();
        scroller.startScroll(getScrollX(), getScrollY(), X, getScrollY());
        invalidate();
        if(onStatechangelistenter != null){
            onStatechangelistenter.onClose(this);
        }
    }

    /**
     * 打开侧滑
     */
    public void openMenu() {
        //delete_width代表delete_width的整个长度
        //拿到x轴的展开偏移数据
        int X = delete_width - getScrollX();
        scroller.startScroll(getScrollX(), getScrollY(), X, getScrollY());
        invalidate();
        if(onStatechangelistenter != null){
            onStatechangelistenter.onOpen(  this);
        }
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(scroller.computeScrollOffset()){
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            invalidate();
        }
    }

    //
    public interface OnStatechangelistenter{
        void onClose(SlideLayout slideLayout);
        void onDown(SlideLayout slideLayout);
        void onOpen(SlideLayout slideLayout);
    };

    public OnStatechangelistenter onStatechangelistenter;

    public void setOnStatechangelistenter(OnStatechangelistenter listenter){
        onStatechangelistenter = listenter;
    }

}
