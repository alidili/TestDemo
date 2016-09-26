package com.yang.testdemo.utils;

import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.yang.testdemo.Constant;
import com.yang.testdemo.logic.Controller;

/**
 * 信息输出
 * Created by yangle on 2016/3/28.
 */
public class OutPutMessage {

    public static Toast UIToast;
    public static Handler handler;

    /**
     * 当前使用对象。包括：开发，测送，用户
     */
    public static int currentOutTarget = Constant.OUT_TARGET.DEBUG;

    /**
     * 输出Log
     *
     * @param TAG
     * @param message
     */
    public static void LogCatInfo(String TAG, String message) {
        if (currentOutTarget != Constant.OUT_TARGET.USER) {
            Log.i(TAG, message);
        }
    }

    /**
     * Toast显示
     *
     * @param str Toast显示内容
     */
    public static void showToast(final String str) {
        if (handler == null) {
            if (Thread.currentThread().getName().equals("main")) {
                handler = new Handler();
            }
        }
        if (handler != null) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if (UIToast == null) {
                        UIToast = Toast.makeText(
                                Controller.getCurrentActivity(), str,
                                Toast.LENGTH_SHORT);
                        UIToast.show();
                        handler.postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                UIToast.cancel();
                            }
                        }, 1000);

                    } else {
                        UIToast.setText(str);
                        UIToast.show();
                        handler.postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                UIToast.cancel();
                            }
                        }, 2000);
                    }
                }
            });
        }
    }
}
