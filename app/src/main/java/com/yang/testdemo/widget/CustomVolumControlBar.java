package com.yang.testdemo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.yang.testdemo.R;

/**
 * Created by yangle on 2016/5/23.
 */
public class CustomVolumControlBar extends View {

    /**
     * 第一圈的颜色
     */
    private int mFirstColor;
    /**
     * 第二圈的颜色
     */
    private int mSecondColor;
    /**
     * 圈的宽度
     */
    private int mCircleWidth;
    /**
     * 画笔
     */
    private Paint mPaint;
    /**
     * 当前进度
     */
    private int mCurrentCount = 3;

    /**
     * 中间的图片
     */
    private Bitmap mImage;
    /**
     * 每个块块间的间隙
     */
    private int mSplitSize;
    /**
     * 个数
     */
    private int mCount;

    private Rect mRect;

    public CustomVolumControlBar(Context context) {
        this(context, null);
    }

    public CustomVolumControlBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomVolumControlBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomVolumControlBar, defStyleAttr, 0);
        int n = typedArray.getIndexCount();

        for (int i = 0; i < n; i++) {
            int attr = typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.CustomVolumControlBar_firstColor:
                    mFirstColor = typedArray.getColor(attr, Color.GREEN);
                    break;

                case R.styleable.CustomVolumControlBar_secondColor:
                    mSecondColor = typedArray.getColor(attr, Color.CYAN);
                    break;

                case R.styleable.CustomVolumControlBar_bg:
                    mImage = BitmapFactory.decodeResource(getResources(), typedArray.getResourceId(attr, 0));
                    break;

                case R.styleable.CustomVolumControlBar_circleWidth:
                    mCircleWidth = typedArray.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));
                    break;

                case R.styleable.CustomVolumControlBar_dotCount:
                    mCount = typedArray.getInt(attr, 20); //默认20
                    break;

                case R.styleable.CustomVolumControlBar_splitSize:
                    mSplitSize = typedArray.getInt(attr, 20);
                    break;
            }

            typedArray.recycle();
            mPaint = new Paint();
            mRect = new Rect();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //消除锯齿
        mPaint.setAntiAlias(true);
        //设置圆环的宽度
        mPaint.setStrokeWidth(mCircleWidth);
        //定义线段断点形状为圆头
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        //消除锯齿
        mPaint.setAntiAlias(true);
        //设置空心
        mPaint.setStyle(Paint.Style.STROKE);
        //获取圆心的x坐标
        int centre = getWidth() / 2;
        //半径
        int radius = centre - mCircleWidth / 2;

        //画块块去
        drawOval(canvas, centre, radius);

        //计算内切正方形的位置
        int relRadius = radius - mCircleWidth / 2;// 获得内圆的半径
        //内切正方形的距离顶部 = mCircleWidth + relRadius - √2 / 2
        mRect.left = (int) (relRadius - Math.sqrt(2) * 1.0f / 2 * relRadius) + mCircleWidth;
        //内切正方形的距离左边 = mCircleWidth + relRadius - √2 / 2
        mRect.top = (int) (relRadius - Math.sqrt(2) * 1.0f / 2 * relRadius) + mCircleWidth;
        mRect.bottom = (int) (mRect.left + Math.sqrt(2) * relRadius);
        mRect.right = (int) (mRect.left + Math.sqrt(2) * relRadius);

        //如果图片比较小，那么根据图片的尺寸放置到正中心
        if (mImage.getWidth() < Math.sqrt(2) * relRadius) {
            mRect.left = (int) (mRect.left + Math.sqrt(2) * relRadius * 1.0f / 2 - mImage.getWidth() * 1.0f / 2);
            mRect.top = (int) (mRect.top + Math.sqrt(2) * relRadius * 1.0f / 2 - mImage.getHeight() * 1.0f / 2);
            mRect.right = mRect.left + mImage.getWidth();
            mRect.bottom = mRect.top + mImage.getHeight();
        }

        // 绘图
        canvas.drawBitmap(mImage, null, mRect, mPaint);
    }

    /**
     * 根据参数画出每个小块
     *
     * @param canvas 画布
     * @param centre 圆心的x坐标
     * @param radius 半径
     */
    private void drawOval(Canvas canvas, int centre, int radius) {
        //根据需要画的个数以及间隙计算每个块块所占的比例*360
        float itemSize = (360 * 1.0f - mCount * mSplitSize) / mCount;
        //用于定义的圆弧的形状和大小的界限
        RectF oval = new RectF(centre - radius, centre - radius, centre + radius, centre + radius);

        //设置圆环的颜色
        mPaint.setColor(mFirstColor);
        for (int i = 0; i < mCount; i++) {
            //根据进度画圆弧
            canvas.drawArc(oval, i * (itemSize + mSplitSize), itemSize, false, mPaint);
        }

        mPaint.setColor(mSecondColor);
        for (int i = 0; i < mCurrentCount; i++) {
            canvas.drawArc(oval, i * (itemSize + mSplitSize), itemSize, false, mPaint);
        }
    }

    private int xDown, xUp;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDown = (int) event.getY();
                break;

            case MotionEvent.ACTION_UP:
                xUp = (int) event.getY();
                if (xUp > xDown) { //下滑
                    down();
                } else {
                    up();
                }
                break;
        }

        return true;
    }

    /**
     * 当前数量+1
     */
    public void up() {
        if (mCurrentCount < mCount) {
            mCurrentCount++;
        }
        postInvalidate();
    }

    /**
     * 当前数量-1
     */
    public void down() {
        if (mCurrentCount > 0) {
            mCurrentCount--;
        }
        postInvalidate();
    }
}
