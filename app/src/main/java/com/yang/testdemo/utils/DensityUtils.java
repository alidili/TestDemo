package com.yang.testdemo.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * px与dp互相转换
 * Created by yangle on 2016/4/12.
 */
public class DensityUtils {

    public static int dp2px(Context context, float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                context.getResources().getDisplayMetrics());
    }

    public static float px2dp(Context context, int px) {
        //获取设备密度
        float density = context.getResources().getDisplayMetrics().density;
        float dp = px / density;
        return dp;
    }
}
