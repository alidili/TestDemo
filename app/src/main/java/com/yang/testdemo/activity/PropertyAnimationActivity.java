package com.yang.testdemo.activity;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.yang.testdemo.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yangle on 2016/6/23.
 */
public class PropertyAnimationActivity extends Activity {

    @Bind(R.id.iv_photo)
    ImageView ivPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animation);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_trans, R.id.btn_rotate, R.id.btn_alpha, R.id.btn_scale})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_trans:
                ObjectAnimator oa = ObjectAnimator.ofFloat(ivPhoto, "translationX", 0, 10, 20, 40, 60, 100, 120, 140);
                oa.setDuration(3000);
                oa.setRepeatCount(2);
                oa.setRepeatMode(ObjectAnimator.REVERSE);
                oa.start();
                break;

            case R.id.btn_rotate:
                ObjectAnimator ra = ObjectAnimator.ofFloat(ivPhoto, "rotationY", 0, 360);
                ra.setDuration(3000);
                ra.setRepeatCount(2);
                ra.setRepeatMode(ObjectAnimator.REVERSE);
                ra.start();
                break;

            case R.id.btn_alpha:
                ObjectAnimator aa = ObjectAnimator.ofFloat(ivPhoto, "alpha", 0, 0.2f, 0.4f, 0.6f, 0.8f, 1.0f);
                aa.setDuration(3000);
                aa.setRepeatCount(2);
                aa.setRepeatMode(ObjectAnimator.REVERSE);
                aa.start();
                break;

            case R.id.btn_scale:
                ObjectAnimator sa = ObjectAnimator.ofFloat(ivPhoto, "scaleX", 0, 0.2f, 0.4f, 0.6f, 0.8f, 1.0f);
                sa.setDuration(3000);
                sa.setRepeatCount(2);
                sa.setRepeatMode(ObjectAnimator.REVERSE);
                sa.start();
                break;
        }
    }
}
