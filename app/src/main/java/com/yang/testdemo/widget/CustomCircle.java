package com.yang.testdemo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.yang.testdemo.R;

/**
 * Created by yangle on 2016/5/20.
 */
public class CustomCircle extends View {

    private Paint mPaint;
    private int mProgress;

    public CustomCircle(Context context) {
        this(context, null);
    }

    public CustomCircle(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomCircle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        // 绘图线程
        new Thread() {
            public void run() {
                while (true) {
                    mProgress++;
                    if (mProgress == 361) {
                        return;
                    }
                    postInvalidate();
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int center = getWidth() / 2; //获取圆心的x坐标
        int radius = center - 6 / 2; //半径
        mPaint.setStrokeWidth(6); //设置圆环的宽度
        mPaint.setAntiAlias(true); //消除锯齿
        mPaint.setStyle(Paint.Style.STROKE); //设置空心
        RectF oval = new RectF(center - radius, center - radius, center + radius, center + radius); //用于定义的圆弧的形状和大小的界限

        mPaint.setColor(getResources().getColor(R.color.color_D4F668)); //设置圆环的颜色
        canvas.drawArc(oval, -90, mProgress, false, mPaint); //根据进度画圆弧
    }
}
