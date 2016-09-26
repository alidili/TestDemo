package com.yang.testdemo.bean;

import android.util.Log;

import com.yang.testdemo.impl.ExecuteCallBack;

/**
 * Created by yangle on 2016/6/21.
 */
public class Employee {

    private ExecuteCallBack executeCallBack;

    public Employee(ExecuteCallBack executeCallBack) {
        this.executeCallBack = executeCallBack;
    }

    public void work() {
        for (int i = 1; i < 5; i++) {
            Log.i("回调测试", "完成了第" + i + "项工作");
        }
        executeCallBack.execute();
    }
}
