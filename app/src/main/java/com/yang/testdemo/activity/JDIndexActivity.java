package com.yang.testdemo.activity;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.yang.testdemo.R;
import com.yang.testdemo.widget.CircleImageView;
import com.yang.testdemo.widget.ObservableScrollView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 京东金融首页效果
 * Created by yangle on 2017/2/5.
 */
public class JDIndexActivity extends BaseActivity {

    @Bind(R.id.iv_portrait)
    CircleImageView ivPortrait;
    @Bind(R.id.scrollView)
    ObservableScrollView scrollView;

    private ViewGroup.MarginLayoutParams marginLayoutParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jd_index);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        marginLayoutParams = new ViewGroup.MarginLayoutParams(ivPortrait.getLayoutParams());

        scrollView.setScrollViewListener(new ObservableScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
                // 设置头像距离顶部的距离
                int top = dp2px(70) - y;
                if (top < dp2px(10)) {
                    // 固定在标题栏
                    marginLayoutParams.setMargins(dp2px(20), dp2px(10), 0, 0);
                } else {
                    // 向上移动
                    marginLayoutParams.setMargins(dp2px(20), dp2px(70) - y, 0, 0);
                }

                // 根据向上滑动的距离设置头像的大小
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(marginLayoutParams);
                // 头像最大为45dp，最小为30dp
                int height = dp2px(45) - y < dp2px(30) ? dp2px(30) : dp2px(45) - y;
                layoutParams.height = height;
                layoutParams.width = height;
                ivPortrait.setLayoutParams(layoutParams);
            }
        });
    }

    private int dp2px(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }
}
