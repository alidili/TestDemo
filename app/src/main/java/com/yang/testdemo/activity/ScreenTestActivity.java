package com.yang.testdemo.activity;

import android.os.Bundle;
import android.util.Log;

import com.yang.testdemo.R;
import com.yang.testdemo.utils.DensityUtils;

/**
 * 分辨率测试
 * Created by yangle on 2017/6/2.
 */

public class ScreenTestActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_test);
        Log.i("屏幕适配：", DensityUtils.dp2px(this, 10) + "");
    }
}
