package com.yang.testdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.TextView;

import com.yang.testdemo.R;
import com.yang.testdemo.utils.StringUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yangle on 2016/3/28.
 */
public class MainActivity extends BaseActivity {

    @Bind(R.id.text)
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button)
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, PullToRefreshActivity.class);
        startActivity(intent);
    }
}
