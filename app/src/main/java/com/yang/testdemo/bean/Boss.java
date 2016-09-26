package com.yang.testdemo.bean;

import android.util.Log;

import com.yang.testdemo.impl.ExecuteCallBack;

/**
 * Created by Administrator on 2016/6/21.
 */
public class Boss implements ExecuteCallBack {

    @Override
    public void execute() {
        Log.i("回调测试", "工作完成");
    }
}
