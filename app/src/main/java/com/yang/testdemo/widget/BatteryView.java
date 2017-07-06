package com.yang.testdemo.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.yang.testdemo.R;

/**
 * 电量显示
 * Created by yangle on 2017/7/6.
 */

public class BatteryView extends View {

    // 控件宽
    private int width;
    // 控件高
    private int height;
    // 画笔
    private Paint paint;
    // 电池图标
    private Bitmap batteryBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.battery_icon);
    // 电池图标位置
    private Rect srcRect;
    private Rect dstRect;
    // 电量
    private float electricQuantity;

    public BatteryView(Context context) {
        this(context, null);
    }

    public BatteryView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BatteryView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        srcRect = new Rect(0, 0, width, height);
        dstRect = new Rect(0, 0, width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float left = dp2px(1.5f);
        float top = dp2px(1.5f);
        float right = (width - dp2px(3.9f)) * electricQuantity;
        float bottom = height - dp2px(1.5f);

        if (electricQuantity < 0.1) {
            paint.setColor(Color.parseColor("#E93742"));
        } else if (electricQuantity < 0.4) {
            paint.setColor(Color.parseColor("#FFD203"));
        } else {
            paint.setColor(Color.parseColor("#626262"));
        }

        // 绘制电量
        canvas.drawRect(left, top, right, bottom, paint);
        // 绘制电池图标
        canvas.drawBitmap(batteryBitmap, srcRect, dstRect, paint);
    }

    public void setElectricQuantity(float electricQuantity) {
        this.electricQuantity = electricQuantity;
        invalidate();
    }

    public float dp2px(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getContext().getResources().getDisplayMetrics());
    }
}
