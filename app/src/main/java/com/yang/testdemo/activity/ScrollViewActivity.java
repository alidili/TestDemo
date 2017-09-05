package com.yang.testdemo.activity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.yang.testdemo.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * ScrollView
 * Created by yangle on 2017/9/4.
 */

public class ScrollViewActivity extends BaseActivity {

    @Bind(R.id.ll_option)
    LinearLayout llOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view);
        ButterKnife.bind(this);

        for (int i = 0; i < 10; i++) {
            Button btnAnswer = new Button(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            btnAnswer.setLayoutParams(params);
            btnAnswer.setText("啦啦啦");
            llOption.addView(btnAnswer);
        }
    }
}
