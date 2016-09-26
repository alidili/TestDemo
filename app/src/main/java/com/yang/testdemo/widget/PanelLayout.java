package com.yang.testdemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yang.testdemo.R;

/**
 * Created by yangle on 2016/4/5.
 */
public class PanelLayout extends RelativeLayout {

    private TextView tv_panel;

    public PanelLayout(Context context) {
        super(context);
        initView(context);
    }

    public PanelLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public PanelLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.layout_panel, this);

        tv_panel = (TextView) findViewById(R.id.tv_panel);
    }

    public void setPanelText(String title) {
        if (tv_panel == null) {
            throw new AssertionError("title view not found.");
        }
        tv_panel.setText(title);
    }
}
