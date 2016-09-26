package com.yang.testdemo;

import android.app.Application;

import com.yang.testdemo.logic.Controller;

/**
 * Created by yangle on 2016/7/4.
 */
public class TestDemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Controller.setContext(this);
        Controller.defaultController();
    }
}
