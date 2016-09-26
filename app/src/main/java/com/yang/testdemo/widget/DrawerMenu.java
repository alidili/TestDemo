package com.yang.testdemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;

/**
 * Created by yangle on 2016/5/18.
 */
public class DrawerMenu extends ViewGroup implements View.OnClickListener {

    private boolean mIsChanged = true;
    private int mButtonY;

    public DrawerMenu(Context context) {
        this(context, null);
    }

    public DrawerMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawerMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (mIsChanged) {
            layoutBottom();
            int count = getChildCount();
            for (int i = 0; i < count - 1; i++) {
                View child = getChildAt(i + 1);
                int childWidth = child.getMeasuredWidth();
                int childHeight = child.getMeasuredHeight();
                child.layout(0, mButtonY - childHeight * (i + 1), childWidth, mButtonY - childHeight * i);

                child.setVisibility(GONE);
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void layoutBottom() {
        View mButton_buttom = getChildAt(0);
        mButton_buttom.setOnClickListener(this);
        int mWidth_button_bottom = mButton_buttom.getMeasuredWidth();
        int mHeight_button_bottom = mButton_buttom.getMeasuredHeight();
        int mButtonX = 0;
        mButtonY = getMeasuredHeight() - mHeight_button_bottom;
        mButton_buttom.layout(mButtonX, mButtonY, mWidth_button_bottom, getMeasuredHeight());
    }

    @Override
    public void onClick(View v) {
        toggleMenu();
    }

    private void toggleMenu() {
        if (mIsChanged) {
            int count = getChildCount();
            for (int i = 0; i < count - 1; i++) {
                View child = getChildAt(i + 1);
                TranslateAnimation ta = new TranslateAnimation(-child.getMeasuredWidth(), 0, 0, 0);
                ta.setDuration(500 + i * 50);
                child.startAnimation(ta);
                child.setVisibility(VISIBLE);
                mIsChanged = false;
            }
        } else {
            int count = getChildCount();
            for (int i = 0; i < count - 1; i++) {
                View child = getChildAt(i + 1);
                TranslateAnimation ta = new TranslateAnimation(0, -child.getMeasuredWidth(), 0, 0);
                ta.setDuration(500 + i * 50);
                child.startAnimation(ta);
                child.setVisibility(GONE);
                mIsChanged = true;
            }
        }
    }
}
