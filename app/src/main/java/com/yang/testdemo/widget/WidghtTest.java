package com.yang.testdemo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by yangle on 2016/7/28.
 */
public class WidghtTest extends View {

    private Paint mPaint;
    private RectF mOval;
    private int mProgress;
    private int mMax = 100;

    public void setmProgress(int mProgress) {
        this.mProgress = mProgress;
        invalidate();
    }

    public WidghtTest(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mOval = new RectF();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 设置是否抗锯齿
        mPaint.setAntiAlias(true);
        // 帮助消除锯齿
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        // 设置画笔白色
        mPaint.setColor(Color.GREEN);
        // 设置画笔宽度
        mPaint.setStrokeWidth(10);
        // 设置中空的样式
        mPaint.setStyle(Paint.Style.STROKE);
        // 在中心为（100,100）的地方画个半径为96的圆，宽度为setStrokeWidth：10，也就是灰色的底边
        canvas.drawCircle(100, 100, 96, mPaint);
        // 设置画笔为蓝色
        mPaint.setColor(Color.BLUE);
        // 设置类似于左上角坐标（4,4），右下角坐标（196,196），这样也就保证了半径为96
        mOval.set(4, 4, 196, 196);
        // 画圆弧，第二个参数为：起始角度，第三个为跨的角度，第四个为true的时候是实心，false的时候为空心
        canvas.drawArc(mOval, -90, ((float) mProgress / mMax) * 360, false, mPaint);
    }
}
