package com.yang.testdemo.activity;

import android.app.Activity;
import android.os.Bundle;

import com.yang.testdemo.logic.Controller;

/**
 * Created by yangle on 2016/7/1.
 */
public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Controller.setCurrentActivity(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Controller.setCurrentActivity(this);
        Controller.addActivity(this);
    }

}
