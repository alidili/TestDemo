package com.yang.testdemo.activity;

import android.app.Activity;
import android.os.Bundle;

import com.yang.testdemo.R;
import com.yang.testdemo.widget.PanelLayout;

/**
 * Created by yangle on 2016/4/5.
 */
public class PanelLayoutActivity extends Activity{

    private PanelLayout panelLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel_layout);

        panelLayout = (PanelLayout) findViewById(R.id.panel);
        panelLayout.setPanelText("面板文字测试");
    }
}
