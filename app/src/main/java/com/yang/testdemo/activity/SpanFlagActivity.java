package com.yang.testdemo.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.yang.testdemo.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * SpannableString Flag测试
 * Created by yangle on 2017/8/24.
 */

public class SpanFlagActivity extends BaseActivity {

    @Bind(R.id.tv_content)
    TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_span_flag);
        ButterKnife.bind(this);

        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("零一二三四五六七八九十");
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#0099EE"));
        spannableStringBuilder.setSpan(colorSpan, 1, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableStringBuilder.insert(1, "1");
        tvContent.setText(spannableStringBuilder);
    }
}
