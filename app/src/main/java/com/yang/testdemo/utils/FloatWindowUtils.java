package com.yang.testdemo.utils;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.yang.testdemo.R;

/**
 * 悬浮窗辅助类
 * Created by test on 2017/6/23.
 */

public class FloatWindowUtils {

    // 当前悬浮窗View
    private static View view;
    // 窗口管理
    private static WindowManager windowManager;
    // 布局参数
    private static LayoutParams params;
    // 当前悬浮窗是否正在显示
    private static boolean isShowing = false;
    // 距离屏幕的x、y方向距离
    private static float rawX;
    private static float rawY;
    // 距离控件的x、y方向距离
    private static float x;
    private static float y;
    // 是否为点击事件
    private static boolean isClick = false;

    /**
     * 显示悬浮窗
     *
     * @param context 上下文
     */
    public static void showFloatWindow(final Context context) {
        if (isShowing) {
            return;
        }
        isShowing = true;
        // 获取应用的Context
        Context applicationContext = context.getApplicationContext();
        // 获取WindowManager
        windowManager = (WindowManager) applicationContext.getSystemService(Context.WINDOW_SERVICE);
        view = initView(context);
        params = new LayoutParams();
        params.type = LayoutParams.TYPE_PHONE;
        // 悬浮窗口不可聚焦
        params.flags = LayoutParams.FLAG_NOT_FOCUSABLE;
        params.format = PixelFormat.TRANSLUCENT;
        // 设置宽高
        params.width = DensityUtils.dp2px(context, 150);
        params.height = DensityUtils.dp2px(context, 80);
        // 设置位置
        params.gravity = Gravity.START | Gravity.TOP;
        windowManager.addView(view, params);
    }

    /**
     * 设置悬浮窗布局
     *
     * @param context 上下文
     * @return 悬浮窗布局View
     */
    private static View initView(final Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_float_window, null);
        final RelativeLayout rlFloatView = (RelativeLayout) view.findViewById(R.id.rl_float_window);

        rlFloatView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        rawX = event.getRawX();
                        rawY = event.getRawY();
                        x = event.getX();
                        y = event.getY();
                        isClick = true;
                        break;

                    case MotionEvent.ACTION_MOVE:
                        if (Math.abs(event.getRawX() - rawX) > 5 || Math.abs(event.getRawY() - rawY) > 5) {
                            params.x = (int) (event.getRawX() - x);
                            params.y = (int) (event.getRawY() - getStatusBarHeight(context) - y);
                            windowManager.updateViewLayout(rlFloatView, params);
                            isClick = false;
                        }
                        break;
                }

                return !isClick;
            }
        });

        rlFloatView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "我被点击了", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    /**
     * 隐藏悬浮窗
     */
    public static void removeFloatWindow() {
        if (isShowing && null != view) {
            windowManager.removeView(view);
            isShowing = false;
        }
    }

    /**
     * 获取状态栏的高度
     *
     * @param context 上下文
     * @return 状态栏的高度
     */
    private static int getStatusBarHeight(Context context) {
        int statusBarHeight = -1;
        //获取status_bar_height资源的ID
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            // 根据资源ID获取响应的尺寸值
            return statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
        }
        return 0;
    }
}
