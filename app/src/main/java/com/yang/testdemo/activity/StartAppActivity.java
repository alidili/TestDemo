package com.yang.testdemo.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.yang.testdemo.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 启动外部应用
 * Created by yangle on 2017/6/8.
 */

public class StartAppActivity extends BaseActivity {

    @Bind(R.id.iv_icon)
    ImageView ivIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_activity);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_method_one, R.id.btn_btn_method_two, R.id.btn_btn_method_three, R.id.btn_get_icon})
    public void onViewClicked(View view) {
        Intent intent = null;

        switch (view.getId()) {
            case R.id.btn_method_one:
                intent = getPackageManager().getLaunchIntentForPackage("com.lzx.demo");
                break;

            case R.id.btn_btn_method_two:
                intent = new Intent();
                ComponentName componentName = new ComponentName("com.lzx.demo", "com.lzx.demo.ui.EndlessLinearLayoutActivity");
                intent.setComponent(componentName);
                //intent.setAction("android.intent.action.MAIN");
                break;

            case R.id.btn_btn_method_three:
                intent = new Intent();
                intent.setAction("android.intent.action.EndlessLinearLayoutActivity");
                break;

            case R.id.btn_get_icon:
                ivIcon.setBackground(getAppIcon("com.lzx.demo"));
                break;

            default:
                break;
        }

        if (intent != null) {
            startActivity(intent);
        }
    }

    public Drawable getAppIcon(String packName) {
        try {
            PackageManager packageManager = getPackageManager();
            ApplicationInfo info = packageManager.getApplicationInfo(packName, 0);
            return info.loadIcon(packageManager);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
