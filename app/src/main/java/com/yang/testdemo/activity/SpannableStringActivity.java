package com.yang.testdemo.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yang.testdemo.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yangle on 2017/9/29.
 */

public class SpannableStringActivity extends BaseActivity {

    @Bind(R.id.tv_content)
    TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spannable_string);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
        String originContent = "你看我不仅能变颜色，还能点击。";
        SpannableString content = new SpannableString(originContent);

        // 设置颜色
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#4DB6AC"));
        content.setSpan(colorSpan, 7, 9, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        // 设置点击事件
        MyClickableSpan myClickableSpan = new MyClickableSpan();
        content.setSpan(myClickableSpan, 12, 14, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // 设置此方法后，点击事件才能生效
        tvContent.setMovementMethod(LinkMovementMethod.getInstance());

        tvContent.setText(content);
    }

    class MyClickableSpan extends ClickableSpan {

        @Override
        public void onClick(View widget) {
            Toast.makeText(SpannableStringActivity.this, "我被点击了", Toast.LENGTH_SHORT).show();
        }
    }
}
