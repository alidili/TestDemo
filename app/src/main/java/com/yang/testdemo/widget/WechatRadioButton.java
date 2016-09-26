package com.yang.testdemo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.StaticLayout;
import android.util.AttributeSet;
import android.widget.RadioButton;

import com.yang.testdemo.R;

/**
 * Created by Droidroid on 2016/5/10.
 */
public class WechatRadioButton extends RadioButton {

    private Paint mFocusPaint;
    private Paint mTextPaint;
    private Paint mDefocusPaint;

    private int iconWidth;
    private int iconPadding;
    private int iconHeight;

    private Bitmap mDefocusBitmap;
    private Bitmap mFocusBitmap;
    private Drawable mFocusDrawable;
    private Drawable mDefocusDrawable;

    private Rect mRect;
    private int mAlpha;
    private int mColor;
    private float mFontHeight;
    private float mTextWidth;

    public WechatRadioButton(Context context) {
        this(context, null);
    }

    public WechatRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mFocusPaint = new Paint();
        mTextPaint = new Paint();
        mDefocusPaint = new Paint();
        mFocusPaint.setAntiAlias(true);
        mTextPaint.setAntiAlias(true);
        mDefocusPaint.setAntiAlias(true);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.WechatRadioButton);
        mFocusDrawable = ta.getDrawable(R.styleable.WechatRadioButton_focus_icon);
        mDefocusDrawable = ta.getDrawable(R.styleable.WechatRadioButton_defocus_icon);
        mColor = ta.getColor(R.styleable.WechatRadioButton_focus_color, Color.BLUE);
        ta.recycle();

        setButtonDrawable(null);
        if (mDefocusDrawable != null) {
            setCompoundDrawablesWithIntrinsicBounds(null, mDefocusDrawable, null, null);
            mDefocusDrawable = getCompoundDrawables()[1];
        }

        if (mFocusDrawable == null || mDefocusDrawable == null) {
            throw new RuntimeException("'focus_icon' and 'defocus_icon' attribute should be defined");
        }

        mRect = mDefocusDrawable.getBounds();
        iconWidth = mRect.width();
        iconHeight = mRect.height();
        iconPadding = getCompoundDrawablePadding();

        Paint.FontMetrics fontMetrics = getPaint().getFontMetrics();
        mFontHeight = (float) Math.ceil(fontMetrics.descent - fontMetrics.ascent);
        mTextWidth = StaticLayout.getDesiredWidth(getText(), getPaint());

        mDefocusBitmap = getBitmapFromDrawable(mDefocusDrawable);
        mFocusBitmap = getBitmapFromDrawable(mFocusDrawable);

        if (isChecked()) {
            mAlpha = 255;
        }
    }

    private Bitmap getBitmapFromDrawable(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        } else {
            throw new RuntimeException("The Drawable must be an instance of BitmapDrawable");
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawDefocusIcon(canvas);
        drawFocusIcon(canvas);
        drawDefocusText(canvas);
        drawFocusText(canvas);
    }

    private void drawDefocusIcon(Canvas canvas) {
        mDefocusPaint.setAlpha(255 - mAlpha);
        canvas.drawBitmap(mDefocusBitmap, (getWidth() - iconWidth) / 2, iconPadding, mDefocusPaint);
    }

    private void drawFocusIcon(Canvas canvas) {
        mFocusPaint.setAlpha(mAlpha);
        canvas.drawBitmap(mFocusBitmap, (getWidth() - iconWidth) / 2, getPaddingTop(), mFocusPaint);
    }

    private void drawDefocusText(Canvas canvas) {
        mTextPaint.setColor(getCurrentTextColor());
        mTextPaint.setAlpha(255 - mAlpha);
        mTextPaint.setTextSize(getTextSize());
        canvas.drawText(getText().toString(), getWidth() / 2 - mTextWidth / 2
                , iconHeight + iconPadding + getPaddingTop() + mFontHeight, mTextPaint);
    }

    private void drawFocusText(Canvas canvas) {
        mTextPaint.setColor(mColor);
        mTextPaint.setAlpha(mAlpha);
        canvas.drawText(getText().toString(), getWidth() / 2 - mTextWidth / 2
                , iconHeight + iconPadding + getPaddingTop() + mFontHeight, mTextPaint);
    }


    public void updateAlpha(float alpha) {
        mAlpha = (int) alpha;
        invalidate();
    }

    public void setRadioButtonChecked(boolean isChecked) {
        setChecked(isChecked);
        if (isChecked) {
            mAlpha = 255;
        } else {
            mAlpha = 0;
        }
        invalidate();
    }

}
